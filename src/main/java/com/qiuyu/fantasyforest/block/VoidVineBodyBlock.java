//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.qiuyu.fantasyforest.block;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class VoidVineBodyBlock extends PlantBlock {
    static final VoxelShape SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);
    public VoidVineBodyBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public boolean canPlaceAt(BlockState state,WorldView world,BlockPos pos){
        return world.getBlockState(pos.up()).isOf(ModBlocks.VOID_VINE_BODY)||world.getBlockState(pos.up()).isOf(ModBlocks.VOID_LEAVES);
    }
}
