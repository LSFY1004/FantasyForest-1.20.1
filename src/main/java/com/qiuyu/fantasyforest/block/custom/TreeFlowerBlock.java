package com.qiuyu.fantasyforest.block.custom;

import com.qiuyu.fantasyforest.effect.ModEffects;
import com.qiuyu.fantasyforest.particle.ModParticles;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TreeFlowerBlock extends Block {
    // 状态0-3：正常花（0单花，1顶端，2底端，3中间）
    // 状态4-6：被剪刀修剪后的形态（4由状态0剪得，5由状态2剪得，6由状态3剪得）
    public static final IntProperty STATE = IntProperty.of("state", 0, 6);

    // 粒子颜色：粉紫色 (RGB 0.8, 0.4, 0.9)
    private static final Vector3f PARTICLE_COLOR = new Vector3f(0.8f, 0.4f, 0.9f);

    public TreeFlowerBlock(Settings settings) {
        super(settings.ticksRandomly()); // 开启随机刻
        setDefaultState(getStateManager().getDefaultState().with(STATE, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STATE);
    }

    // 轮廓箱（统一为2x16x2，实际可调整）
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(2, 0, 2, 14, 16, 14);
    }

    // 放置条件：上方方块必须是同类花（状态<4）或者上方方块的顶面完整
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos upPos = pos.up();
        BlockState upState = world.getBlockState(upPos);
        return upState.isOf(this) || Block.isFaceFullSquare(upState.getCollisionShape(world, upPos), Direction.UP);
    }

    // 相邻方块更新：条件不满足时破坏并掉落
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState,
                                                WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!canPlaceAt(state, world, pos)) {
            if (!world.isClient()) {
                world.breakBlock(pos, true);
                return Blocks.AIR.getDefaultState();
            }
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    // 方块被放置后，更新整串状态
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, net.minecraft.entity.LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient) {
            updateChainState(world, pos);
        }
    }

    // 随机刻：生长 + 状态3的治疗效果
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int stage = state.get(STATE);
        // 只有状态0-3参与生长和效果
        if (stage > 3) return;

        // 向下生长（10%概率）
        if (random.nextInt(10) == 0 && canGrowDown(world, pos, state)) {
            growDown(world, pos);
        }

        if (stage == 3) {
            if(random.nextInt(4) == 0)applyEffect(world, pos);
        }
    }

    // 客户端粒子：状态0和2滴水，状态3额外粒子
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        int stage = state.get(STATE);
//        if (stage == 0 || stage == 2) {
//            if (random.nextInt(10) == 0) {
//                double x = pos.getX() + 0.5;
//                double y = pos.getY() + 0.1;
//                double z = pos.getZ() + 0.5;
//
//                // 添加一些随机偏移，让滴水位置更自然
//                x += (random.nextDouble() - 0.5) * 0.6;
//                z += (random.nextDouble() - 0.5) * 0.6;
//
//                // 速度：向下为主，轻微水平飘移
//                double vx = (random.nextDouble() - 0.5) * 0.02;
//                double vy = -0.08 - random.nextDouble() * 0.04;
//                double vz = (random.nextDouble() - 0.5) * 0.02;
//
//                world.addParticle((ParticleEffect) ModParticles.DRIPPING_PARTICLE, x, y, z, vx, vy, vz);
//            }
//        }
        if (stage == 3) spawnParticles(world, pos, random);
    }

    // ==================== 整串状态管理 ====================

    /**
     * 获取从 pos 开始的一整串连续花的位置，按Y坐标从上到下排序
     */
    private List<BlockPos> getWholeChain(World world, BlockPos pos) {
        List<BlockPos> chain = new ArrayList<>();
        BlockPos current = pos;
        // 向上遍历
        while (true) {
            BlockState state = world.getBlockState(current);
            if (!state.isOf(this)) break;
            chain.add(current);
            BlockPos up = current.up();
            BlockState upState = world.getBlockState(up);
            if (!upState.isOf(this)) break;
            current = up;
        }
        // 从起始点向下遍历（但不重复添加起始点）
        current = pos.down();
        while (true) {
            BlockState state = world.getBlockState(current);
            if (!state.isOf(this)) break;
            chain.add(current);
            current = current.down();
        }
        // 按Y坐标升序排序（从下到上），实际从上到下就是降序，我们按从上到下顺序为0索引为顶端
        chain.sort(Comparator.comparingInt(BlockPos::getY).reversed()); // Y大的在上
        return chain;
    }

    /**
     * 更新一整串花的状态，规则：
     * - 长度1 -> 状态0
     * - 长度>=2 -> 顶端状态1，底端状态2，中间状态3
     */
    private void updateChainState(World world, BlockPos pos) {
        List<BlockPos> chain = getWholeChain(world, pos);
        int len = chain.size();
        if (len == 0) return;
        if (len == 1) {
            BlockPos p = chain.get(0);
            world.setBlockState(p, world.getBlockState(p).with(STATE, 0), Block.NOTIFY_ALL);
            return;
        }
        for (int i = 0; i < len; i++) {
            BlockPos p = chain.get(i);
            if (i == 0) { // 最顶端（Y最大）
                world.setBlockState(p, world.getBlockState(p).with(STATE, 1), Block.NOTIFY_ALL);
            } else if (i == len - 1) { // 最底端（Y最小）
                world.setBlockState(p, world.getBlockState(p).with(STATE, 2), Block.NOTIFY_ALL);
            } else if (world.getBlockState(p).get(STATE)!=6){
                world.setBlockState(p, world.getBlockState(p).with(STATE, 3), Block.NOTIFY_ALL);
            }
        }
    }

    // ==================== 向下生长 ====================

    private boolean canGrowDown(World world, BlockPos pos, BlockState state) {
        List<BlockPos> chain = getWholeChain(world, pos);
        if (chain.isEmpty()) return false;
        BlockPos bottom = chain.get(chain.size() - 1); // 最下方位置
        if (!bottom.equals(pos)) return false; // 只有最下方才能生长
        if (chain.size() >= 4) return false; // 最大长度限制为4
        int stage = state.get(STATE);
        if (stage != 0 && stage != 2) return false; // 必须是单花或底端

        return world.getBlockState(pos.down()).isReplaceable();
    }

    private void growDown(ServerWorld world, BlockPos pos) {
        BlockPos newPos = pos.down();
        // 放置一个默认状态0的花
        world.setBlockState(newPos, getDefaultState(), Block.NOTIFY_ALL);
        // 更新包含新旧位置在内的整串状态
        updateChainState(world, newPos);
    }

    // ==================== 散发效果（状态3） ====================

    private void applyEffect(ServerWorld world, BlockPos center) {
        Box area = new Box(center).expand(5.0); // 周围5格
        world.getEntitiesByClass(net.minecraft.entity.LivingEntity.class, area, LivingEntity::isAlive)
                .forEach(entity -> {
                    entity.addStatusEffect(new StatusEffectInstance(ModEffects.CUPID_KISS, 300, 1, false, true));
                });
    }

    private void spawnParticles(World world, BlockPos pos, Random random) {
        double x = pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.8;
        double y = pos.getY() + 0.5 + (random.nextDouble() - 0.5) * 0.8;
        double z = pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.8;
        // 速度：向下为主，带轻微随机水平偏移
        Vec3d velocity = new Vec3d(
                (random.nextDouble() - 0.5) * 0.02,
                -0.08 - random.nextDouble() * 0.04,
                (random.nextDouble() - 0.5) * 0.02
        );
        world.addParticle(new DustParticleEffect(PARTICLE_COLOR, 1.0f), x, y, z, velocity.x, velocity.y, velocity.z);
    }

    // ==================== 剪刀剪切 ====================

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.isOf(Items.SHEARS)) {
            int currentState = state.get(STATE);
            int newState = -1;
            if (currentState == 0) newState = 4;
            else if (currentState == 2) newState = 5;
            else if (currentState == 3) newState = 6;
            else return ActionResult.PASS;

            if (!world.isClient) {
                // 改变状态
                world.setBlockState(pos, state.with(STATE, newState), Block.NOTIFY_ALL);
                // 消耗耐久
                stack.damage(1, player, p -> p.sendToolBreakStatus(hand));
                // 播放剪切声音
                world.playSound(null, pos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }
            return ActionResult.success(world.isClient);
        }
        return ActionResult.PASS;
    }
}