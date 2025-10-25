package com.qiuyu.fantasyforest.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class StickBlock extends Block {

    // 定义方块状态属性
    public static final EnumProperty<Direction.Axis> AXIS = Properties.AXIS;
    public static final BooleanProperty NORTH = BooleanProperty.of("north");
    public static final BooleanProperty EAST = BooleanProperty.of("east");
    public static final BooleanProperty SOUTH = BooleanProperty.of("south");
    public static final BooleanProperty WEST = BooleanProperty.of("west");
    public static final BooleanProperty UP = BooleanProperty.of("up");
    public static final BooleanProperty DOWN = BooleanProperty.of("down");

    // 基础中心部分 (2x2x2) - 在所有轴向上都是相同的
    private static final VoxelShape BASE_SHAPE = Block.createCuboidShape(7, 7, 7, 9, 9, 9);

    // 各个方向的延伸部分 (2x2x7)
    private static final VoxelShape NORTH_EXTENSION = Block.createCuboidShape(7, 7, 0, 9, 9, 7);
    private static final VoxelShape SOUTH_EXTENSION = Block.createCuboidShape(7, 7, 9, 9, 9, 16);
    private static final VoxelShape EAST_EXTENSION = Block.createCuboidShape(9, 7, 7, 16, 9, 9);
    private static final VoxelShape WEST_EXTENSION = Block.createCuboidShape(0, 7, 7, 7, 9, 9);
    private static final VoxelShape UP_EXTENSION = Block.createCuboidShape(7, 9, 7, 9, 16, 9);
    private static final VoxelShape DOWN_EXTENSION = Block.createCuboidShape(7, 0, 7, 9, 7, 9);

    public StickBlock(Settings settings) {
        super(settings);
        // 设置默认状态
        this.setDefaultState(this.getStateManager().getDefaultState()
                .with(AXIS, Direction.Axis.Y)
                .with(NORTH, false)
                .with(EAST, false)
                .with(SOUTH, false)
                .with(WEST, false)
                .with(UP, true)
                .with(DOWN, true));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AXIS, NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }

    // 放置时确定朝向
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        // 根据玩家点击的面确定轴向（垂直于点击的面）
        Direction clickedFace = ctx.getSide();
        Direction.Axis axis = switch (clickedFace) {
            case UP, DOWN -> Direction.Axis.Y; // 竖着放置
            case NORTH, SOUTH -> Direction.Axis.Z; // 横着放置，沿着Z轴
            case EAST, WEST -> Direction.Axis.X; // 横着放置，沿着X轴
        };

        // 获取初始状态并设置轴向
        BlockState state = this.getDefaultState().with(AXIS, axis);

        // 初始放置时先计算一次连接状态
        return updateConnectionStates(state, ctx.getWorld(), ctx.getBlockPos());
    }

    private BlockState setDefaultExtension(BlockState state) {
        Direction.Axis axis = state.get(AXIS);

        // 根据轴向设置默认延伸
        switch (axis) {
            case X -> {
                return state.with(WEST, true).with(EAST, true)
                        .with(NORTH, false).with(SOUTH, false)
                        .with(UP, false).with(DOWN, false);
            }
            case Z -> {
                return state.with(NORTH, true).with(SOUTH, true)
                        .with(EAST, false).with(WEST, false)
                        .with(UP, false).with(DOWN, false);
            }
            case Y -> {
                return state.with(UP, true).with(DOWN, true)
                        .with(NORTH, false).with(EAST, false)
                        .with(SOUTH, false).with(WEST, false);
            }
        }
        return state;
    }
    // 更新连接状态的核心方法
    private BlockState updateConnectionStates(BlockState state, World world, BlockPos pos) {
        state.get(AXIS);

        // 检查六个方向是否可以连接
        boolean north = canConnectTo(world, pos.north(), pos, Direction.NORTH);
        boolean east = canConnectTo(world, pos.east(), pos, Direction.EAST);
        boolean south = canConnectTo(world, pos.south(), pos, Direction.SOUTH);
        boolean west = canConnectTo(world, pos.west(), pos, Direction.WEST);
        boolean up = canConnectTo(world, pos.up(), pos, Direction.UP);
        boolean down = canConnectTo(world, pos.down(), pos, Direction.DOWN);

        // 计算连接数量
        int connectionCount = 0;
        if (north) connectionCount++;
        if (east) connectionCount++;
        if (south) connectionCount++;
        if (west) connectionCount++;
        if (up) connectionCount++;
        if (down) connectionCount++;

        // 如果有连接，使用实际连接状态
        if (connectionCount > 0) {
            return state
                    .with(NORTH, north)
                    .with(EAST, east)
                    .with(SOUTH, south)
                    .with(WEST, west)
                    .with(UP, up)
                    .with(DOWN, down);
        } else {
            // 没有连接时，恢复到基于轴向的默认状态
            return setDefaultExtension(state);
        }
    }

    // 判断是否可以连接到目标方块
    private boolean canConnectTo(World world, BlockPos neighborPos, BlockPos pos,Direction direction) {
        BlockState neighborState = world.getBlockState(neighborPos);

        return neighborState.getBlock() instanceof StickBlock ||
                neighborState.isIn(BlockTags.PLANKS) ||
                neighborState.isIn(BlockTags.LOGS) ||
                neighborState.isIn(BlockTags.WOODEN_SLABS) ||
                neighborState.isIn(BlockTags.WOODEN_STAIRS) ||
                neighborState.isOf(Blocks.END_ROD)||
                neighborState.isSideSolid(world,neighborPos,direction.getOpposite(),SideShapeType.FULL)||
                ((neighborState.isOf(Blocks.LANTERN)||neighborState.isOf(Blocks.SOUL_LANTERN)||neighborState.isIn(BlockTags.WOODEN_FENCES))
                        && neighborPos.getY() != pos.getY())||
                (neighborState.isOf(Blocks.TORCH)||neighborState.isOf(Blocks.SOUL_TORCH))
                        &&neighborPos.getY()>pos.getY();
    }

    // 邻居方块更新时重新计算连接状态
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (neighborState.isAir()||neighborState.isOf(Blocks.TORCH)||neighborState.isOf(Blocks.SOUL_TORCH)||neighborState.isOf(Blocks.SOUL_LANTERN)||neighborState.isOf(Blocks.LANTERN)) return state;
        return updateConnectionStates(state, (World) world, pos);
    }

    // 右键刷新状态功能
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack mainHandStack = player.getMainHandStack();
        ItemStack offHandStack = player.getOffHandStack();

        // 检查条件：主手空手且副手拿着同类物品
        if (mainHandStack.isEmpty() && offHandStack.getItem() == this.asItem()) {
            if (!world.isClient) {
                // 强制更新连接状态
                BlockState updatedState = updateConnectionStates(state, world, pos);

                // 如果状态没有变化（已经是最新状态），则重置为默认状态
                if (updatedState.equals(state)) {
                    updatedState = this.getDefaultState()
                            .with(AXIS, state.get(AXIS)) // 保持当前轴向
                            .with(NORTH, false)
                            .with(EAST, false)
                            .with(SOUTH, false)
                            .with(WEST, false)
                            .with(UP, false)
                            .with(DOWN, false);
                }

                world.setBlockState(pos, updatedState);
            }
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return buildDynamicShape(state);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return buildDynamicShape(state);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return false;
    }

    private VoxelShape buildDynamicShape(BlockState state) {
        // 总是从基础形状开始
        VoxelShape shape = BASE_SHAPE;

        // 根据连接状态添加各个方向的延伸
        if (state.get(NORTH)) {
            shape = VoxelShapes.union(shape, NORTH_EXTENSION);
        }
        if (state.get(SOUTH)) {
            shape = VoxelShapes.union(shape, SOUTH_EXTENSION);
        }
        if (state.get(EAST)) {
            shape = VoxelShapes.union(shape, EAST_EXTENSION);
        }
        if (state.get(WEST)) {
            shape = VoxelShapes.union(shape, WEST_EXTENSION);
        }
        if (state.get(UP)) {
            shape = VoxelShapes.union(shape, UP_EXTENSION);
        }
        if (state.get(DOWN)) {
            shape = VoxelShapes.union(shape, DOWN_EXTENSION);
        }

        return shape;
    }
}