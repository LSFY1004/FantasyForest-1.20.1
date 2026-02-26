package com.qiuyu.fantasyforest.block;

import com.qiuyu.fantasyforest.block.custom.EnderTransmissionSuppressor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.PersistentState;
import net.minecraft.world.World;

import java.util.*;

public class SuppressorManager {
    private static final String PERSISTENT_ID = "fantasyforest_suppressors";
    // 保存每个世界的 SuppressorData 实例
    private static final Map<World, SuppressorData> WORLD_DATA_MAP = new HashMap<>();

    // 内部数据类，继承 PersistentState
    public static class SuppressorData extends PersistentState {
        private final Set<BlockPos> activeSuppressors = new HashSet<>();

        @Override
        public NbtCompound writeNbt(NbtCompound nbt) {
            // 保存时，将 Set<BlockPos> 序列化为 NbtList
            NbtList posList = new NbtList();
            for (BlockPos pos : this.activeSuppressors) {
                NbtCompound posNbt = new NbtCompound();
                posNbt.putInt("x", pos.getX());
                posNbt.putInt("y", pos.getY());
                posNbt.putInt("z", pos.getZ());
                posList.add(posNbt);
            }
            nbt.put("suppressors", posList);
            return nbt;
        }

        // 静态方法，用于从 NBT 数据创建 SuppressorData 实例
        public static SuppressorData fromNbt(NbtCompound nbt) {
            SuppressorData data = new SuppressorData();
            NbtList posList = nbt.getList("suppressors", 10); // 10 代表 COMPOUND 类型
            for (int i = 0; i < posList.size(); i++) {
                NbtCompound posNbt = posList.getCompound(i);
                BlockPos pos = new BlockPos(
                        posNbt.getInt("x"),
                        posNbt.getInt("y"),
                        posNbt.getInt("z")
                );
                data.activeSuppressors.add(pos);
            }
            return data;
        }
    }

    // 获取或创建指定世界的 SuppressorData
    private static SuppressorData getData(World world) {
        if (!(world instanceof ServerWorld serverWorld)) {
            throw new RuntimeException("SuppressorManager should not be used on client side.");
        }

        // 尝试从缓存获取
        SuppressorData data = WORLD_DATA_MAP.get(world);
        if (data == null) {
            // 从持久化存储中加载，如果不存在则创建新的
            data = serverWorld.getPersistentStateManager()
                    .getOrCreate(SuppressorData::fromNbt, SuppressorData::new, PERSISTENT_ID);
            WORLD_DATA_MAP.put(world, data);
        }
        return data;
    }

    public static void addSuppressor(World world, BlockPos pos) {
        BlockPos immutablePos = pos.toImmutable();
        SuppressorData data = getData(world);
        if (data.activeSuppressors.add(immutablePos)) {
            data.markDirty(); // 标记数据为脏，以便游戏自动保存
        }
    }

    public static void removeSuppressor(World world, BlockPos pos) {
        SuppressorData data = getData(world);
        if (data.activeSuppressors.remove(pos)) {
            data.markDirty(); // 标记数据为脏，以便游戏自动保存
        }
    }

    public static boolean isInSuppressionRange(World world, BlockPos endLogPos) {
        // 确保我们获取的是最新的数据，而不是缓存中可能过时的数据
        SuppressorData data = getData(world);
        if (data.activeSuppressors.isEmpty()) {
            return false;
        }

        for (BlockPos suppressorPos : data.activeSuppressors) {
            double distanceSquared = endLogPos.getSquaredDistance(suppressorPos);
            if (distanceSquared <= EnderTransmissionSuppressor.SUPPRESSION_RANGE * EnderTransmissionSuppressor.SUPPRESSION_RANGE) {
                return true;
            }
        }
        return false;
    }

    public static void restoreSuppressorStates(World world) {
        if (!(world instanceof ServerWorld)) {
            return;
        }

        SuppressorData data = getData(world);
        if (data.activeSuppressors.isEmpty()) {
            return;
        }

        // 遍历所有保存的抑制器位置，恢复方块状态
        for (BlockPos pos : data.activeSuppressors) {
            BlockState currentState = world.getBlockState(pos);

            // 检查该位置是否还是抑制器方块
            if (currentState.getBlock() instanceof EnderTransmissionSuppressor) {
                // 如果抑制器当前不活跃，但应该在活跃状态，则激活它
                if (!EnderTransmissionSuppressor.isActive(currentState)) {
                    // 设置为激活状态（LIT=true）
                    BlockState newState = currentState.with(EnderTransmissionSuppressor.LIT, true);
                    world.setBlockState(pos, newState, Block.NOTIFY_ALL);
                }
            } else {
                // 如果该位置不再是抑制器，从数据中移除
                data.activeSuppressors.remove(pos);
                data.markDirty();
            }
        }
    }
}