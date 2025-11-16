package com.qiuyu.fantasyforest.item.spetial;

import net.minecraft.server.MinecraftServer;

public interface ILongRunningTask {
    boolean executeNextStage(MinecraftServer server);
}
