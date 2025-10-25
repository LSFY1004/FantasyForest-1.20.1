package com.qiuyu.fantasyforest.block.custom;

import com.qiuyu.fantasyforest.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SpreadableBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import org.joml.Vector3f;

import java.util.List;

public class EndLichenBlock extends SpreadableBlock {
    // 行走粒子特效配置
    private static final int STEP_PARTICLE_COUNT = 2;
    private static final float STEP_PARTICLE_CHANCE = 0.2f; // 20% 概率生成粒子

    // 定义发光属性
    public static final BooleanProperty LIT = Properties.LIT;
    // 定义计时器属性
    public static final IntProperty TIMER = IntProperty.of("timer", 0, 100);

    public EndLichenBlock(Settings settings) {
        super(settings.luminance(state -> {
            boolean lit = state.get(LIT);
            return lit ? 9 : 0;
        }));
        // 设置默认状态为不发光的，计时器为0
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(LIT, false)
                .with(TIMER, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<net.minecraft.block.Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(LIT, TIMER);
    }

    private static boolean canSurvive(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);

        if (blockState.getFluidState().getLevel() == 8) {
            return false; // 不能生存
        }
        // 情况3: 其他情况
        else {
            // 计算实际的光照不透明度
            int i = ChunkLightProvider.getRealisticOpacity(world, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
            // 判断计算出的不透明度是否小于世界的最大光照等级
            return i < world.getMaxLightLevel();
        }
    }

    // 重写随机刻逻辑：控制扩散速度和目标
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!canSurvive(state,world,pos)) {
            world.setBlockState(pos, Blocks.END_STONE.getDefaultState());
            return;
        }

        int count = 0;
        for(int i=-1;i<=1;i++){
            for(int j=-1;j<=1;j++){
                for(int k = -1; k <=1; k++){
                    if(world.getBlockState(pos.add(i,j,k)).isOf(Blocks.END_STONE) &&
                            world.getBlockState(pos.add(i,j+1,k)).isAir())count++;
                }
            }
        }
        BlockPos targetPos = pos.add(
                random.nextInt(3) - 1,
                random.nextInt(3) - 1,
                random.nextInt(3) - 1
        );

        if (world.getBlockState(targetPos).isOf(Blocks.END_STONE) &&
                world.getBlockState(targetPos.up()).isAir()&&
                random.nextInt(27)>count-2) {
            world.setBlockState(targetPos, this.getDefaultState());
        }
    }

    private boolean hasEntityOnBlock(World world, BlockPos pos) {
        // 创建一个稍微高于方块的检测区域
        Box detectionBox = new Box(
                pos.getX(), pos.getY(), pos.getZ(),
                pos.getX() + 1, pos.getY() + 0.2, pos.getZ() + 1
        );

        // 获取区域内的所有实体
        List<Entity> entities = world.getNonSpectatingEntities(Entity.class, detectionBox);

        // 如果有任何实体，返回true
        return !entities.isEmpty();
    }

    // 当实体踏上方块时调用
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        // 触发发光效果
        if (!world.isClient()) {
            BlockState newState = state;

            // 如果方块未点亮，则点亮它
            if (!state.get(LIT)) {
                newState = newState.with(LIT, true);
            }

            // 重置计时器
            newState = newState.with(TIMER, 0);

            if (!newState.equals(state)) {
                world.setBlockState(pos, newState);
            }

            // 安排下一次检查
            if (world instanceof ServerWorld serverWorld) {
                serverWorld.scheduleBlockTick(pos, this, 10);
            }
        }

        // 只在服务端生成粒子（会同步到客户端）
        if (!world.isClient() && entity instanceof PlayerEntity) {
            // 随机决定是否生成粒子（避免每一步都生成）
            if (world.random.nextFloat() < STEP_PARTICLE_CHANCE) {
                spawnStepParticles(world, entity);
            }
        }
        super.onSteppedOn(world, pos, state, entity);
    }

    // 处理计划刻 - 用于关闭发光
    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.get(LIT)) {
            return; // 如果已经熄灭，不再处理
        }

        boolean hasEntity = hasEntityOnBlock(world, pos);
        int currentTimer = state.get(TIMER);

        if (hasEntity) {
            // 有实体在上面，重置计时器并继续检查
            world.setBlockState(pos, state.with(TIMER, 0));
            world.scheduleBlockTick(pos, this, 10);
        } else {
            // 没有实体，增加计时器
            if (currentTimer >= 100) {
                // 达到5秒，熄灭方块
                world.setBlockState(pos, state.with(LIT, false).with(TIMER, 0));
            } else {
                // 未达到5秒，继续计时
                world.setBlockState(pos, state.with(TIMER, currentTimer + 10));
                world.scheduleBlockTick(pos, this, 10);
            }
        }
    }

    // 生成行走粒子特效
    private void spawnStepParticles(World world, Entity entity) {
        // 获取玩家位置
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        // 在玩家脚部位置生成粒子
        for (int i = 0; i < STEP_PARTICLE_COUNT; i++) {
            double offsetX = (world.random.nextDouble() - 0.5) * entity.getWidth();
            double offsetZ = (world.random.nextDouble() - 0.5) * entity.getWidth();

            // 计算粒子位置（在玩家脚部高度）
            double particleX = x + offsetX;
            double particleY = y + 0.1; // 地面略微上方
            double particleZ = z + offsetZ;

            // 发送粒子到所有客户端
            if (world instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(
                        getStepParticleType(), // 粒子类型
                        particleX,
                        particleY,
                        particleZ,
                        1, // 数量
                        0.0, 0.0, 0.0, // 偏移
                        0.1 // 速度
                );
            }
        }
    }

    // 获取粒子类型（可根据需求自定义）
    private ParticleEffect getStepParticleType() {
        return new DustParticleEffect(new Vector3f(0.0f, 1.0f, 0.3f), 1.0f); // 紫色反向传送门粒子
    }

    // 添加右键使用功能：用铲子变成末地石，用骨粉促进生长
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (itemStack.isIn(ItemTags.SHOVELS)) {
            return convertToDead(world, pos, player, hand);
        } else if (itemStack.isOf(Items.BONE_MEAL)) {
            return applyBoneMeal(world, pos, player, hand);
        }

        return ActionResult.PASS;
    }

    private ActionResult convertToDead(World world, BlockPos pos, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            // 服务端逻辑
            world.setBlockState(pos, ModBlocks.DEAD_END_LICHEN.getDefaultState());
            world.playSound(null, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);

            // 消耗耐久
            ItemStack itemStack = player.getStackInHand(hand);
            if (!player.isCreative()) {
                itemStack.damage(1, player, (p) -> p.sendToolBreakStatus(hand));
            }
        }

        return ActionResult.success(world.isClient);
    }

    // 骨粉促进生长功能
    private ActionResult applyBoneMeal(World world, BlockPos pos, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            // 服务端逻辑
            boolean success = false;
            for (int x = -2; x <= 2; x++) {
                for (int y = -2; y <= 2; y++){
                    for (int z = -2; z <= 2; z++){
                        if(Math.abs(x)+Math.abs(y)+Math.abs(z)<=3) {
                            BlockPos targetPos = pos.add(x,y,z);
                            if (world.getBlockState(targetPos).isOf(Blocks.END_STONE) &&
                                    world.getBlockState(targetPos.up()).isAir()) {
                                world.setBlockState(targetPos, this.getDefaultState());
                                success=true;
                                if (world instanceof ServerWorld serverWorld) {
                                    serverWorld.spawnParticles(
                                            ParticleTypes.HAPPY_VILLAGER,
                                            targetPos.getX() + 0.5,
                                            targetPos.getY() + 0.5,
                                            targetPos.getZ() + 0.5,
                                            10,
                                            0.5, 0.5, 0.5,
                                            0.05
                                    );
                                }
                            }
                        }
                    }
                }
            }
            if(success){
                if (world instanceof ServerWorld serverWorld) {
                    serverWorld.spawnParticles(
                            ParticleTypes.HAPPY_VILLAGER,
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5,
                            10,
                            0.5, 0.5, 0.5,
                            0.05
                    );
                }
                // 播放骨粉使用效果
                world.playSound(null, pos, SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                // 消耗骨粉
                ItemStack itemStack = player.getStackInHand(hand);
                if (!player.isCreative()) {
                    itemStack.decrement(1);
                }
            }

        } else {
            // 客户端播放粒子效果
            world.addParticle(
                    ParticleTypes.HAPPY_VILLAGER,
                    pos.getX() + 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5,
                    0, 0, 0
            );
        }

        return ActionResult.success(world.isClient);
    }
}