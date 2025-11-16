package com.qiuyu.fantasyforest;

import com.qiuyu.fantasyforest.block.ModBlocks;
import com.qiuyu.fantasyforest.block.entity.ModBlockEntities;
import com.qiuyu.fantasyforest.effect.ModEffects;
import com.qiuyu.fantasyforest.effect.ModEventListeners;
import com.qiuyu.fantasyforest.entity.ModEntities;
import com.qiuyu.fantasyforest.entity.vehicle.ModBoats;
import com.qiuyu.fantasyforest.item.MODItems;
import com.qiuyu.fantasyforest.item.ModItemGroups;
import com.qiuyu.fantasyforest.item.spetial.ProgressiveTaskScheduler;
import com.qiuyu.fantasyforest.recipe.ModRecipes;
import com.qiuyu.fantasyforest.screen.ModScreenHandlers;
import com.qiuyu.fantasyforest.world.gen.foliage.ModFoliagePlacerType;
import com.qiuyu.fantasyforest.world.gen.treedecorator.ModTreeDecoratorType;
import com.qiuyu.fantasyforest.world.gen.trunk.ModTrunkPlacerType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FantasyForest implements ModInitializer {
	public static final String MOD_ID = "fantasy-forest";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModBlocks.registerModBlocks();
		MODItems.registerItems();
		ModItemGroups.registerGroups();
		ModTrunkPlacerType.register();
		ModFoliagePlacerType.register();
		ModTreeDecoratorType.registerTreeDecorators();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerModScreenHandlers();
        ModRecipes.registerRecipes();
        ModEffects.registerEffects();
        ModEventListeners.registerEvents();
		ModEntities.registerEntities();
		ModBoats.registerBoats();
        ProgressiveTaskScheduler.register();

        StrippableBlockRegistry.register(ModBlocks.PERMEAROMA_LOG,ModBlocks.STRIPPED_PERMEAROMA_LOG);
        StrippableBlockRegistry.register(ModBlocks.PERMEAROMA_WOOD,ModBlocks.STRIPPED_PERMEAROMA_WOOD);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PERMEAROMA_LOG,5,5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PERMEAROMA_WOOD,5,5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_PERMEAROMA_LOG,5,5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_PERMEAROMA_WOOD,5,5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PERMEAROMA_LEAVES,30,60);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PERMEAROMA_PLANKS,5,20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PERMEAROMA_TRAPDOOR,5,20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PERMEAROMA_STAIRS,5,20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PERMEAROMA_FENCE_GATE,5,20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PERMEAROMA_PRESSURE_PLATE,5,20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PERMEAROMA_SLAB,5,20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PERMEAROMA_BUTTON,5,20);

        StrippableBlockRegistry.register(ModBlocks.END_LOG,ModBlocks.STRIPPED_END_LOG);
		StrippableBlockRegistry.register(ModBlocks.END_WOOD,ModBlocks.STRIPPED_END_WOOD);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.END_LOG,3,4);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.END_WOOD,3,4);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_END_LOG,3,4);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_END_WOOD,3,4);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.END_LEAVES,30,60);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.END_PLANKS,3,16);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.END_WOODEN_TRAPDOOR,3,16);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.END_WOODEN_STAIRS,3,16);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.END_WOODEN_FENCE_GATE,3,16);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.END_WOODEN_PRESSURE_PLATE,3,16);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.END_WOODEN_SLAB,3,16);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.END_WOODEN_BUTTON,3,16);

		StrippableBlockRegistry.register(ModBlocks.MUTATED_END_LOG,ModBlocks.STRIPPED_MUTATED_END_LOG);
		StrippableBlockRegistry.register(ModBlocks.MUTATED_END_WOOD,ModBlocks.STRIPPED_MUTATED_END_WOOD);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.MUTATED_END_LOG,3,4);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.MUTATED_END_WOOD,3,4);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_MUTATED_END_LOG,3,4);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_MUTATED_END_WOOD,3,4);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.MUTATED_END_PLANKS,3,16);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.MUTATED_END_WOODEN_TRAPDOOR,3,16);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.MUTATED_END_WOODEN_STAIRS,3,16);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.MUTATED_END_WOODEN_FENCE_GATE,3,16);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.MUTATED_END_WOODEN_PRESSURE_PLATE,3,16);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.MUTATED_END_WOODEN_SLAB,3,16);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.MUTATED_END_WOODEN_BUTTON,3,16);

        StrippableBlockRegistry.register(ModBlocks.VOID_LOG,ModBlocks.STRIPPED_VOID_LOG);
        StrippableBlockRegistry.register(ModBlocks.VOID_WOOD,ModBlocks.STRIPPED_VOID_WOOD);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.VOID_LOG,2,3);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.VOID_WOOD,2,3);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_VOID_LOG,2,3);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_VOID_WOOD,2,3);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.VOID_LEAVES,30,60);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.VOID_PLANKS,2,12);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.VOID_WOODEN_TRAPDOOR,2,12);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.VOID_WOODEN_STAIRS,2,12);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.VOID_WOODEN_FENCE_GATE,2,12);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.VOID_WOODEN_PRESSURE_PLATE,2,12);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.VOID_WOODEN_SLAB,2,12);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.VOID_WOODEN_BUTTON,2,12);
		LOGGER.info("Hello Fabric world!");
	}
}