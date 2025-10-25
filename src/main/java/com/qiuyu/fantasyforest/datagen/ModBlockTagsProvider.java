package com.qiuyu.fantasyforest.datagen;

import com.qiuyu.fantasyforest.block.ModBlocks;
import com.qiuyu.fantasyforest.tag.ModBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture){
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)//镐子
                .add(ModBlocks.END_LICHEN)
                .add(ModBlocks.DEAD_END_LICHEN);

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)//斧头
                .add(ModBlocks.ARBOREAL_CRAFTING_TABLE)
                .add(ModBlocks.PERMEAROMA_LOG)
                .add(ModBlocks.PERMEAROMA_PLANKS)
                .add(ModBlocks.PERMEAROMA_WOOD)
                .add(ModBlocks.END_LOG)
                .add(ModBlocks.END_WOOD)
                .add(ModBlocks.END_PLANKS)
                .add(ModBlocks.MUTATED_END_LOG)
                .add(ModBlocks.MUTATED_END_WOOD)
                .add(ModBlocks.MUTATED_END_PLANKS)
                .add(ModBlocks.VOID_LOG)
                .add(ModBlocks.VOID_WOOD)
                .add(ModBlocks.VOID_PLANKS)
                .add(ModBlocks.VOID_ROOTS);

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE);//铲子

        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)//锄头
                .add(ModBlocks.PERMEAROMA_LEAVES)
                .add(ModBlocks.END_LEAVES)
                .add(ModBlocks.VOID_LEAVES);

        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(ModBlocks.PERMEAROMA_LEAVES)
                .add(ModBlocks.END_LEAVES)
                .add(ModBlocks.VOID_LEAVES);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.END_LOG)
                .add(ModBlocks.END_WOOD)
                .add(ModBlocks.MUTATED_END_LOG)
                .add(ModBlocks.MUTATED_END_WOOD)
                .add(ModBlocks.VOID_LOG)
                .add(ModBlocks.VOID_WOOD);

        getOrCreateTagBuilder(BlockTags.PLANKS)
                .add(ModBlocks.PERMEAROMA_PLANKS)
                .add(ModBlocks.END_PLANKS)
                .add(ModBlocks.MUTATED_END_PLANKS)
                .add(ModBlocks.VOID_PLANKS);

        getOrCreateTagBuilder(BlockTags.SAPLINGS)
                .add(ModBlocks.PERMEAROMA_SAPLING)
                .add(ModBlocks.END_TREE_SAPLING)
                .add(ModBlocks.VOID_TREE_SAPLING);

        getOrCreateTagBuilder(ModBlockTags.END_LOGS)
                .add(ModBlocks.END_LOG)
                .add(ModBlocks.END_WOOD);

        getOrCreateTagBuilder(ModBlockTags.MUTATED_END_LOGS)
                .add(ModBlocks.MUTATED_END_LOG)
                .add(ModBlocks.MUTATED_END_WOOD);

        getOrCreateTagBuilder(ModBlockTags.VOID_LOGS)
                .add(ModBlocks.VOID_LOG)
                .add(ModBlocks.VOID_WOOD);

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.PERMEAROMA_FENCE)
                .add(ModBlocks.END_WOODEN_FENCE)
                .add(ModBlocks.MUTATED_END_WOODEN_FENCE)
                .add(ModBlocks.VOID_WOODEN_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.PERMEAROMA_FENCE_GATE)
                .add(ModBlocks.END_WOODEN_FENCE_GATE)
                .add(ModBlocks.MUTATED_END_WOODEN_FENCE_GATE)
                .add(ModBlocks.VOID_WOODEN_FENCE_GATE);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.PERMEAROMA_LOG)
                .add(ModBlocks.PERMEAROMA_WOOD)
                .add(ModBlocks.STRIPPED_PERMEAROMA_LOG)
                .add(ModBlocks.STRIPPED_PERMEAROMA_WOOD)
                .add(ModBlocks.END_LOG)
                .add(ModBlocks.END_WOOD)
                .add(ModBlocks.STRIPPED_END_LOG)
                .add(ModBlocks.STRIPPED_END_WOOD)
                .add(ModBlocks.MUTATED_END_LOG)
                .add(ModBlocks.MUTATED_END_WOOD)
                .add(ModBlocks.STRIPPED_MUTATED_END_LOG)
                .add(ModBlocks.STRIPPED_MUTATED_END_WOOD)
                .add(ModBlocks.VOID_LOG)
                .add(ModBlocks.VOID_WOOD)
                .add(ModBlocks.STRIPPED_VOID_LOG)
                .add(ModBlocks.STRIPPED_VOID_WOOD);

        getOrCreateTagBuilder(BlockTags.CLIMBABLE)
                .add(ModBlocks.FRUITING_VOID_VINE_HEAD)
                .add(ModBlocks.VOID_VINE_BODY)
                .add(ModBlocks.VOID_VINE_HEAD);



        getOrCreateTagBuilder(ModBlockTags.STICKS)
                .add(ModBlocks.OAK_STICK)
                .add(ModBlocks.SPRUCE_STICK)
                .add(ModBlocks.ACACIA_STICK)
                .add(ModBlocks.BAMBOO_STICK)
                .add(ModBlocks.CHERRY_STICK)
                .add(ModBlocks.BIRCH_STICK)
                .add(ModBlocks.CRIMSON_STICK)
                .add(ModBlocks.JUNGLE_STICK)
                .add(ModBlocks.DARK_OAK_STICK)
                .add(ModBlocks.MANGROVE_STICK)
                .add(ModBlocks.WARPED_STICK)
                .add(ModBlocks.PERMEAROMA_STICK)
                .add(ModBlocks.END_STICK)
                .add(ModBlocks.MUTATED_END_STICK)
                .add(ModBlocks.VOID_STICK);
    }
}
