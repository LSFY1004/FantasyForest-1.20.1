package com.qiuyu.fantasyforest.world.tree;

import com.qiuyu.fantasyforest.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

import java.util.Arrays;
import java.util.List;

public class EndTreeSaplingBlock extends SaplingBlock {
    private static final List<Block> ALLOWED_GROUND = Arrays.asList(
            Blocks.END_STONE,
            ModBlocks.END_LICHEN,
            ModBlocks.DEAD_END_LICHEN);

    public EndTreeSaplingBlock(SaplingGenerator generator, Settings settings) {
        super(generator, settings);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos downPos = pos.down();
        BlockState downState = world.getBlockState(downPos);

        // 检查下方方块是否在允许列表中
        return ALLOWED_GROUND.contains(downState.getBlock());
    }
}
