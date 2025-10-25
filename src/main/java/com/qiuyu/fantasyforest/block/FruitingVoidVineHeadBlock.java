package com.qiuyu.fantasyforest.block;

import com.qiuyu.fantasyforest.item.MODItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class FruitingVoidVineHeadBlock extends VoidVineHeadBlock {
    public FruitingVoidVineHeadBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState());
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // 确保只在服务器端处理
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }

        // 采摘浆果
        ActionResult result = this.pickBerries(state, world, pos);
        if (result.isAccepted()) {
            // 采摘后变为普通藤头
            world.setBlockState(pos, ModBlocks.VOID_VINE_HEAD.getDefaultState());
            return ActionResult.CONSUME;
        }
        return result;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return super.getOutlineShape(state, world, pos, context);
    }

    @Override
    public ActionResult pickBerries(BlockState state, World world, BlockPos pos) {
        if (!world.isClient) {
            // 生成果实掉落
            Block.dropStack(world, pos, new ItemStack(MODItems.VOID_FRUIT));
        }

        // 播放采摘声音
        world.playSound(null, pos, SoundEvents.BLOCK_CAVE_VINES_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 1.0F);

        // 返回成功
        return ActionResult.success(world.isClient);
    }

    @Override
    public boolean hasBerries(BlockState state) {
        return true;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);

        // 破坏时额外掉落果实
        if (!world.isClient) {
                // 普通头部：掉落1个果实
            Block.dropStack(world, pos, new ItemStack(MODItems.VOID_FRUIT));
        }
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        return false; // 结果状态不可施肥
    }
}
