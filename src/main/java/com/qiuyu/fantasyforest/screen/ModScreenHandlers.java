// ModScreenHandlers.java
package com.qiuyu.fantasyforest.screen;

import com.qiuyu.fantasyforest.FantasyForest;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static ScreenHandlerType<ArborealCraftingTableScreenHandler> ARBOREAL_CRAFTING_TABLE_SCREEN_HANDLER;

    public static void registerModScreenHandlers() {
        ARBOREAL_CRAFTING_TABLE_SCREEN_HANDLER = Registry.register(
                Registries.SCREEN_HANDLER,
                new Identifier(FantasyForest.MOD_ID, "arboreal_crafting_table"),
                new ExtendedScreenHandlerType<>(ArborealCraftingTableScreenHandler::new)
        );
    }
}