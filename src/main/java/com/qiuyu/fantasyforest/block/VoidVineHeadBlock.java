package com.qiuyu.fantasyforest.block;

import com.qiuyu.fantasyforest.item.MODItems;
import net.minecraft.block.*;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class VoidVineHeadBlock extends PlantBlock implements Fertilizable, CaveVines {

    public VoidVineHeadBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoidVineBodyBlock.SHAPE; // 使用相同的碰撞箱
    }

    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(MODItems.VOID_FRUIT);
    }

    @Override
    public ActionResult pickBerries(BlockState state, World world, BlockPos pos) {
        // 普通头部没有浆果可采摘
        return ActionResult.PASS;
    }

    @Override
    public boolean hasBerries(BlockState state) {
        return false;
    }

    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int currentAge;

        for(currentAge=0;currentAge<=10;currentAge++){
            if(!world.getBlockState(pos.up(1+currentAge)).isOf(ModBlocks.VOID_VINE_BODY)){
                break;
            }
        }

        if (currentAge >= 10) {
            // 替换为结果头部方块
            world.setBlockState(pos, ModBlocks.FRUITING_VOID_VINE_HEAD.getDefaultState());
        } else if (currentAge >= 7) {
            if (random.nextFloat() < 0.5F) {
                // 替换为结果头部方块
                world.setBlockState(pos, ModBlocks.FRUITING_VOID_VINE_HEAD.getDefaultState());
            } else {
                if(world.getBlockState(pos.down()).isAir())world.setBlockState(pos, ModBlocks.VOID_VINE_BODY.getDefaultState());
                if(world.getBlockState(pos.down()).isAir())world.setBlockState(pos.down(), ModBlocks.VOID_VINE_HEAD.getDefaultState());
            }
        } else {
            if(world.getBlockState(pos.down()).isAir())world.setBlockState(pos, ModBlocks.VOID_VINE_BODY.getDefaultState());
            if(world.getBlockState(pos.down()).isAir())world.setBlockState(pos.down(), ModBlocks.VOID_VINE_HEAD.getDefaultState());
        }
    }

    @Override
    public boolean canPlaceAt(BlockState state,WorldView world,BlockPos pos){
        return world.getBlockState(pos.up()).isOf(ModBlocks.VOID_VINE_BODY)||world.getBlockState(pos.up()).isOf(ModBlocks.VOID_LEAVES);
    }
}