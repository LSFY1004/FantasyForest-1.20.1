package com.qiuyu.fantasyforest.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

import java.util.Random;

public class CupidKiss extends StatusEffect {
    // 颜色使用粉紫色 (RGB: 230,168,215)
    public CupidKiss() {
        super(StatusEffectCategory.NEUTRAL, 0xE6A8D7);
    }

    // 每 tick 都调用 applyUpdateEffect
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.getWorld().isClient()) return;

        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 10, amplifier+1, true, false));

        // 对怪物：失去索敌
        if (entity instanceof MobEntity mob) {
            mob.setTarget(null);
            if (mob.getAttacker() instanceof LivingEntity) {
                mob.setAttacker(null);
            }
        }
        if (!entity.getWorld().isClient) {
            // 获取实体的世界和位置
            Random random = new Random();
            if(random.nextInt(15)!=0)return;
            ServerWorld world = (ServerWorld) entity.getWorld();
            double x = entity.getX();
            double y = entity.getY() + entity.getHeight() / 2; // 在身体中心生成，效果更好
            double z = entity.getZ();

            // 生成爱心粒子！核心步骤
            world.spawnParticles(ParticleTypes.HEART, x, y, z, 4,0.5,0.5,0.5,0.5);
        }

    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);
        if (entity.getWorld().isClient()) return;

        // 对动物：立即进入 Love 模式（心形粒子，能与同类繁殖）
        if (entity instanceof AnimalEntity animal && !animal.isBaby() && animal.canEat()) {
            // 设置爱意持续600 tick (30秒)
            animal.setLoveTicks(600);
        }
    }
}