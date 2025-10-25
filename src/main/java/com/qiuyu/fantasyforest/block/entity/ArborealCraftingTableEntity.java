// ArborealCraftingTableEntity.java
package com.qiuyu.fantasyforest.block.entity;

import com.qiuyu.fantasyforest.block.ModBlocks;
import com.qiuyu.fantasyforest.recipe.ArborealCraftingTableRecipe;
import com.qiuyu.fantasyforest.screen.ArborealCraftingTableProvider;
import com.qiuyu.fantasyforest.screen.ArborealCraftingTableScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ArborealCraftingTableEntity extends BlockEntity implements ExtendedScreenHandlerFactory, Inventory, ArborealCraftingTableProvider {

    // 定义槽位：1个树苗槽 + 9个合成格 + 1个输出槽
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(11, ItemStack.EMPTY);
    public static final int SAPLING_SLOT = 0;
    public static final int OUTPUT_SLOT = 10;
    public static final int CRAFTING_START = 1;
    public static final int CRAFTING_END = 9;
    private int progress;

    public ArborealCraftingTableEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ARBOREAL_CRAFTING_TABLE, pos, state);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(ModBlocks.ARBOREAL_CRAFTING_TABLE.getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ArborealCraftingTableScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    // Inventory 接口实现
    @Override
    public int size() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        return inventory.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getStack(int slot) {
        return inventory.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack result = Inventories.splitStack(inventory, slot, amount);
        if (!result.isEmpty() && slot != OUTPUT_SLOT) {
            updateRecipe();
        }
        markDirty();
        return result;
    }

    @Override
    public ItemStack removeStack(int slot) {
        ItemStack result = Inventories.removeStack(inventory, slot);
        if (!result.isEmpty() && slot != OUTPUT_SLOT) {
            updateRecipe();
        }
        markDirty();
        return result;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        inventory.set(slot, stack);
        if (stack.getCount() > getMaxCountPerStack()) {
            stack.setCount(getMaxCountPerStack());
        }
        updateRecipe();
        markDirty();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return Inventory.canPlayerUse(this, player);
    }

    @Override
    public void clear() {
        inventory.clear();
        markDirty();
    }

    // NBT 数据保存和读取
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }

    // 每 tick 更新逻辑
    public static void tick(World world, BlockPos pos, BlockState state, ArborealCraftingTableEntity entity) {
        // 这里可以添加自动合成检测逻辑
//        if (!world.isClient) {
//            // 检查配方匹配
//            entity.updateRecipe();
//        }
    }

    private void updateRecipe() {
        if (world == null || world.isClient) return;

        Optional<ArborealCraftingTableRecipe> match = getCurrentRecipe();

        if (match.isPresent()) {
            ArborealCraftingTableRecipe recipe = match.get();
            ItemStack result = recipe.craft(this, world.getRegistryManager());
            ItemStack currentOutput = getStack(OUTPUT_SLOT);

            // 安全的比较方式
            boolean shouldUpdateOutput = currentOutput.isEmpty() ||
                    !ItemStack.areEqual(currentOutput, result) ||
                    currentOutput.getCount() != result.getCount();

            if (shouldUpdateOutput) {
                setStack(OUTPUT_SLOT, result.copy());
                markDirty();
            }

        } else {
            ItemStack currentOutput = getStack(OUTPUT_SLOT);
            if (!currentOutput.isEmpty()) {
                setStack(OUTPUT_SLOT, ItemStack.EMPTY);
                markDirty();
            }
        }
    }

    private Optional<ArborealCraftingTableRecipe> getCurrentRecipe(){
        return getWorld().getRecipeManager().getFirstMatch(ArborealCraftingTableRecipe.Type.INSTANCE,this,getWorld());
    }

    @Override
    public int getCraftingProgress() {
        // 返回实际的合成进度
        return this.progress;
    }
}