package com.qiuyu.fantasyforest.world.tree;

import com.qiuyu.fantasyforest.world.ModConfiguredFeatures;
import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class EndTreeSaplingGenerator extends LargeTreeSaplingGenerator {

    @Override
    protected @Nullable RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return random.nextInt(64)==0?ModConfiguredFeatures.MUTATED_END_TREE_KEY:ModConfiguredFeatures.END_TREE_KEY;
    }
    @Override
    protected RegistryKey<ConfiguredFeature<?, ?>> getLargeTreeFeature(Random random) {
        return random.nextInt(16)==0?ModConfiguredFeatures.MUTATED_LARGE_END_TREE_KEY:ModConfiguredFeatures.LARGE_END_TREE_KEY;
    }
}
