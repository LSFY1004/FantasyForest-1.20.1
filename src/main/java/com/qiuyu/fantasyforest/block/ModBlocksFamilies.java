package com.qiuyu.fantasyforest.block;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.registry.Registries;

import java.util.Map;
import java.util.stream.Stream;

public class ModBlocksFamilies {
    private static final Map<Block, BlockFamily> BASE_BLOCKS_TO_FAMILIES = Maps.<Block, BlockFamily>newHashMap();

    public static final BlockFamily PERMEAROMA_WOODEN = register(ModBlocks.PERMEAROMA_PLANKS)
            .button(ModBlocks.PERMEAROMA_BUTTON)
            .fence(ModBlocks.PERMEAROMA_FENCE)
            .fenceGate(ModBlocks.PERMEAROMA_FENCE_GATE)
            .pressurePlate(ModBlocks.PERMEAROMA_PRESSURE_PLATE)
            .slab(ModBlocks.PERMEAROMA_SLAB)
            .stairs(ModBlocks.PERMEAROMA_STAIRS)
            .door(ModBlocks.PERMEAROMA_DOOR)
            .trapdoor(ModBlocks.PERMEAROMA_TRAPDOOR)
            .sign(ModBlocks.PERMEAROMA_SIGN,ModBlocks.PERMEAROMA_WALL_SIGN)
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();

    public static final BlockFamily END_WOODEN = register(ModBlocks.END_PLANKS)
            .button(ModBlocks.END_WOODEN_BUTTON)
            .fence(ModBlocks.END_WOODEN_FENCE)
            .fenceGate(ModBlocks.END_WOODEN_FENCE_GATE)
            .pressurePlate(ModBlocks.END_WOODEN_PRESSURE_PLATE)
            .slab(ModBlocks.END_WOODEN_SLAB)
            .stairs(ModBlocks.END_WOODEN_STAIRS)
            .door(ModBlocks.END_WOODEN_DOOR)
            .trapdoor(ModBlocks.END_WOODEN_TRAPDOOR)
            .sign(ModBlocks.END_WOODEN_SIGN,ModBlocks.END_WOODEN_WALL_SIGN)
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();

    public static final BlockFamily MUTATED_END_WOODEN = register(ModBlocks.MUTATED_END_PLANKS)
            .button(ModBlocks.MUTATED_END_WOODEN_BUTTON)
            .fence(ModBlocks.MUTATED_END_WOODEN_FENCE)
            .fenceGate(ModBlocks.MUTATED_END_WOODEN_FENCE_GATE)
            .pressurePlate(ModBlocks.MUTATED_END_WOODEN_PRESSURE_PLATE)
            .slab(ModBlocks.MUTATED_END_WOODEN_SLAB)
            .stairs(ModBlocks.MUTATED_END_WOODEN_STAIRS)
            .door(ModBlocks.MUTATED_END_WOODEN_DOOR)
            .trapdoor(ModBlocks.MUTATED_END_WOODEN_TRAPDOOR)
            .sign(ModBlocks.MUTATED_END_WOODEN_SIGN,ModBlocks.MUTATED_END_WOODEN_WALL_SIGN)
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();

    public static final BlockFamily VOID_WOODEN = register(ModBlocks.VOID_PLANKS)
            .button(ModBlocks.VOID_WOODEN_BUTTON)
            .fence(ModBlocks.VOID_WOODEN_FENCE)
            .fenceGate(ModBlocks.VOID_WOODEN_FENCE_GATE)
            .pressurePlate(ModBlocks.VOID_WOODEN_PRESSURE_PLATE)
            .slab(ModBlocks.VOID_WOODEN_SLAB)
            .stairs(ModBlocks.VOID_WOODEN_STAIRS)
            .door(ModBlocks.VOID_WOODEN_DOOR)
            .trapdoor(ModBlocks.VOID_WOODEN_TRAPDOOR)
            .sign(ModBlocks.VOID_WOODEN_SIGN,ModBlocks.VOID_WOODEN_WALL_SIGN)
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();

    public static BlockFamily.Builder register(Block baseBlock) {
        BlockFamily.Builder builder = new BlockFamily.Builder(baseBlock);
        BlockFamily blockFamily = BASE_BLOCKS_TO_FAMILIES.put(baseBlock, builder.build());
        if (blockFamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + Registries.BLOCK.getId(baseBlock));
        } else {
            return builder;
        }
    }

    public static Stream<BlockFamily> getFamilies() {
        return BASE_BLOCKS_TO_FAMILIES.values().stream();
    }
}
