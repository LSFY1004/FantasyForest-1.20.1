package com.qiuyu.fantasyforest.block.custom;

import com.qiuyu.fantasyforest.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class DeadEndLichenBlock extends EndLichenBlock{
    public DeadEndLichenBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Random random = world.random;

        if (itemStack.isOf(Items.BONE_MEAL)){
            if (world instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(
                        ParticleTypes.HAPPY_VILLAGER,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        10,
                        0.5, 0.5, 0.5,
                        0.05
                );
            }
            world.playSound(null, pos, SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if(random.nextInt(2)==0)return convertToLive(world, pos, player, hand);
        }
        return ActionResult.PASS;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
    }

    private ActionResult convertToLive(World world, BlockPos pos, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            world.setBlockState(pos, ModBlocks.END_LICHEN.getDefaultState());
            ItemStack itemStack = player.getStackInHand(hand);
            if (!player.isCreative()) itemStack.decrement(1);
        }
        return ActionResult.success(world.isClient);
    }
}
