package com.qiuyu.fantasyforest.item.spetial;

import com.qiuyu.fantasyforest.block.ModBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random; // 使用 Minecraft 的 Random 接口

public class RevelationLandTask implements ILongRunningTask {
    // 状态常量
    private static final int STATE_INIT = 0;
    private static final int STATE_GATHERING = 1;
    private static final int STATE_EXPLODE = 2;
    private static final int STATE_BIOME = 3;
    private static final int STATE_DONE = 4;

    private final ServerWorld world;
    private final BlockPos centerPos;

    private int currentState = STATE_INIT;
    private int gatheringTicksLeft;
    private int currentRadius = 0;
    private final int maxRadius;

    public RevelationLandTask(ServerWorld world, BlockPos center, int maxRadius) {
        this.world = world;
        this.centerPos = center;
        this.maxRadius = maxRadius;
    }

    @Override
    public boolean executeNextStage(MinecraftServer server) {
        switch (currentState) {
            case STATE_INIT:
                startGathering();
                currentState = STATE_GATHERING;
                return false;

            case STATE_GATHERING:
                if (gatheringTicksLeft > 0) {
                    if(gatheringTicksLeft>40||Random.create().nextInt(40)<gatheringTicksLeft)
                        spawnGatheringParticles();
                    gatheringTicksLeft--;
                } else {
                    currentState = STATE_EXPLODE;
                }
                return false;

            case STATE_EXPLODE:
                performExplosion();
                currentState = STATE_BIOME;
                currentRadius = 0;
                return false;

            case STATE_BIOME:
                boolean biomeDone = changeBiomeProgressively(server);
                if (biomeDone) {
                    currentState = STATE_DONE;
                    return true;
                }
                return false;

            case STATE_DONE:
            default:
                return true;
        }
    }

    private void startGathering() {
        gatheringTicksLeft = 100; // 5秒 (20 ticks/秒)
        world.playSound(null, centerPos, SoundEvents.BLOCK_BEACON_AMBIENT,
                SoundCategory.AMBIENT, 3.0f, 1.0f);
    }

    private void spawnGatheringParticles() {
        Random random = world.random;
        double radius = 5.0;
        int particlesPerTick = 10;

        for (int i = 0; i < particlesPerTick; i++) {
            double x = centerPos.getX() + 0.5 + (random.nextDouble() - 0.5) * radius * 2;
            double y = centerPos.getY() + 1.0 + (random.nextDouble() - 0.5) * radius;
            double z = centerPos.getZ() + 0.5 + (random.nextDouble() - 0.5) * radius * 2;

            double dx = centerPos.getX() + 0.5 - x;
            double dy = centerPos.getY() + 1.0 - y;
            double dz = centerPos.getZ() + 0.5 - z;
            double length = Math.sqrt(dx * dx + dy * dy + dz * dz);
            dx = dx / length * 0.3;
            dy = dy / length * 0.3;
            dz = dz / length * 0.3;

            world.spawnParticles(ParticleTypes.END_ROD,
                    x, y, z,
                    0,
                    dx, dy, dz,
                    1.5);
        }
    }

    private void performExplosion() {
        world.playSound(null, centerPos, SoundEvents.ENTITY_PLAYER_LEVELUP,
                SoundCategory.BLOCKS, 0.8f, 1.0f);
        world.playSound(null, centerPos, SoundEvents.BLOCK_BEACON_ACTIVATE,
                SoundCategory.BLOCKS, 0.8f, 1.0f);
        world.spawnParticles(ParticleTypes.HAPPY_VILLAGER,
                centerPos.getX() + 0.5, centerPos.getY() + 1.0, centerPos.getZ() + 0.5,
                20, 1, 1, 1, 1);
        for(int i =0;i<30;i++){
            world.spawnParticles(ParticleTypes.END_ROD,
                    centerPos.getX() + 0.5, centerPos.getY() + 1.0, centerPos.getZ() + 0.5,
                    0,
                    world.random.nextFloat()-0.5, world.random.nextFloat(), world.random.nextFloat()-0.5,
                    3);
        }

        placeSapling();
    }

    private void placeSapling() {
        BlockPos saplingPos = centerPos.up();
        if (world.getBlockState(saplingPos).isReplaceable()) {
            world.setBlockState(saplingPos, ModBlocks.REVELATION_SAPLING.getDefaultState());
        } else {
            ItemScatterer.spawn(world,centerPos.up(), DefaultedList.copyOf(ItemStack.EMPTY,
                    new ItemStack(ModBlocks.REVELATION_SAPLING.asItem())));
        }
    }

    private boolean changeBiomeProgressively(MinecraftServer server) {
        if (currentRadius > maxRadius) {
            return true;
        }

        for (int x = -currentRadius; x <= currentRadius; x++) {
            for (int z = -currentRadius; z <= currentRadius; z++) {
                if (Math.sqrt(x * x + z * z) <= currentRadius) {
                    BlockPos pos = centerPos.add(x, 0, z);
                    String command = String.format("fillbiome %d %d %d %d %d %d %s",
                            pos.getX(), Math.max(pos.getY() - 15, world.getBottomY()), pos.getZ(),
                            pos.getX(), Math.min(pos.getY() + 16, world.getTopY()), pos.getZ(),
                            "fantasy-forest:land_of_revelation");
                    server.getCommandManager().executeWithPrefix(
                            server.getCommandSource().withSilent(),
                            command
                    );
                }
            }
        }

        currentRadius++;
        return false;
    }

    public ServerWorld getWorld() {
        return world;
    }
}