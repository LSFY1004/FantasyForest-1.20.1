package com.qiuyu.fantasyforest.world;

import com.google.common.collect.ImmutableList;
import com.qiuyu.fantasyforest.FantasyForest;
import com.qiuyu.fantasyforest.block.ModBlocks;
import com.qiuyu.fantasyforest.world.gen.foliage.EllipsoidFoliagePlacer;
import com.qiuyu.fantasyforest.world.gen.treedecorator.VoidVineDecorator;
import com.qiuyu.fantasyforest.world.gen.trunk.*;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.CherryFoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?,?>> PERMEAROMA_KEY = registryKey("permearoma");
    public static final RegistryKey<ConfiguredFeature<?,?>> END_TREE_KEY = registryKey("end_tree");
    public static final RegistryKey<ConfiguredFeature<?,?>> LARGE_END_TREE_KEY = registryKey("large_end_tree");
    public static final RegistryKey<ConfiguredFeature<?,?>> MUTATED_END_TREE_KEY = registryKey("mutated_end_tree");
    public static final RegistryKey<ConfiguredFeature<?,?>> MUTATED_LARGE_END_TREE_KEY = registryKey("mutated_large_end_tree");
    public static final RegistryKey<ConfiguredFeature<?,?>> VOID_TREE_KEY = registryKey("void_tree");
    public static final RegistryKey<ConfiguredFeature<?,?>> LARGE_VOID_TREE_KEY = registryKey("large_void_tree");
    public static final RegistryKey<ConfiguredFeature<?,?>> REVELATION_TREE_KEY = registryKey("revelation_tree");

    public static void boostrap(Registerable<ConfiguredFeature<?,?>> context){
        register(context, REVELATION_TREE_KEY,Feature.TREE,new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.REVELATION_LOG),
                new RevelationTrunkPlacer(11,3,0),
                BlockStateProvider.of(ModBlocks.REVELATION_LEAVES),
                new CherryFoliagePlacer(ConstantIntProvider.create(2),ConstantIntProvider.create(0),ConstantIntProvider.create(5),0.5F,0.5F,0.5F,0.5F),
                new TwoLayersFeatureSize(1,0,2)
        )
                .dirtProvider(BlockStateProvider.of(Blocks.DIRT))
                .ignoreVines()
                .build());
        register(context,PERMEAROMA_KEY,Feature.TREE,new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.PERMEAROMA_LOG),
                new PermearomaTrunkPlacer(11,3,0),
                BlockStateProvider.of(ModBlocks.PERMEAROMA_LEAVES),
                new CherryFoliagePlacer(ConstantIntProvider.create(2),ConstantIntProvider.create(0),ConstantIntProvider.create(5),0.5F,0.5F,0.5F,0.5F),
                new TwoLayersFeatureSize(1,0,2)
        )
                .dirtProvider(BlockStateProvider.of(Blocks.DIRT))
                .ignoreVines()
                .build());
        register(context,END_TREE_KEY,Feature.TREE,new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.END_LOG),
                new ForkingTrunkPlacer(6,5,3),
                BlockStateProvider.of(ModBlocks.END_LEAVES),
                new LargeOakFoliagePlacer(ConstantIntProvider.create(2),ConstantIntProvider.create(0),3),
                new TwoLayersFeatureSize(1,0,2)
        )
                .dirtProvider(BlockStateProvider.of(Blocks.END_STONE))
                .ignoreVines()
                .build());
        register(context, LARGE_END_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.END_LOG),
                new LargeEndTreeTrunkPlacer(23, 12, 3),
                BlockStateProvider.of(ModBlocks.END_LEAVES),
                new EllipsoidFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 0.7F,1.0F),
                new TwoLayersFeatureSize(1, 1, 4))
                .dirtProvider(BlockStateProvider.of(Blocks.END_STONE))
                .ignoreVines()
                .build());
        register(context,MUTATED_END_TREE_KEY,Feature.TREE,new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.MUTATED_END_LOG),
                new EndTreeTrunkPlacer(6,5,3),
                BlockStateProvider.of(ModBlocks.END_LEAVES),
                new LargeOakFoliagePlacer(ConstantIntProvider.create(2),ConstantIntProvider.create(0),3),
                new TwoLayersFeatureSize(1,0,2)
        )
                .dirtProvider(BlockStateProvider.of(Blocks.END_STONE))
                .ignoreVines()
                .build());
        register(context, MUTATED_LARGE_END_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.MUTATED_END_LOG),
                new LargeEndTreeTrunkPlacer(23, 12, 3), // 更高的树干
                BlockStateProvider.of(ModBlocks.END_LEAVES),
                new EllipsoidFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 0.7F,1.0F),
                new TwoLayersFeatureSize(1, 1, 4))
                .dirtProvider(BlockStateProvider.of(Blocks.END_STONE))
                .ignoreVines()
                .build());

        register(context,VOID_TREE_KEY,Feature.TREE,new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.VOID_LOG),
                new VoidTreeTrunkPlacer(8,3,2),
                BlockStateProvider.of(ModBlocks.VOID_LEAVES),
                new CherryFoliagePlacer(ConstantIntProvider.create(2),ConstantIntProvider.create(0),ConstantIntProvider.create(5),0.5F,0.5F,1.0F,1.0F),
                new TwoLayersFeatureSize(2,1,3))
                .dirtProvider(BlockStateProvider.of(ModBlocks.VOID_ROOTS))
                .ignoreVines()
                .decorators(ImmutableList.of(new VoidVineDecorator(0.05F)))
                .build());
        register(context, LARGE_VOID_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.VOID_LOG),
                new LargeVoidTreeTrunkPlacer(23, 6, 3), // 更高的树干
                BlockStateProvider.of(ModBlocks.VOID_LEAVES),
                new CherryFoliagePlacer(ConstantIntProvider.create(2),ConstantIntProvider.create(0),ConstantIntProvider.create(5),0.5F,0.5F,1.0F,1.0F),
                new TwoLayersFeatureSize(1, 1, 2))
                .dirtProvider(BlockStateProvider.of(ModBlocks.VOID_ROOTS))
                .ignoreVines()
                .decorators(ImmutableList.of(new VoidVineDecorator(0.02F)))
                .build());
    }

    public static RegistryKey<ConfiguredFeature<?,?>>registryKey(String name){
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(FantasyForest.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?,?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?,?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}


//                // 调整树干生成器参数：基础高度、随机额外高度、分支数量
//                new StraightTrunkPlacer(5, 2, 0),
//                BlockStateProvider.of(ModBlocks.END_LEAVES),
//                // 调整树叶生成器参数：半径、偏移、高度
//                new BlobFoliagePlacer(
//                        UniformIntProvider.create(2, 3), // 使用随机范围代替固定值
//                        ConstantIntProvider.create(1),
//                        2),
//                // 调整特征尺寸参数
//                new TwoLayersFeatureSize(1, 0, 2)
//                        .ignoreVines() // 允许穿过藤蔓
//                        .build());
