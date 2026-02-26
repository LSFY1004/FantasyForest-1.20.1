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
    // 冷却时间（单位：tick），20 tick = 1秒，这里设为30秒（可根据需要调整）
    private static final int COOLDOWN_TICKS = 600;

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

        // 检查玩家是否处于冷却中（原版机制会自动阻止使用，但双重检查更安全）
        if (player != null && player.getItemCooldownManager().isCoolingDown(this)) {
            // 可选的提示信息（可不加，原版会显示物品变灰）
            // player.sendMessage(Text.literal("§c启示之种正在冷却中！"), true);
            return ActionResult.SUCCESS;
        }

        // 消耗物品（创造模式不消耗）
        if (player != null && !player.isCreative()) {
            player.getStackInHand(context.getHand()).decrement(1);
        }

        // 调度任务
        RevelationLandTask task = new RevelationLandTask((ServerWorld) world, pos, 50);
        ProgressiveTaskScheduler.scheduleTask(task);

        // 设置物品冷却
        if (player != null) {
            // 创造模式可设置较短冷却（例如10秒），方便测试；生存模式使用完整冷却
            if (player.isCreative()) {
                player.getItemCooldownManager().set(this, 200); // 10秒
            } else {
                player.getItemCooldownManager().set(this, COOLDOWN_TICKS);
            }
        }

        return ActionResult.SUCCESS;
    }
}