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

public class PermearomaTrunkPlacer extends TrunkPlacer {

    public static final Codec<PermearomaTrunkPlacer> CODEC = RecordCodecBuilder.create(
            instance -> fillTrunkPlacerFields(instance).apply(instance, PermearomaTrunkPlacer::new));

    public PermearomaTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return ModTrunkPlacerType.PERMEAROMA_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer,
                                                 Random random, int height, BlockPos startPos, TreeFeatureConfig config) {
        setToDirt(world, replacer, random, startPos.down(), config);
        List<FoliagePlacer.TreeNode> foliageNodes = Lists.newArrayList();

        // 生成主干（从地面到整个高度）
        for (int i = 0; i < height; i++) {
            this.getAndSetState(world, replacer, random, startPos.up(i), config);
        }

        // 添加主干顶部的树叶节点
        foliageNodes.add(new FoliagePlacer.TreeNode(startPos.up(height), 1, false));

        // 确定分支数量（3-5个）
        int branchCount = random.nextBetween(3, 5);

        // 生成侧向分支（等角度分布）
        double angleStep = 2 * Math.PI / branchCount;
        for (int i = 0; i < branchCount; i++) {
            double angle = i * angleStep;
            // 计算分支方向向量（略向上倾斜）
            double xDir = Math.cos(angle);
            double zDir = Math.sin(angle);
            Vec3i direction = new Vec3i(
                    (int) Math.signum(xDir),
                    1,
                    (int) Math.signum(zDir)
            );

            // 随机选择分支起始高度（在树干的上半部分）
            int branchStartHeight = random.nextBetween(height/2+1, height/2 + 4);
            generateBranch(world, replacer, random, startPos.up(branchStartHeight),
                    config, foliageNodes, direction,
                    random.nextBetween(3, 4)); // 分支长度
        }

        return foliageNodes;
    }

    private void generateBranch(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer,
                                Random random, BlockPos startPos, TreeFeatureConfig config,
                                List<FoliagePlacer.TreeNode> foliageNodes, Vec3i direction, int branchLength) {
        BlockPos currentPos = startPos;
        for (int i = 0; i < branchLength; i++) {
            currentPos = startPos.add(direction.getX()*(i+1), direction.getY()-i*2/3, direction.getZ()*(i+1));
            this.getAndSetState(world, replacer, random, currentPos, config);
            if(i==branchLength/2-1)foliageNodes.add(new FoliagePlacer.TreeNode(currentPos, 1, false));
        }
        foliageNodes.add(new FoliagePlacer.TreeNode(currentPos.up(branchLength/2), 1, false));
    }
}