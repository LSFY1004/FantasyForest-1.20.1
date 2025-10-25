//package com.qiuyu.fantasyforest.world.biome;
//
//import com.qiuyu.fantasyforest.world.ModPlacedFeatures;
//import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
//import net.minecraft.entity.EntityType;
//import net.minecraft.entity.SpawnGroup;
//import net.minecraft.registry.RegistryEntryLookup;
//import net.minecraft.registry.RegistryKey;
//import net.minecraft.registry.RegistryKeys;
//import net.minecraft.util.Identifier;
//import net.minecraft.world.biome.*;
//import net.minecraft.world.gen.GenerationStep;
//import net.minecraft.world.gen.carver.ConfiguredCarver;
//import net.minecraft.world.gen.feature.EndPlacedFeatures;
//import net.minecraft.world.gen.feature.PlacedFeature;
//
//public class EndPlainsBiome {
//    public static final RegistryKey<Biome> END_PLAINS = RegistryKey.of(
//            RegistryKeys.BIOME,
//            new Identifier("yourmodid", "end_plains")
//    );
//
//    public static Biome create(RegistryEntryLookup<PlacedFeature> featureLookup,
//                               RegistryEntryLookup<ConfiguredCarver<?>> carverLookup) {
//        // 基于末地高地创建，但移除紫颂树
//        GenerationSettings.LookupBackedBuilder builder =
//                new GenerationSettings.LookupBackedBuilder(featureLookup, carverLookup);
//
//        // 添加自定义特征（平坦地形）
//        builder.feature(GenerationStep.Feature.RAW_GENERATION,
//                EndPlacedFeatures.END_ISLAND_DECORATED); // 平坦岛屿
//
//        return new Biome.Builder()
//                .precipitation(false)
//                .temperature(0.5F)
//                .downfall(0.0F)
//                .effects(new BiomeEffects.Builder()
//                        .waterColor(0x3F76E4)
//                        .waterFogColor(0x050533)
//                        .fogColor(0xA080A0)
//                        .skyColor(0x000000)
//                        .grassColor(0xE0E0E0) // 灰白色草地
//                        .build())
//                .spawnSettings(createSpawnSettings())
//                .generationSettings(builder.build())
//                .build();
//    }
//
//    private static SpawnSettings createSpawnSettings() {
//        return new SpawnSettings.Builder()
//                .spawn(SpawnGroup.MONSTER,
//                        new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 1, 1, 2)) // 减少末影人
//                .build();
//    }
//}