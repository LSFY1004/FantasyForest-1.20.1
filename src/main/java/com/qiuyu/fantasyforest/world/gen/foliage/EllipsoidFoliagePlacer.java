package com.qiuyu.fantasyforest.world.gen.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class EllipsoidFoliagePlacer extends FoliagePlacer {
    // 椭球体参数配置
    public static final Codec<EllipsoidFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
            fillFoliagePlacerFields(instance)
                    .and(Codec.FLOAT.fieldOf("vertical_ratio").forGetter(placer -> placer.verticalRatio))
                    .and(Codec.FLOAT.fieldOf("density").forGetter(placer -> placer.density))
                    .apply(instance, EllipsoidFoliagePlacer::new));

    private final float verticalRatio; // 垂直半径比例 (0.5 = 扁椭球, 1.0 = 球体, 2.0 = 高椭球)
    private final float density;      // 树叶密度 (0.0-1.0)

    public EllipsoidFoliagePlacer(IntProvider radius, IntProvider offset, float verticalRatio, float density) {
        super(radius, offset);
        this.verticalRatio = verticalRatio;
        this.density = density;
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return ModFoliagePlacerType.ELLIPSOID_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, FoliagePlacer.BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, FoliagePlacer.TreeNode treeNode, int foliageHeight, int radius, int offset) {
        BlockPos center = treeNode.getCenter();
        radius+=treeNode.getFoliageRadius();
        float v = verticalRatio;
        if(treeNode.isGiantTrunk()){
            v /= 1.2F;
            radius++;
        }
        int verticalRadius = Math.max(1, (int)(radius * v));

        // 计算椭球体边界
        int minX = center.getX() - radius;
        int maxX = center.getX() + radius;
        int minY = center.getY() - verticalRadius;
        int maxY = center.getY() + verticalRadius;
        int minZ = center.getZ() - radius;
        int maxZ = center.getZ() + radius;

        // 遍历椭球体内的所有位置
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                for (int z = minZ; z <= maxZ; z++) {
                    BlockPos pos = new BlockPos(x, y, z);

                    // 计算到中心的归一化距离 (0.0-1.0)
                    double dx = (x - center.getX()) / (double)radius;
                    double dy = (y - center.getY()) / (double)verticalRadius;
                    double dz = (z - center.getZ()) / (double)radius;

                    // 椭球方程: (dx^2 + dy^2 + dz^2) <= 1
                    double distanceSq = dx*dx + dy*dy + dz*dz;

                    if (distanceSq <= 1.0) {
                        // 根据密度和位置随机性决定是否放置树叶
                        if (shouldPlaceLeaf(distanceSq, random, density)) {
                            placeFoliageBlock(world, placer, random, config, pos);
                        }
                    }
                }
            }
        }
    }

    // 决定是否放置树叶（基于密度和位置）
    private boolean shouldPlaceLeaf(double distanceSq, Random random, float density) {
        // 在边缘区域增加放置概率（创造更自然的形状）
        float edgeFactor = MathHelper.clamp((float)Math.sqrt(distanceSq) * 1.5f, 0.5f, 1.0f);

        // 计算实际放置概率（考虑密度和边缘因子）
        float probability = density * edgeFactor;

        // 确保中心区域总是放置树叶
        if (distanceSq < 0.25) {
            return true;
        }

        return random.nextFloat() < probability;
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        // 对于椭球体，高度由 verticalRatio 控制，返回固定值
        return 0;
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        // 不需要特定检查，因为椭球体形状已处理
        return false;
    }

}