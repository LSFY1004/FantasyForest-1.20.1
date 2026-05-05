package com.qiuyu.fantasyforest.world.tree;

import com.qiuyu.fantasyforest.world.ModConfiguredFeatures;
import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class AphrodisiaSaplingGenerator extends LargeTreeSaplingGenerator {

    @Override
    protected @Nullable RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return ModConfiguredFeatures.APHRODISIA_KEY;
    }

    @Override
    protected @Nullable RegistryKey<ConfiguredFeature<?, ?>> getLargeTreeFeature(Random random) {
        return ModConfiguredFeatures.LARGE_APHRODISIA_KEY;
    }
}
