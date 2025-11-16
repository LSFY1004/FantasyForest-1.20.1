package com.qiuyu.fantasyforest.item.customtool;

import com.qiuyu.fantasyforest.KeyHelper;
import com.qiuyu.fantasyforest.effect.ModEffects;
import com.qiuyu.fantasyforest.effect.VoidEffectHandler;
import com.qiuyu.fantasyforest.item.ModToolMaterials;
import com.qiuyu.fantasyforest.tag.ModBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

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

//    @Override
//    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
//        if(isSameWood(stack,state))
//        return super.getMiningSpeedMultiplier(stack, state);
//    }
//
//    public boolean isSameWood(ItemStack stack, BlockState state) {
//        return state.isIn(ModBlockTags)
//    }

    public static void hitEffect(ModToolMaterials material, LivingEntity target){
        switch (material){
            case VOID_WOODEN -> {
                VoidEffectHandler.applyVoidEffect(target);
            }
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
