package com.qiuyu.fantasyforest.world.biome;

import com.qiuyu.fantasyforest.FantasyForest;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;

public class ModBiomes {
    public static final RegistryKey<Biome> LAND_OF_REVELATION = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(FantasyForest.MOD_ID,"land_of_revelation"));
    public static final RegistryKey<Biome> END_TREE_ISLAND = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(FantasyForest.MOD_ID,"end_tree_island"));

    public static void boostrap(Registerable<Biome> context){
        context.register(LAND_OF_REVELATION, landOfRevelation(context));
        context.register(END_TREE_ISLAND, endTreeIsland(context));
    }

    public static Biome landOfRevelation(Registerable<Biome> context){
        return new Biome.Builder()
                .precipitation(true)
                .temperature(0.5f)
                .downfall(0.8f)
                .effects(new BiomeEffects.Builder().fogColor(0xC0D8FF).skyColor(0x7BA4FF)
                        .waterFogColor(0x050533).waterColor(0x3F76E4).build())
                .spawnSettings(new SpawnSettings.Builder().build())
                .generationSettings(new GenerationSettings.Builder().build())
                .build();
    }

    public static Biome endTreeIsland(Registerable<Biome> context){

        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder().spawn(SpawnGroup.MONSTER,
                        new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 1, 3))
                .creatureSpawnProbability(0.07f);

        BiomeEffects.Builder effects = new BiomeEffects.Builder()
                .waterColor(0x3F76E4)
                .waterFogColor(0x050533)
                .fogColor(0xA080A0)
                .skyColor(0x000000);

        // 3. 完整生物群系构建
        return new Biome.Builder()
                .precipitation(false)
                .temperature(0.5f)
                .downfall(0.0f)
                .effects(effects.build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(new GenerationSettings.Builder().build())
                .build();
    }
}
