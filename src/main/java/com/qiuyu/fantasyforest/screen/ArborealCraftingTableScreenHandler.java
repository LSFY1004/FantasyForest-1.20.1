// ArborealCraftingTableScreenHandler.java
package com.qiuyu.fantasyforest.screen;

import com.qiuyu.fantasyforest.block.entity.ArborealCraftingTableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;

public class ArborealCraftingTableScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private final BlockPos pos; // 存储方块位置

    // 服务端使用的构造器 - 接收方块实体
    public ArborealCraftingTableScreenHandler(int syncId, PlayerInventory playerInventory, ArborealCraftingTableEntity entity) {
        super(ModScreenHandlers.ARBOREAL_CRAFTING_TABLE_SCREEN_HANDLER, syncId);
        this.inventory = entity;
        this.pos = entity.getPos(); // 从方块实体获取位置

        checkSize(inventory, 11);
        inventory.onOpen(playerInventory.player);
        setupSlots(playerInventory);
    }

    // 客户端使用的构造器 - 从数据包读取方块位置
    public ArborealCraftingTableScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, buf.readBlockPos());
    }

    // 私有构造器 - 由客户端构造器调用
    private ArborealCraftingTableScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos) {
        super(ModScreenHandlers.ARBOREAL_CRAFTING_TABLE_SCREEN_HANDLER, syncId);
        // 在客户端，我们创建一个简单的库存，因为客户端不需要真实的方块实体逻辑
        this.inventory = new SimpleInventory(11);
        this.pos = pos;

        checkSize(inventory, 11);
        inventory.onOpen(playerInventory.player);
        setupSlots(playerInventory);
    }

    // 提取槽位设置逻辑到单独方法，避免重复代码
    private void setupSlots(PlayerInventory playerInventory) {
        // 树苗槽 (0)
        this.addSlot(new Slot(inventory, ArborealCraftingTableEntity.SAPLING_SLOT, 24, 35) {
            @Override
            public boolean canInsert(ItemStack stack) {
                // 这里可以添加树苗/树脂的检查逻辑
                return true; // 暂时允许任何物品
            }
        });

        // 合成格 (1-9)
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                this.addSlot(new Slot(inventory,
                        ArborealCraftingTableEntity.CRAFTING_START + col + row * 3,
                        62 + col * 18,
                        17 + row * 18
                ));
            }
        }

        // 输出槽 (10) - 不可放入，只能取出
        this.addSlot(new Slot(inventory, ArborealCraftingTableEntity.OUTPUT_SLOT, 138, 35) {
            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                // 调用消耗材料的方法
                consumeIngredients();
                super.onTakeItem(player, stack);
            }

            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            private void consumeIngredients() {
                // 示例：遍历输入槽位并减少物品数量
                // 槽位0是树苗，1-9是合成格
                for (int i = 0; i <= ArborealCraftingTableEntity.CRAFTING_END; i++) {
                    ItemStack inputStack = inventory.getStack(i);
                    if (!inputStack.isEmpty()) {
                        inputStack.decrement(1); // 减少1个物品
                        // 如果数量为0，则设置为空
                        if (inputStack.getCount() <= 0) {
                            inventory.setStack(i, ItemStack.EMPTY);
                        }
                    }
                }
                // 标记方块实体数据已改变
                if (inventory instanceof ArborealCraftingTableEntity entity) {
                    entity.markDirty();
                }
            }
        });

        // 玩家主物品栏
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory,
                        col + row * 9 + 9,
                        8 + col * 18,
                        84 + row * 18
                ));
            }
        }

        // 玩家快捷栏
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    // 获取方块位置的方法，可能在客户端渲染时有用
    public BlockPos getPos() {
        return pos;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            if (invSlot == ArborealCraftingTableEntity.OUTPUT_SLOT) {
                // 从输出槽移动到玩家物品栏
                if (!this.insertItem(originalStack, 11, 47, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickTransfer(originalStack, newStack);

                // 可选：当取出输出物品时触发合成逻辑更新
                // onContentChanged(inventory);

            } else if (invSlot >= 11 && invSlot < 47) {
                // 从玩家物品栏移动到输入槽
                // 先尝试移动到树苗槽
                if (!this.insertItem(originalStack, 0, 1, false)) {
                    // 如果树苗槽已满，尝试移动到合成格
                    if (!this.insertItem(originalStack, 1, 10, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else {
                // 从输入槽移动到玩家物品栏
                if (!this.insertItem(originalStack, 11, 47, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (originalStack.getCount() == newStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, originalStack);
        }

        return newStack;
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);
        // 当库存内容改变时，可以在这里触发合成检查
        // 例如：checkAndUpdateRecipe();
    }

    // 可选：添加合成检查方法
    /*
    private void checkAndUpdateRecipe() {
        // 在这里实现你的合成逻辑
        // 检查树苗槽和合成格中的物品是否匹配某个配方
        // 如果匹配，在输出槽设置结果
    }
    */

    // 可选：添加清理合成槽的方法
    /*
    private void clearCraftingSlots() {
        for (int i = ArborealCraftingTableEntity.CRAFTING_START; i <= ArborealCraftingTableEntity.CRAFTING_END; i++) {
            inventory.setStack(i, ItemStack.EMPTY);
        }
        inventory.setStack(ArborealCraftingTableEntity.SAPLING_SLOT, ItemStack.EMPTY);
    }
    */
}