package com.qiuyu.fantasyforest.item.spetial;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SeedOfRevelationItem extends Item {
    public SeedOfRevelationItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        BlockState state = world.getBlockState(pos);

        // 确保只在服务端执行逻辑，并检查是否在泥土上使用
        if (world.isClient || !state.isIn(BlockTags.DIRT)) {
            return ActionResult.SUCCESS;
        }

        // 消耗物品（创造模式不消耗）
        if (player != null && !player.isCreative()) {
            player.getStackInHand(context.getHand()).decrement(1);
        }

        // 调度任务
        RevelationLandTask task = new RevelationLandTask((ServerWorld) world, pos, 50);
        ProgressiveTaskScheduler.scheduleTask(task);

        return ActionResult.SUCCESS;
    }
}
