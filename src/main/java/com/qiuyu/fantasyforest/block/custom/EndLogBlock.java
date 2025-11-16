package com.qiuyu.fantasyforest.block.custom;

import com.qiuyu.fantasyforest.block.SuppressorManager;
import com.qiuyu.fantasyforest.item.ModToolMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class EndLogBlock extends PillarBlock {
    // 使用线程安全的ConcurrentHashMap来避免并发问题
    private final Map<BlockPos, MiningData> miningDataMap = new WeakHashMap<>();
    private final Map<BlockPos,BlockPos> transPos = new WeakHashMap<>();
    private static final int INTERRUPT_DELAY = 40; // 2秒 = 40 tick
    private static final int TRANS_DISTANCE = 32;
    public static final BooleanProperty FLASHING = BooleanProperty.of("trans");

    public EndLogBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(FLASHING, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FLASHING);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (!world.isClient()) {
            if (!SuppressorManager.isInSuppressionRange(world, pos)) {
                transEffect((ServerWorld) world,pos);
                if(state.get(FLASHING))world.scheduleBlockTick(pos, this, 5);
            }
        }
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        super.onBlockAdded(state, world, pos, oldState, notify);
        if (!world.isClient()) {
            if (!SuppressorManager.isInSuppressionRange(world, pos)&&state.get(FLASHING)) {
                transEffect((ServerWorld) world,pos);
                world.scheduleBlockTick(pos, this, 5);
            }
        }
    }

    // 检查是否可以被破坏
    private boolean canBeBroken(World world, BlockPos pos, PlayerEntity player) {
        // 检查工具材质
        if (isEndWoodenTool(player.getMainHandStack())) {
            return true;
        }

        // 检查周围是否有激活的抑制器
        return SuppressorManager.isInSuppressionRange(world, pos);
    }

    @Override
    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        super.onBlockBreakStart(state, world, pos, player);
        if (!world.isClient()) {
            // 如果正在闪烁，不处理新的挖掘
            if (state.get(FLASHING)) {
                return;
            }

            // 检查是否需要打断挖掘
            if (!canBeBroken(world, pos, player)) {
                // 记录挖掘开始时间和玩家
                MiningData data = miningDataMap.get(pos);
                if (data == null) {
                    data = new MiningData();
                    miningDataMap.put(pos, data);
                }
                data.startTime = world.getTime();
                data.player = player;
                data.isMining = true;

                // 安排一个任务在指定时间后检查并打断挖掘
                world.scheduleBlockTick(pos, this, INTERRUPT_DELAY);
            } else {
                // 如果可以正常挖掘，清除挖掘数据
                miningDataMap.remove(pos);
            }
        }
    }

    private void interruptMining(ServerWorld world, BlockPos pos) {
        transEffect(world, pos);
        resetMiningProgress(world, pos);
        createFlashEffect(world, pos);
    }

    private void transEffect(ServerWorld world, BlockPos pos) {
        world.playSound(
                null,
                pos,
                SoundEvents.ENTITY_ENDERMAN_TELEPORT,
                SoundCategory.BLOCKS,
                1.0F,
                1.0F
        );
        for (int i = 0; i < 20; i++) {
            double x = pos.getX() + 0.5 + (world.random.nextDouble() - 0.5) * 2;
            double y = pos.getY() + 0.5 + (world.random.nextDouble() - 0.5) * 2;
            double z = pos.getZ() + 0.5 + (world.random.nextDouble() - 0.5) * 2;

            world.spawnParticles(
                    ParticleTypes.PORTAL,
                    x, y, z,
                    1,
                    0, 0, 0,
                    0.1
            );
        }
    }

    private void resetMiningProgress(World world, BlockPos pos) {
        for (PlayerEntity player : world.getPlayers()) {
            if (player.getBlockPos().getSquaredDistance(pos) <= 25) {
                player.resetLastAttackedTicks();
                world.updateListeners(pos, world.getBlockState(pos), world.getBlockState(pos), Block.NOTIFY_ALL);
            }
        }
    }

    // 创建闪烁效果（方块短暂消失然后恢复）
    private void createFlashEffect(ServerWorld world, BlockPos pos) {
        BlockState originalState = world.getBlockState(pos);
        if (originalState.getBlock() != this) return;

        Random random = world.random;
        BlockPos newPos = findTeleportPosition(world, pos, random);

        if (newPos != null) {
            // 在新位置放置方块
            world.setBlockState(newPos, originalState.with(FLASHING, true), Block.NOTIFY_ALL);
            // 播放传送效果
            transEffect(world, newPos);
            transPos.put(newPos,pos);
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        } else {
            // 没找到合适位置，恢复原状
            world.setBlockState(pos, originalState.with(FLASHING, false), Block.NOTIFY_ALL);
        }
    }

    // 改进的位置查找方法
    private BlockPos findTeleportPosition(World world, BlockPos center, Random random) {
        for (int attempt = 0; attempt < 100; attempt++) {
            BlockPos testPos = center.add(
                    random.nextBetween(-TRANS_DISTANCE, TRANS_DISTANCE),
                    random.nextBetween(-TRANS_DISTANCE, TRANS_DISTANCE),
                    random.nextBetween(-TRANS_DISTANCE, TRANS_DISTANCE)
            );

            // 确保位置在世界范围内且可替换
            if (world.isInBuildLimit(testPos) &&
                    world.getBlockState(testPos).isReplaceable() &&
                    !testPos.equals(center)) {
                return testPos;
            }
        }

        // 如果随机搜索失败，尝试系统性的搜索
        for (int y = -TRANS_DISTANCE; y <= TRANS_DISTANCE; y++) {
            for (int x = -TRANS_DISTANCE; x <= TRANS_DISTANCE; x++) {
                for (int z = -TRANS_DISTANCE; z <= TRANS_DISTANCE; z++) {
                    BlockPos testPos = center.add(x, y, z);
                    if (world.isInBuildLimit(testPos) &&
                            world.getBlockState(testPos).isReplaceable() &&
                            !testPos.equals(center)) {
                        return testPos;
                    }
                }
            }
        }

        return null; // 没找到合适位置
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.scheduledTick(state, world, pos, random);

        // 处理闪烁恢复
        if (state.get(FLASHING)) {
            world.setBlockState(transPos.get(pos),getDefaultState().with(AXIS,state.get(AXIS)));
            transPos.remove(pos);
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
            return;
        }

        // 检查挖掘超时 - 只有当玩家仍在挖掘时才触发
        MiningData data = miningDataMap.get(pos);
        if (data != null && data.isMining && world.getTime() - data.startTime >= INTERRUPT_DELAY) {
            if (!SuppressorManager.isInSuppressionRange(world, pos)) {
                interruptMining(world, pos);
            }
            miningDataMap.remove(pos);
        } else if (data != null && !data.isMining) {
            // 玩家已停止挖掘，清理数据
            miningDataMap.remove(pos);
        }
    }

    @Override
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience) {
        // 清理数据
        miningDataMap.remove(pos);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        // 如果正在闪烁，不允许破坏
        if (state.get(FLASHING)) {
            return;
        }

        // 检查破坏条件
        if (!canBeBroken(world, pos, player)) {
            if (!world.isClient()) {
                interruptMining((ServerWorld) world, pos);
            }
            return;
        }
        if(!isEndWoodenTool(player.getMainHandStack()))playBreakEffects(world, pos);
        super.onBreak(world, pos, state, player);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        // 如果正在闪烁，不允许破坏和掉落
        if (state.get(FLASHING)) {
            return;
        }

        // 检查破坏条件
        if (!canBeBroken(world, pos, player)) {
            if (!world.isClient()) {
                interruptMining((ServerWorld) world, pos);
            }
            return;
        }

        // 检查掉落条件
        boolean shouldDrop = isEndWoodenTool(stack) ||
                (aboveDiamondAxe(stack) && SuppressorManager.isInSuppressionRange(world, pos));
        super.afterBreak(world, player, pos, state, blockEntity, stack);
        if (shouldDrop&&!player.isCreative()) {
            // 手动掉落物品
            ItemScatterer.spawn(world, pos, DefaultedList.copyOf(ItemStack.EMPTY, new ItemStack(this)));
        }
    }

    private boolean isEndWoodenTool(ItemStack stack) {
        if (stack.getItem() instanceof ToolItem toolItem) {
            return toolItem.getMaterial() == ModToolMaterials.END_WOODEN;
        }
        return false;
    }

    private boolean aboveDiamondAxe(ItemStack stack) {
        if (stack.getItem() instanceof ToolItem toolItem) {
            return toolItem.getMaterial().getMiningLevel() >= 3 && toolItem instanceof AxeItem;
        }
        return false;
    }

    // 新增：当抑制器状态变化时通知所有末影木检查
    public static void onSuppressorStateChange(World world) {
        if (world.isClient()) return;
        // 这里可以添加逻辑来优化性能，比如只更新特定区域的末影木
    }

    private void playBreakEffects(World world, BlockPos pos) {
        if (!world.isClient()) {
            // 播放破坏音效
            world.playSound(
                    null,
                    pos,
                    SoundEvents.BLOCK_WOOD_BREAK,
                    SoundCategory.BLOCKS,
                    1.0F,
                    1.0F
            );

            // 生成破坏粒子
            if (world instanceof ServerWorld serverWorld) {
                BlockState state = this.getDefaultState(); // 获取当前方块的默认状态
                // 创建方块粒子效果
                BlockStateParticleEffect particleEffect = new BlockStateParticleEffect(ParticleTypes.BLOCK, state);

                Random random = world.getRandom();
                for (int i = 0; i < 20; ++i) { // 生成多个粒子
                    double x = pos.getX() + random.nextDouble();
                    double y = pos.getY() + random.nextDouble();
                    double z = pos.getZ() + random.nextDouble();
                    double velocityX = random.nextGaussian() * 0.02;
                    double velocityY = random.nextGaussian() * 0.02;
                    double velocityZ = random.nextGaussian() * 0.02;

                    serverWorld.spawnParticles(particleEffect, x, y, z, 1, velocityX, velocityY, velocityZ, 0.05);
                }
            }
        }
    }
    // 内部类用于存储挖掘数据
    private static class MiningData {
        long startTime;
        PlayerEntity player;
        boolean isMining = false;
    }
}