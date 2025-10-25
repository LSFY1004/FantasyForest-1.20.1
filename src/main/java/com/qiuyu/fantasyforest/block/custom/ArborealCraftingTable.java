package com.qiuyu.fantasyforest.block.custom;

import com.qiuyu.fantasyforest.block.entity.ArborealCraftingTableEntity;
import com.qiuyu.fantasyforest.block.entity.ModBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ArborealCraftingTable extends BlockWithEntity implements BlockEntityProvider {

    public static final VoxelShape SHAPE = Block.createCuboidShape(0,0,0,16,10,16);

    public ArborealCraftingTable(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if(state.getBlock()!=newState.getBlock()){
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof ArborealCraftingTableEntity craftingTableEntity) {

                // 1. 手动散射物品，排除输出槽
                // 遍历所有槽位，只散射输入槽（0-9，其中10是输出槽，我们不散射它）
                for (int i = 0; i < craftingTableEntity.size() - 1; i++) { // 注意这里是 size() - 1
                    ItemStack stack = craftingTableEntity.getStack(i);
                    if (!stack.isEmpty()) {
                        // 在每个槽位的位置周围随机散射物品
                        ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                    }
                }
                // 2. 更新比较器（如果用到的话）
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient){
            NamedScreenHandlerFactory screenHandlerFactory = (ArborealCraftingTableEntity)world.getBlockEntity(pos);
            if(screenHandlerFactory!=null){
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ArborealCraftingTableEntity(pos,state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.ARBOREAL_CRAFTING_TABLE,
                ((world1, pos, state1, blockEntity) -> blockEntity.tick(world1,pos,state1,blockEntity)));
    }
}
