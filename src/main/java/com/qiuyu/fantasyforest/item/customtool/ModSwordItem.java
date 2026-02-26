package com.qiuyu.fantasyforest.item.customtool;

import com.qiuyu.fantasyforest.FantasyForest;
import com.qiuyu.fantasyforest.KeyHelper;
import com.qiuyu.fantasyforest.effect.ModEffects;
import com.qiuyu.fantasyforest.effect.VoidEffectHandler;
import com.qiuyu.fantasyforest.item.ModToolMaterials;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class ModSwordItem extends SwordItem {
    private final ModToolMaterials material;

    public ModSwordItem(ModToolMaterials material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
        this.material = material;
    }

    public static String info(ModToolMaterials material) {
        String s = KeyHelper.isShiftKeyDown() ? ".shift" : "";
        switch (material) {
            case PERMEAROMA -> {
                return "tooltip.fantasyforest.permearoma" + s;
            }
            case END_WOODEN -> {
                return "tooltip.fantasyforest.end_wood" + s;
            }
            case MUTATED_END_WOODEN -> {
                return "tooltip.fantasyforest.mutated_end_wood" + s;
            }
            case VOID_WOODEN -> {
                return "tooltip.fantasyforest.void_wood" + s;
            }
            default -> {
                return "";
            }
        }
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        PlayerEntity player = context.getPlayer();
        Hand hand = context.getHand();
        if(isSameWood(state)&&player.isSneaking()){
//            if (!world.canPlayerBreakBlock(pos, player)) {
//                return ActionResult.PASS;
//            }

            // 执行破坏与拾取（仅在服务端）
            if (world instanceof ServerWorld) {
                player.swingHand(hand);

                // 1. 破坏方块，但不生成掉落物实体（第二个参数 false）
                world.breakBlock(pos, false, player);

                // 2. 获取方块掉落物
                List<ItemStack> drops = Collections.singletonList(new ItemStack(state.getBlock().asItem(), 1));

                // 3. 尝试将掉落物直接加入玩家背包，多余的掉落在地
                for (ItemStack drop : drops) {
                    if (!player.getInventory().insertStack(drop)) {
                        player.dropItem(drop, false); // false 表示不延迟拾取
                    }
                }
            }
            return ActionResult.SUCCESS;
        }
        return super.useOnBlock(context);
    }

    private boolean isSameWood(BlockState state) {
        String id = material.name().toLowerCase() + "_blocks";
        return state.isIn(TagKey.of(RegistryKeys.BLOCK,new Identifier(FantasyForest.MOD_ID,id)));
    }

    public static void hitEffect(ModToolMaterials material, LivingEntity target){
        switch (material){
            case VOID_WOODEN -> VoidEffectHandler.applyVoidEffect(target);
            case END_WOODEN -> target.addStatusEffect(new StatusEffectInstance(
                    ModEffects.SPATIAL_CONSTRAINT,
                    80,
                    0,
                    false, // 不显示粒子
                    true   // 显示图标
            ));
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (!KeyHelper.isShiftKeyDown()) tooltip.add(Text.translatable("tooltip.arboreal"));
        else tooltip.add(Text.translatable("tooltip.arboreal.shift"));
        if(material.isSpecial())tooltip.add(Text.translatable(info(material)));
        if (!KeyHelper.isShiftKeyDown()) tooltip.add(Text.translatable("tooltip.fantasyforest.more"));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        hitEffect(material, target);
        return super.postHit(stack, target, attacker);
    }
}
