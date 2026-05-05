package com.qiuyu.fantasyforest.world.gen.trunk;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.qiuyu.fantasyforest.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
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

public class RevelationTrunkPlacer extends TrunkPlacer {

    public static final Codec<RevelationTrunkPlacer> CODEC = RecordCodecBuilder.create(
            instance -> fillTrunkPlacerFields(instance).apply(instance, RevelationTrunkPlacer::new));

    public RevelationTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return ModTrunkPlacerType.REVELATION_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer,
                                                 Random random, int height, BlockPos startPos, TreeFeatureConfig config) {
        setToDirt(world, replacer, random, startPos.down(), config);
        List<FoliagePlacer.TreeNode> foliageNodes = Lists.newArrayList();

        // 生成主干
        for(int x=-3;x<=3;x++){
            for(int z=-3;z<=3;z++){
                if(Math.abs(x)+Math.abs(z)<=4)
                    this.getAndSetState(world, replacer, random, startPos.add(x,0,z), config);
            }
        }
        for(int x=-2;x<=2;x++){
            for(int z=-2;z<=2;z++){
                if(Math.abs(x)+Math.abs(z)==3||(x*z==1||x*z==-1))
                    this.getAndSetState(world, replacer, random, startPos.add(x,1,z), config);
            }
        }
        for(int x=-1;x<=1;x++){
            for(int z=-1;z<=1;z++){
                if(x*z==1||x*z==-1||(x==0&&z==0)){
                    this.getAndSetState(world, replacer, random, startPos.add(x,2,z), config);
                }
                if(x*z==0&&x+z!=0){
                    replacer.accept(startPos.add(x,1,z), ModBlocks.REVELATION_CORE_SIDE.getDefaultState());
                    replacer.accept(startPos.add(x,2,z), ModBlocks.REVELATION_CORE_SIDE.getDefaultState());
                }
                this.getAndSetState(world, replacer, random, startPos.add(x,3,z), config);
            }
        }
        replacer.accept(startPos.up(), ModBlocks.REVELATION_CORE.getDefaultState());
        int h1=random.nextInt(2);
        for (int y = 4; y < height-3-h1; y++) {
            for(int x=-1;x<=1;x++){
                for(int z=-1;z<=1;z++){
                    if(x*z==0){
                        this.getAndSetState(world, replacer, random, startPos.add(x,y,z), config);
                    }
                }
            }
        }
        for(int y=height-3-h1;y<height;y++){
            this.getAndSetState(world, replacer, random, startPos.up(y), config);
        }

        // 添加主干顶部的树叶节点
        foliageNodes.add(new FoliagePlacer.TreeNode(startPos.up(height+1), 1, false));

        // 生成分支
        int b1=random.nextInt(1);
        for(int x=-1;x<=1;x++){
            for(int z=-1;z<=1;z++){
                if(x*z==0){
                    for(int i=0;i<6+h1;i++){
                        int i1 = height - i * i / (i + 3 + b1);BlockPos branchPos = startPos.add(x * i, i1, z * i);

                        BlockState trunkState = config.trunkProvider.get(random, branchPos);
                        // 根据延伸方向设置轴向
                        Direction.Axis axis;
                        if (x != 0) {
                            axis = Direction.Axis.X;       // 沿X方向延伸 → 轴向X
                        } else if (z != 0) {
                            axis = Direction.Axis.Z;       // 沿Z方向延伸 → 轴向Z
                        } else {
                            axis = Direction.Axis.Y;       // 中心点（x=0,z=0）保持竖直
                        }
                        trunkState = trunkState.with(Properties.AXIS, axis);
                        // 直接放置自定义状态的方块
                        replacer.accept(branchPos, trunkState);
                        if(i==5+h1||i==(6+h1)/2)
                            foliageNodes.add(new FoliagePlacer.TreeNode(startPos.add(x*i, i1 +1,z*i), 1, false));
                    }
                }
            }
        }

        return foliageNodes;
    }
}