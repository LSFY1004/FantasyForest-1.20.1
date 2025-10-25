//package com.qiuyu.fantasyforest.world.biome;
//
//import com.qiuyu.fantasyforest.FantasyForest;
//import com.qiuyu.fantasyforest.world.ModPlacedFeatures;
//import net.minecraft.entity.EntityType;
//import net.minecraft.entity.SpawnGroup;
//import net.minecraft.registry.Registerable;
//import net.minecraft.registry.RegistryKey;
//import net.minecraft.registry.RegistryKeys;
//import net.minecraft.sound.BiomeMoodSound;
//import net.minecraft.util.Identifier;
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.biome.BiomeEffects;
//import net.minecraft.world.biome.GenerationSettings;
//import net.minecraft.world.biome.SpawnSettings;
//import net.minecraft.world.gen.GenerationStep;
//
//public class ModBiomes {
//    public static final RegistryKey<Biome> END_PLAINS = RegistryKey.of(RegistryKeys.BIOME,
//            new Identifier(FantasyForest.MOD_ID,"end_plains"));
//
//    public static void boostrap(Registerable<Biome> context){
//        context.register(END_PLAINS,endPlains(context));
//    }
//
//    public static Biome endPlains(Registerable<Biome> context){
//
//        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder().spawn(SpawnGroup.MONSTER,
//                        new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 4, 4))
//                .creatureSpawnProbability(0.07f);
//
//        GenerationSettings.LookupBackedBuilder generationSettings =
//                new GenerationSettings.LookupBackedBuilder(
//                        context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
//                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
//                );
//
//        // 明确设置特征生成方式（使用权重而非概率）
//        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION,
//                ModPlacedFeatures.END_TREE_PLACED_KEY);
//        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION,
//                ModPlacedFeatures.LARGE_END_TREE_PLACED_KEY);
//        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION,
//                ModPlacedFeatures.MUTATED_END_TREE_PLACED_KEY);
//        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION,
//                ModPlacedFeatures.MUTATED_LARGE_END_TREE_PLACED_KEY);
//
//        BiomeEffects.Builder effects = new BiomeEffects.Builder()
//                .waterColor(0x3F76E4)
//                .waterFogColor(0x050533)
//                .fogColor(0xA080A0)
//                .skyColor(0x000000)
//                .moodSound(BiomeMoodSound.CAVE)
//                .grassColor(0x7E03FC)  // 必须设置
//                .foliageColor(0x7E03FC); // 必须设置
//
//        // 3. 完整生物群系构建
//        return new Biome.Builder()
//                .precipitation(false)
//                .temperature(0.5f)
//                .downfall(0.0f)
//                .effects(effects.build())
//                .spawnSettings(spawnSettings.build())
//                .generationSettings(generationSettings.build())
//                .build();
//    }
//}
