package com.qiuyu.fantasyforest.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class PhosphorBlock extends Block {
    public static final IntProperty LIGHT_STATE = IntProperty.of("light_state", 0, 2);
    public static final BooleanProperty POWERED = Properties.POWERED;
    private static final int SCHEDULED_TICK_DELAY = 2; // 2刻延迟防止瞬时峰值

    public PhosphorBlock(Settings settings) {
        super(settings.luminance(state -> {
            int lightState = state.get(LIGHT_STATE);
            return lightState == 2 ? 15 : (lightState == 1 ? 7 : 0);
        }));
        this.setDefaultState(this.getStateManager().getDefaultState()
                .with(LIGHT_STATE, 2)
                .with(POWERED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIGHT_STATE, POWERED);
    }

    // 修复1：使用正确的状态更新方法
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction,
                                                BlockState neighborState, WorldAccess world,
                                                BlockPos pos, BlockPos neighborPos) {
        if (world.isClient()) return state;

        boolean isPowered = world.getReceivedRedstonePower(pos) > 0;
        boolean wasPowered = state.get(POWERED);

        // 只在上升沿触发状态切换
        if (isPowered && !wasPowered) {
            // 延迟执行防止瞬时峰值
            if (world instanceof ServerWorld serverWorld) {
                serverWorld.scheduleBlockTick(pos, this, SCHEDULED_TICK_DELAY);
            }
        }

        return state.with(POWERED, isPowered);
    }

    // 修复2：延迟状态切换
    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int currentState = state.get(LIGHT_STATE);
        int nextState = (currentState + 1) % 3;
        world.setBlockState(pos, state.with(LIGHT_STATE, nextState), Block.NOTIFY_ALL);
    }

    // 修复3：使用正确的比较器API
    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return state.get(LIGHT_STATE); // 0,1,2 对应输出0,1,2
    }
}