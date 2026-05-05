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

                .add(ModBlocks.REVELATION_LOG)
                .add(ModBlocks.REVELATION_WOOD)
                .add(ModBlocks.STRIPPED_REVELATION_LOG)
                .add(ModBlocks.STRIPPED_REVELATION_WOOD)
                .add(ModBlocks.REVELATION_PLANKS)
                .add(ModBlocks.REVELATION_STICK)
                .add(ModBlocks.REVELATION_BUTTON)
                .add(ModBlocks.REVELATION_DOOR)
                .add(ModBlocks.REVELATION_HANGING_SIGN)
                .add(ModBlocks.REVELATION_PRESSURE_PLATE)
                .add(ModBlocks.REVELATION_SIGN)
                .add(ModBlocks.REVELATION_WALL_SIGN)
                .add(ModBlocks.REVELATION_WALL_HANGING_SIGN)
                .add(ModBlocks.REVELATION_TRAPDOOR)

                .add(ModBlocks.PERMEAROMA_LOG)
                .add(ModBlocks.PERMEAROMA_WOOD)
                .add(ModBlocks.STRIPPED_PERMEAROMA_LOG)
                .add(ModBlocks.STRIPPED_PERMEAROMA_WOOD)
                .add(ModBlocks.PERMEAROMA_PLANKS)
                .add(ModBlocks.PERMEAROMA_STICK)
                .add(ModBlocks.PERMEAROMA_BUTTON)
                .add(ModBlocks.PERMEAROMA_DOOR)
                .add(ModBlocks.PERMEAROMA_HANGING_SIGN)
                .add(ModBlocks.PERMEAROMA_PRESSURE_PLATE)
                .add(ModBlocks.PERMEAROMA_SIGN)
                .add(ModBlocks.PERMEAROMA_WALL_SIGN)
                .add(ModBlocks.PERMEAROMA_WALL_HANGING_SIGN)
                .add(ModBlocks.PERMEAROMA_TRAPDOOR)

                .add(ModBlocks.APHRODISIA_LOG)
                .add(ModBlocks.APHRODISIA_WOOD)
                .add(ModBlocks.STRIPPED_APHRODISIA_LOG)
                .add(ModBlocks.STRIPPED_APHRODISIA_WOOD)
                .add(ModBlocks.APHRODISIA_PLANKS)
                .add(ModBlocks.APHRODISIA_STICK)
                .add(ModBlocks.APHRODISIA_BUTTON)
                .add(ModBlocks.APHRODISIA_DOOR)
                .add(ModBlocks.APHRODISIA_HANGING_SIGN)
                .add(ModBlocks.APHRODISIA_PRESSURE_PLATE)
                .add(ModBlocks.APHRODISIA_SIGN)
                .add(ModBlocks.APHRODISIA_WALL_SIGN)
                .add(ModBlocks.APHRODISIA_WALL_HANGING_SIGN)
                .add(ModBlocks.APHRODISIA_TRAPDOOR)

                .add(ModBlocks.END_LOG)
                .add(ModBlocks.END_WOOD)
                .add(ModBlocks.STRIPPED_END_LOG)
                .add(ModBlocks.STRIPPED_END_WOOD)
                .add(ModBlocks.END_PLANKS)
                .add(ModBlocks.END_STICK)
                .add(ModBlocks.END_WOODEN_BUTTON)
                .add(ModBlocks.END_WOODEN_DOOR)
                .add(ModBlocks.END_WOODEN_HANGING_SIGN)
                .add(ModBlocks.END_WOODEN_PRESSURE_PLATE)
                .add(ModBlocks.END_WOODEN_SIGN)
                .add(ModBlocks.END_WOODEN_WALL_SIGN)
                .add(ModBlocks.END_WOODEN_WALL_HANGING_SIGN)
                .add(ModBlocks.END_WOODEN_TRAPDOOR)

                .add(ModBlocks.MUTATED_END_LOG)
                .add(ModBlocks.MUTATED_END_WOOD)
                .add(ModBlocks.STRIPPED_MUTATED_END_LOG)
                .add(ModBlocks.STRIPPED_MUTATED_END_WOOD)
                .add(ModBlocks.MUTATED_END_PLANKS)
                .add(ModBlocks.MUTATED_END_STICK)
                .add(ModBlocks.MUTATED_END_WOODEN_BUTTON)
                .add(ModBlocks.MUTATED_END_WOODEN_DOOR)
                .add(ModBlocks.MUTATED_END_WOODEN_HANGING_SIGN)
                .add(ModBlocks.MUTATED_END_WOODEN_PRESSURE_PLATE)
                .add(ModBlocks.MUTATED_END_WOODEN_SIGN)
                .add(ModBlocks.MUTATED_END_WOODEN_WALL_SIGN)
                .add(ModBlocks.MUTATED_END_WOODEN_WALL_HANGING_SIGN)
                .add(ModBlocks.MUTATED_END_WOODEN_TRAPDOOR)

                .add(ModBlocks.VOID_LOG)
                .add(ModBlocks.VOID_WOOD)
                .add(ModBlocks.STRIPPED_VOID_LOG)
                .add(ModBlocks.STRIPPED_VOID_WOOD)
                .add(ModBlocks.VOID_PLANKS)
                .add(ModBlocks.VOID_ROOTS)
                .add(ModBlocks.VOID_STICK)
                .add(ModBlocks.VOID_WOODEN_BUTTON)
                .add(ModBlocks.VOID_WOODEN_DOOR)
                .add(ModBlocks.VOID_WOODEN_HANGING_SIGN)
                .add(ModBlocks.VOID_WOODEN_PRESSURE_PLATE)
                .add(ModBlocks.VOID_WOODEN_SIGN)
                .add(ModBlocks.VOID_WOODEN_WALL_SIGN)
                .add(ModBlocks.VOID_WOODEN_WALL_HANGING_SIGN)
                .add(ModBlocks.VOID_WOODEN_TRAPDOOR);

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE);//铲子

        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)//锄头
                .add(ModBlocks.REVELATION_LEAVES)
                .add(ModBlocks.PERMEAROMA_LEAVES)
                .add(ModBlocks.APHRODISIA_LEAVES)
                .add(ModBlocks.END_LEAVES)
                .add(ModBlocks.VOID_LEAVES);

        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(ModBlocks.REVELATION_LEAVES)
                .add(ModBlocks.PERMEAROMA_LEAVES)
                .add(ModBlocks.APHRODISIA_LEAVES)
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
                .add(ModBlocks.REVELATION_PLANKS)
                .add(ModBlocks.PERMEAROMA_PLANKS)
                .add(ModBlocks.APHRODISIA_PLANKS)
                .add(ModBlocks.END_PLANKS)
                .add(ModBlocks.MUTATED_END_PLANKS)
                .add(ModBlocks.VOID_PLANKS);

        getOrCreateTagBuilder(BlockTags.SAPLINGS)
                .add(ModBlocks.REVELATION_SAPLING)
                .add(ModBlocks.PERMEAROMA_SAPLING)
                .add(ModBlocks.APHRODISIA_SAPLING)
                .add(ModBlocks.END_TREE_SAPLING)
                .add(ModBlocks.VOID_TREE_SAPLING);

        getOrCreateTagBuilder(ModBlockTags.END_WOODEN_BLOCKS)
                .add(ModBlocks.END_LOG)
                .add(ModBlocks.END_WOOD)
                .add(ModBlocks.STRIPPED_END_LOG)
                .add(ModBlocks.STRIPPED_END_WOOD)
                .add(ModBlocks.END_PLANKS)
                .add(ModBlocks.END_WOODEN_BUTTON)
                .add(ModBlocks.END_WOODEN_DOOR)
                .add(ModBlocks.END_WOODEN_PRESSURE_PLATE)
                .add(ModBlocks.END_WOODEN_SLAB)
                .add(ModBlocks.END_WOODEN_STAIRS)
                .add(ModBlocks.END_WOODEN_TRAPDOOR)
                .add(ModBlocks.END_WOODEN_WALL_SIGN)
                .add(ModBlocks.END_WOODEN_WALL_HANGING_SIGN)
                .add(ModBlocks.END_WOODEN_FENCE)
                .add(ModBlocks.END_WOODEN_FENCE_GATE)
                .add(ModBlocks.END_WOODEN_HANGING_SIGN)
                .add(ModBlocks.END_WOODEN_SIGN)
                .add(ModBlocks.END_STICK);

        getOrCreateTagBuilder(ModBlockTags.MUTATED_END_WOODEN_BLOCKS)
                .add(ModBlocks.MUTATED_END_LOG)
                .add(ModBlocks.MUTATED_END_WOOD)
                .add(ModBlocks.STRIPPED_MUTATED_END_LOG)
                .add(ModBlocks.STRIPPED_MUTATED_END_WOOD)
                .add(ModBlocks.MUTATED_END_PLANKS)
                .add(ModBlocks.MUTATED_END_WOODEN_BUTTON)
                .add(ModBlocks.MUTATED_END_WOODEN_DOOR)
                .add(ModBlocks.MUTATED_END_WOODEN_PRESSURE_PLATE)
                .add(ModBlocks.MUTATED_END_WOODEN_SLAB)
                .add(ModBlocks.MUTATED_END_WOODEN_STAIRS)
                .add(ModBlocks.MUTATED_END_WOODEN_TRAPDOOR)
                .add(ModBlocks.MUTATED_END_WOODEN_WALL_SIGN)
                .add(ModBlocks.MUTATED_END_WOODEN_WALL_HANGING_SIGN)
                .add(ModBlocks.MUTATED_END_WOODEN_FENCE)
                .add(ModBlocks.MUTATED_END_WOODEN_FENCE_GATE)
                .add(ModBlocks.MUTATED_END_WOODEN_HANGING_SIGN)
                .add(ModBlocks.MUTATED_END_WOODEN_SIGN)
                .add(ModBlocks.MUTATED_END_STICK);

        getOrCreateTagBuilder(ModBlockTags.VOID_WOODEN_BLOCKS)
                .add(ModBlocks.VOID_LOG)
                .add(ModBlocks.VOID_WOOD)
                .add(ModBlocks.STRIPPED_VOID_LOG)
                .add(ModBlocks.STRIPPED_VOID_WOOD)
                .add(ModBlocks.VOID_PLANKS)
                .add(ModBlocks.VOID_WOODEN_BUTTON)
                .add(ModBlocks.VOID_WOODEN_DOOR)
                .add(ModBlocks.VOID_WOODEN_PRESSURE_PLATE)
                .add(ModBlocks.VOID_WOODEN_SLAB)
                .add(ModBlocks.VOID_WOODEN_STAIRS)
                .add(ModBlocks.VOID_WOODEN_TRAPDOOR)
                .add(ModBlocks.VOID_WOODEN_WALL_SIGN)
                .add(ModBlocks.VOID_WOODEN_WALL_HANGING_SIGN)
                .add(ModBlocks.VOID_WOODEN_FENCE)
                .add(ModBlocks.VOID_WOODEN_FENCE_GATE)
                .add(ModBlocks.VOID_WOODEN_HANGING_SIGN)
                .add(ModBlocks.VOID_WOODEN_SIGN)
                .add(ModBlocks.VOID_STICK);

        getOrCreateTagBuilder(ModBlockTags.PERMEAROMA_BLOCKS)
                .add(ModBlocks.PERMEAROMA_LOG)
                .add(ModBlocks.PERMEAROMA_WOOD)
                .add(ModBlocks.STRIPPED_PERMEAROMA_LOG)
                .add(ModBlocks.STRIPPED_PERMEAROMA_WOOD)
                .add(ModBlocks.PERMEAROMA_PLANKS)
                .add(ModBlocks.PERMEAROMA_BUTTON)
                .add(ModBlocks.PERMEAROMA_DOOR)
                .add(ModBlocks.PERMEAROMA_PRESSURE_PLATE)
                .add(ModBlocks.PERMEAROMA_SLAB)
                .add(ModBlocks.PERMEAROMA_STAIRS)
                .add(ModBlocks.PERMEAROMA_TRAPDOOR)
                .add(ModBlocks.PERMEAROMA_WALL_SIGN)
                .add(ModBlocks.PERMEAROMA_WALL_HANGING_SIGN)
                .add(ModBlocks.PERMEAROMA_FENCE)
                .add(ModBlocks.PERMEAROMA_FENCE_GATE)
                .add(ModBlocks.PERMEAROMA_HANGING_SIGN)
                .add(ModBlocks.PERMEAROMA_SIGN)
                .add(ModBlocks.PERMEAROMA_STICK);

        getOrCreateTagBuilder(ModBlockTags.APHRODISIA_BLOCKS)
                .add(ModBlocks.APHRODISIA_LOG)
                .add(ModBlocks.APHRODISIA_WOOD)
                .add(ModBlocks.STRIPPED_APHRODISIA_LOG)
                .add(ModBlocks.STRIPPED_APHRODISIA_WOOD)
                .add(ModBlocks.APHRODISIA_PLANKS)
                .add(ModBlocks.APHRODISIA_BUTTON)
                .add(ModBlocks.APHRODISIA_DOOR)
                .add(ModBlocks.APHRODISIA_PRESSURE_PLATE)
                .add(ModBlocks.APHRODISIA_SLAB)
                .add(ModBlocks.APHRODISIA_STAIRS)
                .add(ModBlocks.APHRODISIA_TRAPDOOR)
                .add(ModBlocks.APHRODISIA_WALL_SIGN)
                .add(ModBlocks.APHRODISIA_WALL_HANGING_SIGN)
                .add(ModBlocks.APHRODISIA_FENCE)
                .add(ModBlocks.APHRODISIA_FENCE_GATE)
                .add(ModBlocks.APHRODISIA_HANGING_SIGN)
                .add(ModBlocks.APHRODISIA_SIGN)
                .add(ModBlocks.APHRODISIA_STICK);

        getOrCreateTagBuilder(ModBlockTags.REVELATION_BLOCKS)
                .add(ModBlocks.REVELATION_LOG)
                .add(ModBlocks.REVELATION_WOOD)
                .add(ModBlocks.STRIPPED_REVELATION_LOG)
                .add(ModBlocks.STRIPPED_REVELATION_WOOD)
                .add(ModBlocks.REVELATION_PLANKS)
                .add(ModBlocks.REVELATION_BUTTON)
                .add(ModBlocks.REVELATION_DOOR)
                .add(ModBlocks.REVELATION_PRESSURE_PLATE)
                .add(ModBlocks.REVELATION_SLAB)
                .add(ModBlocks.REVELATION_STAIRS)
                .add(ModBlocks.REVELATION_TRAPDOOR)
                .add(ModBlocks.REVELATION_WALL_SIGN)
                .add(ModBlocks.REVELATION_WALL_HANGING_SIGN)
                .add(ModBlocks.REVELATION_FENCE)
                .add(ModBlocks.REVELATION_FENCE_GATE)
                .add(ModBlocks.REVELATION_HANGING_SIGN)
                .add(ModBlocks.REVELATION_SIGN)
                .add(ModBlocks.REVELATION_STICK);

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.REVELATION_FENCE)
                .add(ModBlocks.PERMEAROMA_FENCE)
                .add(ModBlocks.APHRODISIA_FENCE)
                .add(ModBlocks.END_WOODEN_FENCE)
                .add(ModBlocks.MUTATED_END_WOODEN_FENCE)
                .add(ModBlocks.VOID_WOODEN_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.REVELATION_FENCE_GATE)
                .add(ModBlocks.PERMEAROMA_FENCE_GATE)
                .add(ModBlocks.APHRODISIA_FENCE_GATE)
                .add(ModBlocks.END_WOODEN_FENCE_GATE)
                .add(ModBlocks.MUTATED_END_WOODEN_FENCE_GATE)
                .add(ModBlocks.VOID_WOODEN_FENCE_GATE);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.REVELATION_LOG)
                .add(ModBlocks.REVELATION_WOOD)
                .add(ModBlocks.STRIPPED_REVELATION_LOG)
                .add(ModBlocks.STRIPPED_REVELATION_WOOD)
                .add(ModBlocks.PERMEAROMA_LOG)
                .add(ModBlocks.PERMEAROMA_WOOD)
                .add(ModBlocks.STRIPPED_PERMEAROMA_LOG)
                .add(ModBlocks.STRIPPED_PERMEAROMA_WOOD)
                .add(ModBlocks.APHRODISIA_LOG)
                .add(ModBlocks.APHRODISIA_WOOD)
                .add(ModBlocks.STRIPPED_APHRODISIA_LOG)
                .add(ModBlocks.STRIPPED_APHRODISIA_WOOD)
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
                .add(ModBlocks.REVELATION_STICK)
                .add(ModBlocks.PERMEAROMA_STICK)
                .add(ModBlocks.APHRODISIA_STICK)
                .add(ModBlocks.END_STICK)
                .add(ModBlocks.MUTATED_END_STICK)
                .add(ModBlocks.VOID_STICK);
    }
}
