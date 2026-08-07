package com.qiuyu.fantasyforest.datagen;

import com.qiuyu.fantasyforest.block.ModBlocks;
import com.qiuyu.fantasyforest.item.ModItems;
import com.qiuyu.fantasyforest.tag.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModItemTags.STICKS)
                .add(ModBlocks.REVELATION_STICK.asItem())
                .add(ModBlocks.PERMEAROMA_STICK.asItem())
                .add(ModBlocks.APHRODISIA_STICK.asItem())
                .add(ModBlocks.END_STICK.asItem())
                .add(ModBlocks.MUTATED_END_STICK.asItem())
                .add(ModBlocks.VOID_STICK.asItem())
                .add(ModBlocks.OAK_STICK.asItem())
                .add(ModBlocks.SPRUCE_STICK.asItem())
                .add(ModBlocks.ACACIA_STICK.asItem())
                .add(ModBlocks.BAMBOO_STICK.asItem())
                .add(ModBlocks.CHERRY_STICK.asItem())
                .add(ModBlocks.BIRCH_STICK.asItem())
                .add(ModBlocks.CRIMSON_STICK.asItem())
                .add(ModBlocks.JUNGLE_STICK.asItem())
                .add(ModBlocks.DARK_OAK_STICK.asItem())
                .add(ModBlocks.MANGROVE_STICK.asItem())
                .add(ModBlocks.WARPED_STICK.asItem())
                .add(Items.STICK);

        getOrCreateTagBuilder(ModItemTags.REVELATION_ARBOREAL)
                .add(ModBlocks.REVELATION_SAPLING.asItem())
                .add(ModItems.SEED_OF_REVELATION);

        getOrCreateTagBuilder(ModItemTags.PERMEAROMA_ARBOREAL)
                .add(ModBlocks.PERMEAROMA_SAPLING.asItem())
                .add(ModItems.PERMEAROMA_LEAF_PIECES);

        getOrCreateTagBuilder(ModItemTags.APHRODISIA_ARBOREAL)
                .add(ModBlocks.APHRODISIA_SAPLING.asItem());

        getOrCreateTagBuilder(ModItemTags.END_ARBOREAL)
                .add(ModBlocks.END_TREE_SAPLING.asItem())
                .add(ModItems.END_RESIN);

        getOrCreateTagBuilder(ModItemTags.MUTATED_END_ARBOREAL)
                .add(ModItems.MUTATED_END_RESIN);

        getOrCreateTagBuilder(ModItemTags.VOID_ARBOREAL)
                .add(ModBlocks.VOID_TREE_SAPLING.asItem())
                .add(ModItems.VOID_RESIN);

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.PERMEAROMA_PLANKS.asItem())
                .add(ModBlocks.END_PLANKS.asItem())
                .add(ModBlocks.MUTATED_END_PLANKS.asItem())
                .add(ModBlocks.VOID_PLANKS.asItem());

        getOrCreateTagBuilder(ItemTags.SAPLINGS)
                .add(ModBlocks.PERMEAROMA_SAPLING.asItem())
                .add(ModBlocks.END_TREE_SAPLING.asItem())
                .add(ModBlocks.VOID_TREE_SAPLING.asItem());

        getOrCreateTagBuilder(ItemTags.LEAVES)
                .add(ModBlocks.REVELATION_LEAVES.asItem())
                .add(ModBlocks.PERMEAROMA_LEAVES.asItem())
                .add(ModBlocks.APHRODISIA_LEAVES.asItem())
                .add(ModBlocks.END_LEAVES.asItem())
                .add(ModBlocks.VOID_LEAVES.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
                .add(ModBlocks.PERMEAROMA_FENCE.asItem())
                .add(ModBlocks.END_WOODEN_FENCE.asItem())
                .add(ModBlocks.MUTATED_END_WOODEN_FENCE.asItem())
                .add(ModBlocks.VOID_WOODEN_FENCE.asItem());
        getOrCreateTagBuilder(ItemTags.FENCE_GATES)
                .add(ModBlocks.PERMEAROMA_FENCE_GATE.asItem())
                .add(ModBlocks.END_WOODEN_FENCE_GATE.asItem())
                .add(ModBlocks.MUTATED_END_WOODEN_FENCE_GATE.asItem())
                .add(ModBlocks.VOID_WOODEN_FENCE_GATE.asItem());

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.PERMEAROMA_LOG.asItem())
                .add(ModBlocks.PERMEAROMA_WOOD.asItem())
                .add(ModBlocks.STRIPPED_PERMEAROMA_LOG.asItem())
                .add(ModBlocks.STRIPPED_PERMEAROMA_WOOD.asItem())
                .add(ModBlocks.END_LOG.asItem())
                .add(ModBlocks.END_WOOD.asItem())
                .add(ModBlocks.STRIPPED_END_LOG.asItem())
                .add(ModBlocks.STRIPPED_END_WOOD.asItem())
                .add(ModBlocks.MUTATED_END_LOG.asItem())
                .add(ModBlocks.MUTATED_END_WOOD.asItem())
                .add(ModBlocks.STRIPPED_MUTATED_END_LOG.asItem())
                .add(ModBlocks.STRIPPED_MUTATED_END_WOOD.asItem())
                .add(ModBlocks.VOID_LOG.asItem())
                .add(ModBlocks.VOID_WOOD.asItem())
                .add(ModBlocks.STRIPPED_VOID_LOG.asItem())
                .add(ModBlocks.STRIPPED_VOID_WOOD.asItem());

        getOrCreateTagBuilder(ItemTags.LOGS)
                .addTag(ItemTags.LOGS_THAT_BURN);
    }
}
