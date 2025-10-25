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
import java.util.OptionalInt;
import java.util.function.BiConsumer;

public class VoidTreeTrunkPlacer extends TrunkPlacer {
    public static final Codec<VoidTreeTrunkPlacer> CODEC = RecordCodecBuilder.create(
            instance -> fillTrunkPlacerFields(instance).apply(instance, VoidTreeTrunkPlacer::new));

    public VoidTreeTrunkPlacer(int i, int j, int k) {
        super(i, j, k);
    }

    public void generateVoidRoots(int x,int rl,int z,TestableWorld world, BiConsumer<BlockPos, BlockState> replacer,
                                   Random random, BlockPos startPos, TreeFeatureConfig config){
        int ox=0,oz=0;
        if(x>0&&z==0)oz--;
        else if (x<0&&z==0)oz++;
        else if (z>0&&x==0)ox--;
        else if (z<0&&x==0)ox++;
        for(int r=0;r<rl;r++){
            if(rl-r<r){ox=0;oz=0;}
            if(canReplace(world,startPos.add(ox+x*(r+1),0,oz+z*(r+1))))
                setToDirt(world, replacer, random, startPos.add(ox+x*(r+1),0,oz+z*(r+1)), config);
        }
    }
    @Override
    protected TrunkPlacerType<?> getType() {
        return ModTrunkPlacerType.VOID_TREE_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer,
                                                 Random random, int height, BlockPos startPos, TreeFeatureConfig config) {
        List<FoliagePlacer.TreeNode> list = Lists.newArrayList();
        Direction direction = Direction.Type.HORIZONTAL.random(random);
        int i = height - random.nextInt(4) - 1;
        int j = 3 - random.nextInt(3);
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        int k = startPos.getX();
        int l = startPos.getZ();
        OptionalInt optionalInt = OptionalInt.empty();

        for (int m = 2; m < height+2; m++) {
            int n = startPos.getY() + m;
            if (m >= i && j > 0) {
                k += direction.getOffsetX();
                l += direction.getOffsetZ();
                j--;
            }

            if (this.getAndSetState(world, replacer, random, mutable.set(k, n, l), config)) {
                optionalInt = OptionalInt.of(n + 1);
            }
        }

        if (optionalInt.isPresent()) {
            list.add(new FoliagePlacer.TreeNode(new BlockPos(k, optionalInt.getAsInt(), l), 1, false));
        }

        k = startPos.getX();
        l = startPos.getZ();
        Direction direction2 = Direction.Type.HORIZONTAL.random(random);
        if (direction2 != direction) {
            int nx = i - random.nextInt(2) - 1;
            int o = 1 + random.nextInt(3);
            optionalInt = OptionalInt.empty();

            for (int p = nx+1; p < height+2 && o > 0; o--) {
                if (p >= 3) {
                    int q = startPos.getY() + p;
                    k += direction2.getOffsetX();
                    l += direction2.getOffsetZ();
                    if (this.getAndSetState(world, replacer, random, mutable.set(k, q, l), config)) {
                        optionalInt = OptionalInt.of(q + 1);
                    }
                }

                p++;
            }

            if (optionalInt.isPresent()) {
                list.add(new FoliagePlacer.TreeNode(new BlockPos(k, optionalInt.getAsInt(), l), 0, false));
            }
        }


        if(canReplace(world,startPos.add(0,1,1))){setToDirt(world, replacer, random, startPos.add(0,1,1), config);}
        if(canReplace(world,startPos.add(0,1,-1))){setToDirt(world, replacer, random, startPos.add(0,1,-1), config);}
        if(canReplace(world,startPos.add(1,1,0))){setToDirt(world, replacer, random, startPos.add(1,1,0), config);}
        if(canReplace(world,startPos.add(-1,1,0))){setToDirt(world, replacer, random, startPos.add(-1,1,0), config);}

        int rl = height/3+1+random.nextInt(2);
        Direction rd1 = Direction.Type.HORIZONTAL.random(random);
        int x1 = rd1.getOffsetX();
        int z1 = rd1.getOffsetZ();
        generateVoidRoots(x1,rl,z1,world,replacer,random,startPos,config);
        Direction rd2 = Direction.Type.HORIZONTAL.random(random);
        int x2 = rd2.getOffsetX();
        int z2 = rd2.getOffsetZ();
        if(x2==x1&&z2==z1){
            x2=-x2;
            z2=-z2;
        }
        generateVoidRoots(x2,rl,z2,world,replacer,random,startPos,config);
        int x3=-x1-x2;
        int z3=-z1-z2;
        generateVoidRoots(x3,rl-1,z3,world,replacer,random,startPos,config);
        BlockState phosphorState = ModBlocks.PHOSPHOR.getDefaultState()
                .with(PhosphorBlock.LIGHT_STATE, 2); // 状态2=15亮度
        replacer.accept(startPos.up(), phosphorState);
        return list;
    }
}
