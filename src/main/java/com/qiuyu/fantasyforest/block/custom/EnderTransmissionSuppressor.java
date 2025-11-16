package com.qiuyu.fantasyforest.block.custom;

import com.qiuyu.fantasyforest.block.SuppressorManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class EnderTransmissionSuppressor extends Block {
    public static final BooleanProperty POWERED = Properties.POWERED;
    public static final BooleanProperty LIT = Properties.LIT;

    // 抑制器的作用范围
    public static final int SUPPRESSION_RANGE = 30;

    public EnderTransmissionSuppressor(Settings settings) {
        super(settings.luminance(state-> {
            boolean l = state.get(LIT);
            return l ? 15 : 5;
        }));
        this.setDefaultState(this.getStateManager().getDefaultState()
                .with(POWERED, false)
                .with(LIT, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED, LIT);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        super.onBlockAdded(state, world, pos, oldState, notify);
        if (!world.isClient() && isActive(state)) {
            SuppressorManager.addSuppressor(world, pos);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            // 切换激活状态
            boolean newLitState = !state.get(LIT);
            world.setBlockState(pos, state.with(LIT, newLitState), Block.NOTIFY_ALL);

            // 发布状态变化事件
            publishStateChange(world, pos, newLitState || state.get(POWERED));
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!world.isClient()) {
            boolean isReceivingPower = world.isReceivingRedstonePower(pos);
            boolean currentlyPowered = state.get(POWERED);

            if (isReceivingPower != currentlyPowered) {
                // 红石信号变化
                world.setBlockState(pos, state.with(POWERED, isReceivingPower), Block.NOTIFY_ALL);

                // 只有当实际激活状态改变时才发布事件
                boolean oldActiveState = isActive(state);
                boolean newActiveState = isActive(state.with(POWERED, isReceivingPower));

                if (oldActiveState != newActiveState) {
                    publishStateChange(world, pos, newActiveState);
                }
            }
        }
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.afterBreak(world, player, pos, state, blockEntity, tool);
        if (!world.isClient()) {
            SuppressorManager.removeSuppressor(world, pos);
        }
    }

    // 发布状态变化事件
    private void publishStateChange(World world, BlockPos pos, boolean isActive) {
        if (world instanceof ServerWorld) {
            if (isActive) {
                SuppressorManager.addSuppressor(world, pos);
            } else {
                SuppressorManager.removeSuppressor(world, pos);
            }
            // 通知末影木抑制器状态变化
            EndLogBlock.onSuppressorStateChange(world);
        }
    }

    // 获取抑制器是否激活
    public static boolean isActive(BlockState state) {
        return state.get(POWERED) || state.get(LIT);
    }

    // 比较器输出
    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return isActive(state) ? 1 : 0;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock()) && !world.isClient() && isActive(state)) {
            SuppressorManager.removeSuppressor(world, pos);
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }
}