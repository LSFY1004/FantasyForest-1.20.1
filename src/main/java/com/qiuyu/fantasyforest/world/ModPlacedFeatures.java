package com.qiuyu.fantasyforest.world;

import com.qiuyu.fantasyforest.FantasyForest;
import com.qiuyu.fantasyforest.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {

    public static final RegistryKey<PlacedFeature> END_TREE_PLACED_KEY = registerKey("end_tree_placed");
    public static final RegistryKey<PlacedFeature> MUTATED_END_TREE_PLACED_KEY = registerKey("mutated_end_tree_placed");
    public static final RegistryKey<PlacedFeature> LARGE_END_TREE_PLACED_KEY = registerKey("large_end_tree_placed");
    public static final RegistryKey<PlacedFeature> MUTATED_LARGE_END_TREE_PLACED_KEY = registerKey("mutated_large_end_tree_placed");

    public static void boostrap(Registerable<PlacedFeature> context){
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
//        register(context,END_TREE_PLACED_KEY,configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.END_TREE_KEY),
//                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(1,0.6F,1),
//                        ModBlocks.END_TREE_SAPLING));
//        register(context,MUTATED_END_TREE_PLACED_KEY,configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.MUTATED_END_TREE_KEY),
//                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(0,0.1F,1),
//                        ModBlocks.END_TREE_SAPLING));
//        register(context,LARGE_END_TREE_PLACED_KEY,configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.LARGE_END_TREE_KEY),
//                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(0,0.3F,1),
//                        ModBlocks.END_TREE_SAPLING));
//        register(context,MUTATED_LARGE_END_TREE_PLACED_KEY,configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.MUTATED_LARGE_END_TREE_KEY),
//                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(0,0.1F,1),
//                        ModBlocks.END_TREE_SAPLING));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name){
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(FantasyForest.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?,?>> configuration,
                                 List<PlacementModifier>modifiers){
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
