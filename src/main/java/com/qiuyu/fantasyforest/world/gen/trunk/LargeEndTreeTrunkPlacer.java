package com.qiuyu.fantasyforest.world.gen.trunk;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class LargeEndTreeTrunkPlacer extends TrunkPlacer {
    public static final Codec<LargeEndTreeTrunkPlacer> CODEC = RecordCodecBuilder.create(
            instance -> fillTrunkPlacerFields(instance).apply(instance, LargeEndTreeTrunkPlacer::new));

    public LargeEndTreeTrunkPlacer(int i, int j, int k) {
        super(i, j, k);
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return ModTrunkPlacerType.LARGE_END_TREE_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer,
                                                 Random random, int height, BlockPos startPos, TreeFeatureConfig config) {
        List<FoliagePlacer.TreeNode> list = Lists.newArrayList();
        int branchHeight = height - random.nextInt(2) - 3;// 分支起始高度
        int branchLength = height / 5 + 2 + random.nextInt(3);
        int branchLength2 = height / 8 + 2 + random.nextInt(3);
        Direction direction = Direction.Type.HORIZONTAL.random(random);
        Direction direction2 = direction.getOpposite();
        int ax = 0, az = 0;
        // 生成2×2主干
        for (int y = 0; y < branchHeight; y++) {
            boolean os = branchHeight - y == 17
                    || branchHeight - y == 14
                    || branchHeight - y == 11
                    || branchHeight - y == 8
                    || branchHeight - y == 5
                    || branchHeight - y == 2;
            if (direction.getOffsetX() > 0 && os) ax++;
            if (direction.getOffsetX() < 0 && os) ax--;
            if (direction.getOffsetZ() > 0 && os) az++;
            if (direction.getOffsetZ() < 0 && os) az--;
            if (ax < 2 && az < 2 && ax > -2 && az > -2) {
                BlockPos pos = startPos;
                for (int x = -1; x < 2; x++) {
                    for (int z = -1; z < 2; z++) {
                        pos = new BlockPos(
                                startPos.getX() + x + ax,
                                startPos.getY() + y,
                                startPos.getZ() + z + az
                        );
                        this.getAndSetState(world, replacer, random, pos, config);
                        if (y == 0 && world.testBlockState(pos.down(), state -> state.isOf(Blocks.OBSIDIAN)))
                            setToDirt(world, replacer, random, pos.down(), config);
                        }
                    }
                    if (branchHeight - y == 15) list.add(new FoliagePlacer.TreeNode(pos.up(), 3, true));
                }
                for (int x = 0; x < 2; x++) {
                    for (int z = 0; z < 2; z++) {
                        BlockPos pos = new BlockPos(
                                startPos.getX() + x + ax,
                                startPos.getY() + y,
                                startPos.getZ() + z + az
                        );
                        this.getAndSetState(world, replacer, random, pos, config);
                    }
                }
            }

            // 主干顶部中心作为树叶节点
            BlockPos centerTop = new BlockPos(
                    startPos.getX() + 1 + ax,
                    startPos.getY() + branchHeight,
                    startPos.getZ() + 1 + az
            );
            list.add(new FoliagePlacer.TreeNode(centerTop, 3, true));

            int bx = ax, bz = az;
            for (int y = branchHeight; y < height + 3; y++) {
                if (y % 3 == 1) {
                    ax += -bx / 6;
                    az += -bz / 6;
                }
                BlockPos pos = new BlockPos(
                        startPos.getX() + ax,
                        startPos.getY() + y,
                        startPos.getZ() + az
                );
                this.getAndSetState(world, replacer, random, pos, config);
                if (y == height + 2) list.add(new FoliagePlacer.TreeNode(pos.up(), 1, false));
            }
            ax = bx;
            az = bz;

            // 生成1×1的分支（从主干侧面延伸）
            BlockPos.Mutable branchPos = new BlockPos.Mutable();
            int xOffset = direction.getOffsetX();
            int zOffset = direction.getOffsetZ();

            for (int i = 0; i < branchLength; i++) {
                branchPos.set(
                        startPos.getX() + 1 - xOffset * (i + 1),
                        startPos.getY() + branchHeight/4 + i / 2,
                        startPos.getZ() + 1 - zOffset * (i + 1)
                );
                this.getAndSetState(world, replacer, random, branchPos, config);
                if (i == branchLength - 1) {
                    list.add(new FoliagePlacer.TreeNode(branchPos.up(), 1, false));
                }
            }

            for (int i = 0; i < branchLength - 3; i++) {
                branchPos.set(
                        startPos.getX() + 1 + xOffset * (i + 1),
                        startPos.getY() + branchHeight/4 - i / 3,
                        startPos.getZ() + 1 + zOffset * (i + 1)
                );
                this.getAndSetState(world, replacer, random, branchPos, config);
                if (i == branchLength - 4) {
                    list.add(new FoliagePlacer.TreeNode(branchPos.up(), 2, false));
                }
            }

            BlockPos.Mutable branchPos2 = new BlockPos.Mutable();
            int xOffset2 = direction2.getOffsetX();
            int zOffset2 = direction2.getOffsetZ();
            for (int i = 0; i < branchLength2; i++) {
                branchPos2.set(
                        startPos.getX() + 1 + xOffset2 * (i + 1) + ax / 2,
                        startPos.getY() + branchHeight + i - 11,
                        startPos.getZ() + 1 + zOffset2 * (i + 1) + az / 2
                );
                this.getAndSetState(world, replacer, random, branchPos2, config);
                if (i == branchLength2 - 1) {
                    list.add(new FoliagePlacer.TreeNode(branchPos2.up(), 1, false));
                }
            }

        return list;
    }
}
