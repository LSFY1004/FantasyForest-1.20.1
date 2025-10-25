package com.qiuyu.fantasyforest.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VoidEffectHandler {
    private static final Map<UUID, VoidEffectData> effectDataMap = new HashMap<>();

    // 存储每个实体的虚空效果数据
    public static class VoidEffectData {
        public int totalDuration = 0;      // 总持续时间（tick）
        public int damageLevel = 0;        // 当前伤害等级
        public int lastDamageTick = 0;     // 上次造成伤害的tick

        public VoidEffectData() {}
        public void reset(){
            totalDuration=0;
            damageLevel=0;
            lastDamageTick=0;
        }
    }

    public static void applyVoidEffect(LivingEntity entity) {
        // 获取或创建效果数据
        UUID entityId = entity.getUuid();
        effectDataMap.computeIfAbsent(entityId, k -> new VoidEffectData());

        // 应用效果2秒（40 tick）
        entity.addStatusEffect(new StatusEffectInstance(
                ModEffects.VOID_EFFECT, // 你的自定义效果
                40, // 2秒 = 40 tick
                0,
                false, // 不显示粒子
                true   // 显示图标
        ));
    }

    public static void handleVoidDamage(LivingEntity entity) {
        // 只在服务端执行
        if (entity.getWorld().isClient()) {
            return;
        }

        // 检查实体是否仍然拥有虚空效果
        StatusEffectInstance effect = entity.getStatusEffect(ModEffects.VOID_EFFECT);
        if (effect == null) {
            // 效果已消失，清理数据
            cleanupData(entity);
            return;
        }

        UUID entityId = entity.getUuid();
        VoidEffectData data = effectDataMap.computeIfAbsent(entityId, k -> new VoidEffectData());

        data.totalDuration++;
        calculateDamageLevel(data);
        applyDamage(entity, data);
        System.out.println(data.damageLevel);System.out.println(data.totalDuration);System.out.println(data.lastDamageTick);

        // 最终检查效果是否仍然存在（可能在applyDamage中实体死亡等）
        if (!entity.hasStatusEffect(ModEffects.VOID_EFFECT)) {
            cleanupData(entity);
        }
    }

    private static void calculateDamageLevel(VoidEffectData data) {
        // 总持续时间（tick）
        int totalTicks = data.totalDuration;

        if (totalTicks < 60) { // 前3秒：无伤害
            data.damageLevel = 0;
        } else if (totalTicks < 120) { // 3-6秒：1点伤害
            data.damageLevel = 1;
        } else if (totalTicks < 180) { // 6-9秒：2点伤害
            data.damageLevel = 2;
        } else if (totalTicks < 240) { // 9-12秒：3点伤害
            data.damageLevel = 3;
        } else { // 12秒以上：4点伤害
            data.damageLevel = 4;
        }
    }

    private static void applyDamage(LivingEntity entity, VoidEffectData data) {
        // 确保在正确的时机造成伤害
        if (data.damageLevel > 0) {
            // 检查是否到了该造成伤害的时间点
            boolean shouldDamage = false;

            if (data.totalDuration >= 60) { // 3秒后开始伤害
                // 每0.5秒（10 tick）造成一次伤害
                int ticksSinceLastDamage = data.totalDuration - data.lastDamageTick;
                if (ticksSinceLastDamage >= 10) {
                    shouldDamage = true;
                }
            }

            if (shouldDamage) {
                DamageSource voidDamageSource = ModDamageSources.of(entity.getWorld(), ModDamageSources.VOID_DAMAGE_TYPE);
                entity.damage(voidDamageSource, data.damageLevel);
                data.lastDamageTick = data.totalDuration;
            }
        }
    }

    // 清理数据
    public static void cleanupData(LivingEntity entity) {
        effectDataMap.remove(entity.getUuid());
        VoidEffectData data = new VoidEffectData();
        data.reset();
    }
}
