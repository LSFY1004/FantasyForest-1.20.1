package com.qiuyu.fantasyforest.world.gen.trunk;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class AphrodisiaTrunkPlacer extends TrunkPlacer {

    public static final Codec<AphrodisiaTrunkPlacer> CODEC = RecordCodecBuilder.create(
            instance -> fillTrunkPlacerFields(instance).apply(instance, AphrodisiaTrunkPlacer::new));

    public AphrodisiaTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return ModTrunkPlacerType.APHRODISIA_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer,
                                                 Random random, int height, BlockPos startPos, TreeFeatureConfig config) {
        setToDirt(world, replacer, random, startPos.down(), config);

        // 主干实际高度（已由基类根据 baseHeight, firstRandomHeight 计算）
        // 偏移发生的高度索引 (0 为地面，1 为第二格，2 为第三格)
        int offsetIndex = random.nextBoolean() ? 2 : 3; // 第2格或第3格
        // 确保偏移索引不超过 trunkHeight-1
        offsetIndex = Math.min(offsetIndex, height - 1);
        // 随机偏移方向 (东, 南, 西, 北)
        Vec3i offsetDir = switch (random.nextInt(4)) {
            case 0 -> new Vec3i(1, 0, 0);  // 东
            case 1 -> new Vec3i(0, 0, 1);  // 北
            case 2 -> new Vec3i(-1, 0, 0); // 西
            default -> new Vec3i(0, 0, -1); // 南
        };

        // 生成主干方块，并记录顶端位置
        BlockPos trunkTop = generateTrunk(world, replacer, random, startPos, height, offsetIndex, offsetDir, config);

        List<FoliagePlacer.TreeNode> foliageNodes = Lists.newArrayList();
        // 主干顶部树叶节点
        foliageNodes.add(new FoliagePlacer.TreeNode(trunkTop.up(2), 1, false));

        // 枝干长度 = 主干高度 - 1
        int branchLength = height - height/4;
        if (branchLength > 0) {
            // 选择三个枝干方向
            List<Vec3i> branchDirs = selectBranchDirections(random);
            for (Vec3i dir : branchDirs) {
                // 生成枝干，并获得末端的树叶节点位置
                BlockPos branchTip = generateBranch(world, replacer, random, trunkTop, dir, branchLength, config);
                foliageNodes.add(new FoliagePlacer.TreeNode(branchTip, 2, false));
            }
        }

        return foliageNodes;
    }

    /**
     * 生成弯曲的主干
     */
    private BlockPos generateTrunk(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer,
                                   Random random, BlockPos startPos, int height, int offsetIndex,
                                   Vec3i offsetDir, TreeFeatureConfig config) {
        BlockPos.Mutable pos = new BlockPos.Mutable();
        BlockPos offsetPos = null;

        for (int i = 0; i < height; i++) {
            if (i < offsetIndex) {
                // 偏移点之前：垂直向上
                pos.set(startPos.getX(), startPos.getY() + i, startPos.getZ());
            } else if (i == offsetIndex) {
                // 偏移点本身：原位置 + 偏移
                pos.set(startPos.getX() + offsetDir.getX(),
                        startPos.getY() + i,
                        startPos.getZ() + offsetDir.getZ());
                offsetPos = pos.toImmutable();
            } else {
                // 偏移点之后：基于偏移点继续垂直向上
                int upSteps = i - offsetIndex;
                pos.set(offsetPos.getX(),
                        offsetPos.getY() + upSteps,
                        offsetPos.getZ());
            }
            this.getAndSetState(world, replacer, random, pos, config);
        }

        // 顶端位置：最后一次循环的 pos
        return pos.toImmutable();
    }

    /**
     * 选择三个枝干方向：两个互相垂直的轴向方向，第三个为它们反向之和
     */
    private List<Vec3i> selectBranchDirections(Random random) {
        // 所有轴向方向
        Vec3i[] axials = {
                new Vec3i(1, 0, 0),  // 东
                new Vec3i(0, 0, 1),  // 北
                new Vec3i(-1, 0, 0), // 西
                new Vec3i(0, 0, -1)  // 南
        };
        // 随机选第一个方向
        Vec3i dir1 = axials[random.nextInt(4)];
        // 找出与 dir1 垂直的两个方向
        List<Vec3i> perpendicular = Lists.newArrayList();
        for (Vec3i d : axials) {
            if (d.getX() * dir1.getX() + d.getZ() * dir1.getZ() == 0) {
                perpendicular.add(d);
            }
        }
        // 随机选一个垂直方向作为 dir2
        Vec3i dir2 = perpendicular.get(random.nextInt(perpendicular.size()));
        // 第三方向 = -(dir1 + dir2)
        Vec3i dir3 = new Vec3i(-(dir1.getX() + dir2.getX()), 0, -(dir1.getZ() + dir2.getZ()));
        return List.of(dir1, dir2, dir3);
    }

    /**
     * 生成枝干，返回枝干末端位置（树叶节点位置）
     */
    private BlockPos generateBranch(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer,
                                    Random random, BlockPos startPos, Vec3i direction,
                                    int length, TreeFeatureConfig config) {
        // 获取左侧偏移向量（水平）
        Vec3i leftOffset = getLeftOffset(direction);
        BlockPos.Mutable currentPos = new BlockPos.Mutable();

        for (int step = 1; step <= length; step++) {
            // 基础位置：起点 + step * (dx, 1, dz)
            int x = startPos.getX() + direction.getX() * step;
            int y = startPos.getY() + step;            // 每格升高1
            int z = startPos.getZ() + direction.getZ() * step;
            // 从第3格开始，每格额外向左侧偏移 (step-2) 格（累积）
            if (step >= 3) {
                int extra = step - 2;
                x += leftOffset.getX() * extra;
                z += leftOffset.getZ() * extra;
            }
            currentPos.set(x, y, z);
            this.getAndSetState(world, replacer, random, currentPos, config);
        }
        return currentPos.toImmutable();
    }

    /**
     * 根据枝干前进方向（水平向量），返回其左侧方向（水平）
     */
    private Vec3i getLeftOffset(Vec3i dir) {
        int dx = dir.getX();
        int dz = dir.getZ();
        // 轴向方向
        if (dx == 1 && dz == 0) return new Vec3i(0, 0, 1);  // 东 -> 北
        if (dx == -1 && dz == 0) return new Vec3i(0, 0, -1); // 西 -> 南
        if (dx == 0 && dz == 1) return new Vec3i(-1, 0, 0); // 北 -> 西
        if (dx == 0 && dz == -1) return new Vec3i(1, 0, 0); // 南 -> 东
        // 对角线方向
        if (dx == 1 && dz == 1) return new Vec3i(-1, 0, 0);  // 东北 -> 西
        if (dx == 1 && dz == -1) return new Vec3i(0, 0, 1);  // 东南 -> 北
        if (dx == -1 && dz == 1) return new Vec3i(0, 0, -1); // 西北 -> 南
        if (dx == -1 && dz == -1) return new Vec3i(1, 0, 0); // 西南 -> 东
        // fallback (不应发生)
        return new Vec3i(0, 0, 0);
    }
}