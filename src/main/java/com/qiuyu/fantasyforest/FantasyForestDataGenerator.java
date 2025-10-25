package com.qiuyu.fantasyforest;

import com.qiuyu.fantasyforest.datagen.*;
import com.qiuyu.fantasyforest.world.ModConfiguredFeatures;
import com.qiuyu.fantasyforest.world.ModPlacedFeatures;
//import com.qiuyu.fantasyforest.world.biome.ModBiomes;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class FantasyForestDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModBlockTagsProvider::new);
        pack.addProvider(ModItemTagsProvider::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModZhCnLangProvider::new);
        pack.addProvider(ModEnUsLangProvider::new);
        pack.addProvider(ModBlockLootTableProvider::new);
        pack.addProvider(ModWorldGenerator::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder){
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::boostrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::boostrap);
//        registryBuilder.addRegistry(RegistryKeys.BIOME, ModBiomes::boostrap);
    }
}
