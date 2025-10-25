package com.qiuyu.fantasyforest.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

import static com.qiuyu.fantasyforest.effect.VoidEffectHandler.handleVoidDamage;

public class VoidEffect extends StatusEffect {
    public VoidEffect() {
        super(
                StatusEffectCategory.HARMFUL, // 有害效果
                0x3D3D3D
        );
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true; // 每tick都执行更新
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        // 这里处理伤害逻辑，后面会详细实现
        handleVoidDamage(entity);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onRemoved(entity, attributes, amplifier);
        if (!entity.getWorld().isClient()&&entity.getStatusEffect(ModEffects.VOID_EFFECT)==null) {
            // 清理或重置该实体的虚空效果数据
            VoidEffectHandler.cleanupData(entity);
        }
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        handleVoidDamage(entity);
    }
}
