package com.qiuyu.fantasyforest.item.spetial;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class RevelationLandTask implements ILongRunningTask {
    private final ServerWorld world;
    private final BlockPos centerPos;

    private int currentRadius = 0;
    private final int maxRadius;

    // 大树生成状态变量 (示例)
    private boolean treeGenerationStarted = false;
    private boolean treeGenerationCompleted = false;

    public RevelationLandTask(ServerWorld world, BlockPos center, int maxRadius) {
        this.world = world;
        this.centerPos = center;
        this.maxRadius = maxRadius;
    }

    @Override
    public boolean executeNextStage(MinecraftServer server) {
        // 1. 首先处理大树生成 (如果还没开始)
        if (!treeGenerationStarted) {
            startTreeGeneration();
            treeGenerationStarted = true;
            return false; // 本轮不进行群系更改，下轮再继续
        }

        // 2. 如果树还在生成中，等待其完成 (这里假设树生成也是一个分帧任务)
        // 在实际应用中，你可能需要检查一个 ProgressiveTreeGenerator 的状态
        if (!treeGenerationCompleted) {
            // 检查树是否生成完毕，如果完毕则设置 treeGenerationCompleted = true;
            // 为了示例，我们假设树生成需要3 tick来完成
            // 在真实代码中，这里会是你的渐进式树生成器的状态检查
            treeGenerationCompleted = true; // 示例中直接设为完成
            return false;
        }

        // 3. 大树生成完成后，开始渐进式更改群系
        return changeBiomeProgressively(server);
    }

    private void startTreeGeneration() {
        // 调用你的渐进式树生成器，而不是立即生成
        // ProgressiveTreeGenerator.scheduleTask(new MyTreeGenerationTask(world, centerPos.up()));
        // 示例中，我们只是标记树开始生成
        System.out.println("开始渐进式生成启示之树...");
    }

    private boolean changeBiomeProgressively(MinecraftServer server) {
        if (currentRadius > maxRadius) {
            System.out.println("启示之地群系转换完成！");
            return true; // 任务完成
        }

        // 简化示例：更改当前半径范围内的一层方块
        for (int x = -currentRadius; x <= currentRadius; x++) {
            for (int z = -currentRadius; z <= currentRadius; z++) {
                // 简单边界检查，确保我们更改的是一个"圆"
                if (Math.sqrt(x*x + z*z) <= currentRadius) {
                    BlockPos pos = centerPos.add(x, 0, z);
                    String command = String.format("fillbiome %d %d %d %d %d %d %s",
                            pos.getX(), Math.max(pos.getY()-15,world.getBottomY()), pos.getZ(),
                            pos.getX(), Math.min(pos.getY()+16,world.getTopY()), pos.getZ(),
                            "fantasy-forest:land_of_revelation");
                    server.getCommandManager().executeWithPrefix(
                            server.getCommandSource().withSilent(),
                            command
                    );
                }
            }
        }

        System.out.println("更改群系半径: " + currentRadius);

        currentRadius++;

        return false; // 任务未完成
    }

    public ServerWorld getWorld() {
        return world;
    }
}
