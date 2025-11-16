package com.qiuyu.fantasyforest.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SpatialConstraint extends StatusEffect {

    // 存储每个实体的记录位置和维度
    private static final Map<UUID, RecordedPosition> positionRecords = new HashMap<>();
    private static final Map<UUID, Integer> particleTimers = new HashMap<>();

    // 基础传送距离阈值
    private static final double BASE_TELEPORT_DISTANCE = 5.0;
    private static final int PARTICLE_INTERVAL = 20;

    protected SpatialConstraint(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true; // 每tick都执行更新
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        // 只在服务器端执行逻辑
        if (entity.getWorld().isClient) {
            return;
        }

        UUID entityId = entity.getUuid();

        // 检查是否有记录的位置
        if (!positionRecords.containsKey(entityId)) {
            positionRecords.put(entityId,new RecordedPosition(entity.getPos(),entity.getWorld().getRegistryKey().getValue().toString()));
        }

        RecordedPosition recordedPos = positionRecords.get(entityId);
        Vec3d currentPos = entity.getPos();
        int timer = particleTimers.getOrDefault(entityId, 0) + 1;
        particleTimers.put(entityId, timer);

        // 检查是否到达粒子生成间隔
        if (timer >= PARTICLE_INTERVAL) {
            particleTimers.put(entityId, 0);
            spawnRecordingParticles((ServerWorld) entity.getWorld(), recordedPos.position);
        }
        // 检查是否在同一维度
        String currentDimension = entity.getWorld().getRegistryKey().getValue().toString();
        if (!currentDimension.equals(recordedPos.dimension)) {
            return;
        }
        // 计算与记录位置的距离
        double distance = currentPos.distanceTo(recordedPos.position);

        // 计算传送阈值（根据放大器等级调整）
        double teleportThreshold = BASE_TELEPORT_DISTANCE * (1 + amplifier * 0.5);

        // 如果超过阈值，传送回记录位置
        if (distance > teleportThreshold) {
            teleportToRecordedPosition(entity, recordedPos);
        }
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onRemoved(entity, attributes, amplifier);

        // 移除效果时清除记录的位置
        positionRecords.remove(entity.getUuid());

//        NbtCompound nbt = entity.writeNbt(new NbtCompound());
//        nbt.put("const_pos",null);
//        entity.readNbt(nbt);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);

        recordCurrentPosition(entity);

        // 可以在应用时添加一些视觉或声音效果
        if (!entity.getWorld().isClient) {
            // 这里可以添加粒子效果或声音
            spawnRecordingParticles((ServerWorld) entity.getWorld(),entity.getPos());
            // entity.getWorld().playSound(null, entity.getBlockPos(), SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, ...);
        }
    }

    /**
     * 记录实体的当前位置
     */
    private void recordCurrentPosition(LivingEntity entity) {
        UUID entityId = entity.getUuid();
        Vec3d currentPos = entity.getPos();
        World world = entity.getWorld();

        // 获取维度标识
        String dimension = world.getRegistryKey().getValue().toString();

        positionRecords.put(entityId, new RecordedPosition(currentPos, dimension));

        // 可选：添加记录位置的视觉反馈
        if (!world.isClient) {
            spawnRecordingParticles((ServerWorld) world, currentPos);
        }
    }

    /**
     * 将实体传送回记录的位置
     */
    private void teleportToRecordedPosition(LivingEntity entity, RecordedPosition recordedPos) {
        // 检查是否在同一维度
        String currentDimension = entity.getWorld().getRegistryKey().getValue().toString();

        if (!currentDimension.equals(recordedPos.dimension)) {
            // 如果不在同一维度，不进行传送，但可以更新记录位置到当前维度
            recordCurrentPosition(entity);
            return;
        }

        // 执行传送
        entity.teleport(recordedPos.position.x, recordedPos.position.y, recordedPos.position.z);

        // 传送后的视觉和声音效果
        if (!entity.getWorld().isClient) {

            ServerWorld world= (ServerWorld) entity.getWorld();
            world.spawnParticles(ParticleTypes.PORTAL,
                    recordedPos.position.x, recordedPos.position.y, recordedPos.position.z,
                    15, 0.5, 0.5, 0.5, 0.1);
            entity.getWorld().playSound(null, entity.getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS);
        }
    }

    /**
     * 生成记录位置时的粒子效果
     */
    private void spawnRecordingParticles(ServerWorld world, Vec3d pos) {
        // 使用末影粒子效果
        world.spawnParticles(ParticleTypes.PORTAL,
                pos.x, pos.y + 1, pos.z,
                15, 0.5, 0.5, 0.5, 0.1);
        world.spawnParticles(ParticleTypes.GLOW,
                pos.x, pos.y + 1, pos.z,
                10, 0.5, 0.5, 0.5, 0.1);
    }

    /**
     * 内部类：记录的位置信息
     */
    private record RecordedPosition(Vec3d position, String dimension) {
    }

    // 可选：添加一个方法来获取某个实体的记录位置（用于调试）
    public static Vec3d getRecordedPosition(UUID entityId) {
        RecordedPosition record = positionRecords.get(entityId);
        return record != null ? record.position : null;
    }
}