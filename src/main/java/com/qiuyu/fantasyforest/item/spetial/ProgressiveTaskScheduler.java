package com.qiuyu.fantasyforest.item.spetial;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import java.util.LinkedList;
import java.util.Queue;

public class ProgressiveTaskScheduler {
    private static final Queue<ILongRunningTask> TASKS = new LinkedList<>();

    public static void scheduleTask(ILongRunningTask task) {
        TASKS.add(task);
    }

    public static void register() {
        // 注册到服务器刻开始事件
        ServerTickEvents.START_SERVER_TICK.register(ProgressiveTaskScheduler::onServerTick);
    }

    private static void onServerTick(MinecraftServer server) {
        if (!TASKS.isEmpty()) {
            // 每帧只处理一个任务的一个阶段，避免单帧负载过高
            ILongRunningTask task = TASKS.peek();
            if (task.executeNextStage(server)) {
                // 如果任务已完成，将其移出队列
                TASKS.poll();
            }
            // 如果任务未完成，下一帧继续执行
        }
    }
}
