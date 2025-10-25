package com.qiuyu.fantasyforest.world.gen.trunk;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.qiuyu.fantasyforest.block.ModBlocks;
import com.qiuyu.fantasyforest.block.custom.PhosphorBlock;
import net.minecraft.block.BlockState;
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

public class LargeVoidTreeTrunkPlacer extends TrunkPlacer {
    public static final Codec<LargeVoidTreeTrunkPlacer> CODEC = RecordCodecBuilder.create(
            instance -> fillTrunkPlacerFields(instance).apply(instance, LargeVoidTreeTrunkPlacer::new));

    public LargeVoidTreeTrunkPlacer(int i, int j, int k) {
        super(i, j, k);
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return ModTrunkPlacerType.LARGE_VOID_TREE_TRUNK_PLACER;
    }

    private void genBranch (TestableWorld world, BiConsumer<BlockPos, BlockState> replacer,
                            Random random, int branchHeight, int rootHeight, int y, int xOffset, int zOffset, BlockPos startPos, int branchLength, TreeFeatureConfig config,
                            List<FoliagePlacer.TreeNode> list){
        for (int i = 0; i < branchLength; i++) {
            BlockPos.Mutable branchPos = new BlockPos.Mutable();
            branchPos.set(
                    startPos.getX() + 1 + xOffset * (i + 1),
                    startPos.getY() + branchHeight - i *i/ y + rootHeight,
                    startPos.getZ() + 1 + zOffset * (i + 1)
            );
            this.getAndSetState(world, replacer, random, branchPos, config);
            for(int j = 1; j<i*i/ y -(i-1)*(i-1)/ y; j++)
                this.getAndSetState(world, replacer, random, branchPos.up(j), config);
            if (i % 3 == 2||i==branchLength-1) {
                list.add(new FoliagePlacer.TreeNode(branchPos.up(2), 1, false));
            }
        }
    }

    private void genRoots (TestableWorld world, BiConsumer<BlockPos, BlockState> replacer,
                           Random random, int xOffset, int zOffset, BlockPos startPos, int rootLength, TreeFeatureConfig config){
        for (int i = 3; i < rootLength; i++) {
            BlockPos.Mutable rootPos = new BlockPos.Mutable();
            rootPos.set(
                    startPos.getX() + 1 + xOffset * (i + 1),
                    startPos.getY(),
                    startPos.getZ() + 1 + zOffset * (i + 1)
            );
            for(int rr=0;rr<rootLength-i;rr++) {
                BlockPos pos = new BlockPos(
                        rootPos.getX()+random.nextInt(rootLength-i)/3-(rootLength-i)/6,
                        rootPos.getY()+random.nextInt(4)/3,
                        rootPos.getZ()+random.nextInt(rootLength-i)/3-(rootLength-i)/6
                );
                if (canReplace(world, pos)) setToDirt(world, replacer, random, pos, config);
                if (canReplace(world, rootPos)) setToDirt(world, replacer, random, rootPos, config);
            }
        }
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer,
                                                 Random random, int height, BlockPos startPos, TreeFeatureConfig config) {
        List<FoliagePlacer.TreeNode> list = Lists.newArrayList();
        // 分支起始高度
        int branchLength = height / 5 + 4 + random.nextInt(3);
        int rootHeight = height / 8 + 2;
        Direction direction = Direction.Type.HORIZONTAL.random(random);
        int ax = 0, az = 0;
        // 生成主干
        for (int y = 0; y < height; y++) {
            if (y >= rootHeight) {
                int[][] offsets = {{0, 1}, {1, 1}, {1, 0}, {0, 0}};
                int index = (y % 8)/2;
                ax = offsets[index][0];
                az = offsets[index][1];
            }
            BlockPos pos = startPos;
            for (int x = -1; x < 2; x++) {
                for (int z = -1; z < 2; z++) {
                    pos = new BlockPos(
                            startPos.getX() + x + ax,
                            startPos.getY() + y + rootHeight,
                            startPos.getZ() + z + az
                    );
                    this.getAndSetState(world, replacer, random, pos, config);
                }
            }
            if ((y - rootHeight) % 8 == 1) list.add(new FoliagePlacer.TreeNode(pos.add(ax-direction.getOffsetZ(),2,az-direction.getOffsetX()), 1, false));
        }

        // 主干顶部中心作为树叶节点
        BlockPos centerTop = new BlockPos(
                startPos.getX() + 1 + ax,
                startPos.getY() + height + rootHeight,
                startPos.getZ() + 1 + az
        );
        list.add(new FoliagePlacer.TreeNode(centerTop, 2, false));

        // 生成1×1的分支（从主干侧面延伸）
        for(int x=-1;x<=1;x++){
            for(int z=-1;z<=1;z++){
                genBranch(world,replacer,random, height -2-random.nextInt(4),rootHeight, 6+random.nextInt(4),x,z,startPos,branchLength,config,list);
                genRoots(world,replacer,random,x,z,startPos,branchLength,config);
            }
        }

        for(int y=rootHeight;y>=0;y--){
            for(int x=-2+y-rootHeight;x<=2-y+rootHeight;x++){
                for(int z=-2+y-rootHeight;z<=2-y+rootHeight;z++){
                    BlockPos pos=new BlockPos(
                            startPos.getX() + x,
                            startPos.getY() + y,
                            startPos.getZ() + z
                    );
                    int r=rootHeight-random.nextInt(rootHeight+1);
                    if(canReplace(world,pos)&&r<y){
                        setToDirt(world,replacer,random,pos,config);
                    }
                    if(world.testBlockState(pos, state -> state.isOf(ModBlocks.VOID_TREE_SAPLING)))
                        setToDirt(world,replacer,random,pos,config);
                }
            }
        }
        BlockState phosphorState = ModBlocks.PHOSPHOR.getDefaultState()
                .with(PhosphorBlock.LIGHT_STATE, 2); // 状态2=15亮度
        for(int y=1;y<3;y++){
            for(int x=-1;x<2;x++){
                for(int z=-1;z<2;z++)
                    replacer.accept(startPos.add(x,y,z), phosphorState);
            }
        }
        return list;
    }
}
