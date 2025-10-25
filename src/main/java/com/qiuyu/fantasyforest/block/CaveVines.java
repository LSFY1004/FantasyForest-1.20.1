package com.qiuyu.fantasyforest.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface CaveVines {
    ActionResult pickBerries(BlockState state, World world, BlockPos pos);

    boolean hasBerries(BlockState state);
}
