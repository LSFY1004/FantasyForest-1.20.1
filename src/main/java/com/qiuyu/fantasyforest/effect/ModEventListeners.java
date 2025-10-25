package com.qiuyu.fantasyforest.effect;

import com.qiuyu.fantasyforest.item.ModToolMaterials;
import com.qiuyu.fantasyforest.item.customtool.*;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;

import static com.qiuyu.fantasyforest.effect.VoidEffectHandler.cleanupData;

public class ModEventListeners {

    public static void registerEvents() {
        // 使用实体伤害事件，覆盖所有伤害情况（包括怪物攻击玩家）
        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            // 只在服务端执行
            if (entity.getWorld().isClient()) {
                return true; // 继续处理原版伤害逻辑
            }

            // 检查伤害来源是否是生物（包括玩家和怪物）
            if (source.getAttacker() instanceof LivingEntity attacker) {
                // 获取攻击者主手的物品
                ItemStack weapon = attacker.getMainHandStack();

                // 检查武器是否是虚空木武器
                if (isVoidWoodWeapon(weapon)) {
                    // 对受害者应用虚空效果
                    VoidEffectHandler.applyVoidEffect(entity);
                }
            }

            return true; // 继续处理原版伤害逻辑
        });
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, source) -> {
            // 只在服务端执行
            if (entity.getWorld().isClient()) {
                return ;
            }
        if (entity.hasStatusEffect(ModEffects.VOID_EFFECT)) {
            cleanupData(entity);
            entity.clearStatusEffects();
        }
        });
    }

    private static boolean isVoidWoodWeapon(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }

        // 检查所有类型的工具
        if (stack.getItem() instanceof ModSwordItem item) {
            return item.getMaterial() == ModToolMaterials.VOID_WOOD;
        }
        if (stack.getItem() instanceof ModAxeItem item) {
            return item.getMaterial() == ModToolMaterials.VOID_WOOD;
        }
        if (stack.getItem() instanceof ModPickaxeItem item) {
            return item.getMaterial() == ModToolMaterials.VOID_WOOD;
        }
        if (stack.getItem() instanceof ModHoeItem item) {
            return item.getMaterial() == ModToolMaterials.VOID_WOOD;
        }
        if (stack.getItem() instanceof ModShovelItem item) {
            return item.getMaterial() == ModToolMaterials.VOID_WOOD;
        }

        return false;
    }
}