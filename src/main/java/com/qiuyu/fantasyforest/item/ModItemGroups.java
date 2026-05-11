package com.qiuyu.fantasyforest.item;

import com.qiuyu.fantasyforest.FantasyForest;
import com.qiuyu.fantasyforest.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> Fantasy_Forest = register("fantasy_forest");

    private static RegistryKey<ItemGroup> register(String id) {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(FantasyForest.MOD_ID, id));
    }

    public static void registerGroups(){
        Registry.register(
                Registries.ITEM_GROUP,
                Fantasy_Forest,
                ItemGroup.create(null, -1)
                        .displayName(Text.translatable("itemGroup.fantasy_forest"))
                        .icon(() -> new ItemStack(ModBlocks.REVELATION_SAPLING))
                        .entries((displayContext, entries) -> {

                            entries.add(MODItems.SEED_OF_REVELATION);

                            entries.add(ModBlocks.ARBOREAL_CRAFTING_TABLE);
                            entries.add(ModBlocks.ENDER_TRANSMISSION_SUPPRESSOR);

                            entries.add(ModBlocks.OAK_STICK);
                            entries.add(ModBlocks.SPRUCE_STICK);
                            entries.add(ModBlocks.BIRCH_STICK);
                            entries.add(ModBlocks.JUNGLE_STICK);
                            entries.add(ModBlocks.ACACIA_STICK);
                            entries.add(ModBlocks.DARK_OAK_STICK);
                            entries.add(ModBlocks.MANGROVE_STICK);
                            entries.add(ModBlocks.CHERRY_STICK);
                            entries.add(ModBlocks.CRIMSON_STICK);
                            entries.add(ModBlocks.WARPED_STICK);

                            entries.add(ModBlocks.END_LICHEN);
                            entries.add(ModBlocks.DEAD_END_LICHEN);

                            entries.add(ModBlocks.REVELATION_LEAVES);
                            entries.add(ModBlocks.REVELATION_SAPLING);
                            entries.add(ModBlocks.REVELATION_LOG);
                            entries.add(ModBlocks.REVELATION_WOOD);
                            entries.add(ModBlocks.STRIPPED_REVELATION_LOG);
                            entries.add(ModBlocks.STRIPPED_REVELATION_WOOD);
                            entries.add(ModBlocks.REVELATION_PLANKS);
                            entries.add(ModBlocks.REVELATION_STAIRS);
                            entries.add(ModBlocks.REVELATION_SLAB);
                            entries.add(ModBlocks.REVELATION_FENCE);
                            entries.add(ModBlocks.REVELATION_FENCE_GATE);
                            entries.add(ModBlocks.REVELATION_DOOR);
                            entries.add(ModBlocks.REVELATION_TRAPDOOR);
                            entries.add(ModBlocks.REVELATION_PRESSURE_PLATE);
                            entries.add(ModBlocks.REVELATION_BUTTON);
                            entries.add(MODItems.REVELATION_SIGN);
                            entries.add(MODItems.REVELATION_HANGING_SIGN);
                            entries.add(MODItems.REVELATION_BOAT);
                            entries.add(MODItems.REVELATION_CHEST_BOAT);
                            entries.add(ModBlocks.REVELATION_STICK);

                            entries.add(MODItems.PERMEAROMA_LEAF_PIECES);
                            entries.add(ModBlocks.PERMEAROMA_LEAVES);
                            entries.add(ModBlocks.PERMEAROMA_SAPLING);
                            entries.add(ModBlocks.PERMEAROMA_LOG);
                            entries.add(ModBlocks.PERMEAROMA_WOOD);
                            entries.add(ModBlocks.STRIPPED_PERMEAROMA_LOG);
                            entries.add(ModBlocks.STRIPPED_PERMEAROMA_WOOD);
                            entries.add(ModBlocks.PERMEAROMA_PLANKS);
                            entries.add(ModBlocks.PERMEAROMA_STAIRS);
                            entries.add(ModBlocks.PERMEAROMA_SLAB);
                            entries.add(ModBlocks.PERMEAROMA_FENCE);
                            entries.add(ModBlocks.PERMEAROMA_FENCE_GATE);
                            entries.add(ModBlocks.PERMEAROMA_DOOR);
                            entries.add(ModBlocks.PERMEAROMA_TRAPDOOR);
                            entries.add(ModBlocks.PERMEAROMA_PRESSURE_PLATE);
                            entries.add(ModBlocks.PERMEAROMA_BUTTON);
                            entries.add(MODItems.PERMEAROMA_SIGN);
                            entries.add(MODItems.PERMEAROMA_HANGING_SIGN);
                            entries.add(MODItems.PERMEAROMA_BOAT);
                            entries.add(MODItems.PERMEAROMA_CHEST_BOAT);
                            entries.add(ModBlocks.PERMEAROMA_STICK);
                            entries.add(MODItems.PERMEAROMA_SWORD);
                            entries.add(MODItems.PERMEAROMA_SHOVEL);
                            entries.add(MODItems.PERMEAROMA_PICKAXE);
                            entries.add(MODItems.PERMEAROMA_AXE);
                            entries.add(MODItems.PERMEAROMA_HOE);

                            entries.add(ModBlocks.APHRODISIA_FLOWER);
                            entries.add(ModBlocks.APHRODISIA_LEAVES);
                            entries.add(ModBlocks.APHRODISIA_SAPLING);
                            entries.add(ModBlocks.APHRODISIA_LOG);
                            entries.add(ModBlocks.APHRODISIA_WOOD);
                            entries.add(ModBlocks.STRIPPED_APHRODISIA_LOG);
                            entries.add(ModBlocks.STRIPPED_APHRODISIA_WOOD);
                            entries.add(ModBlocks.APHRODISIA_PLANKS);
                            entries.add(ModBlocks.APHRODISIA_STAIRS);
                            entries.add(ModBlocks.APHRODISIA_SLAB);
                            entries.add(ModBlocks.APHRODISIA_FENCE);
                            entries.add(ModBlocks.APHRODISIA_FENCE_GATE);
                            entries.add(ModBlocks.APHRODISIA_DOOR);
                            entries.add(ModBlocks.APHRODISIA_TRAPDOOR);
                            entries.add(ModBlocks.APHRODISIA_PRESSURE_PLATE);
                            entries.add(ModBlocks.APHRODISIA_BUTTON);
                            entries.add(MODItems.APHRODISIA_SIGN);
                            entries.add(MODItems.APHRODISIA_HANGING_SIGN);
                            entries.add(MODItems.APHRODISIA_BOAT);
                            entries.add(MODItems.APHRODISIA_CHEST_BOAT);
                            entries.add(ModBlocks.APHRODISIA_STICK);
                            entries.add(MODItems.APHRODISIA_SWORD);
                            entries.add(MODItems.APHRODISIA_SHOVEL);
                            entries.add(MODItems.APHRODISIA_PICKAXE);
                            entries.add(MODItems.APHRODISIA_AXE);
                            entries.add(MODItems.APHRODISIA_HOE);

                            entries.add(MODItems.END_RESIN);
                            entries.add(ModBlocks.END_RESIN_BLOCK);
                            entries.add(ModBlocks.END_LEAVES);
                            entries.add(ModBlocks.END_TREE_SAPLING);
                            entries.add(ModBlocks.END_LOG);
                            entries.add(ModBlocks.END_WOOD);
                            entries.add(ModBlocks.STRIPPED_END_LOG);
                            entries.add(ModBlocks.STRIPPED_END_WOOD);
                            entries.add(ModBlocks.END_PLANKS);
                            entries.add(ModBlocks.END_WOODEN_STAIRS);
                            entries.add(ModBlocks.END_WOODEN_SLAB);
                            entries.add(ModBlocks.END_WOODEN_FENCE);
                            entries.add(ModBlocks.END_WOODEN_FENCE_GATE);
                            entries.add(ModBlocks.END_WOODEN_DOOR);
                            entries.add(ModBlocks.END_WOODEN_TRAPDOOR);
                            entries.add(ModBlocks.END_WOODEN_PRESSURE_PLATE);
                            entries.add(ModBlocks.END_WOODEN_BUTTON);
                            entries.add(MODItems.END_WOODEN_SIGN);
                            entries.add(MODItems.END_WOODEN_HANGING_SIGN);
                            entries.add(MODItems.END_WOODEN_BOAT);
                            entries.add(MODItems.END_WOODEN_CHEST_BOAT);
                            entries.add(ModBlocks.END_STICK);
                            entries.add(MODItems.END_WOODEN_SWORD);
                            entries.add(MODItems.END_WOODEN_SHOVEL);
                            entries.add(MODItems.END_WOODEN_PICKAXE);
                            entries.add(MODItems.END_WOODEN_AXE);
                            entries.add(MODItems.END_WOODEN_HOE);

                            entries.add(MODItems.MUTATED_END_RESIN);
                            entries.add(ModBlocks.MUTATED_END_RESIN_BLOCK);
                            entries.add(ModBlocks.MUTATED_END_LOG);
                            entries.add(ModBlocks.MUTATED_END_WOOD);
                            entries.add(ModBlocks.STRIPPED_MUTATED_END_LOG);
                            entries.add(ModBlocks.STRIPPED_MUTATED_END_WOOD);
                            entries.add(ModBlocks.MUTATED_END_PLANKS);
                            entries.add(ModBlocks.MUTATED_END_WOODEN_STAIRS);
                            entries.add(ModBlocks.MUTATED_END_WOODEN_SLAB);
                            entries.add(ModBlocks.MUTATED_END_WOODEN_FENCE);
                            entries.add(ModBlocks.MUTATED_END_WOODEN_FENCE_GATE);
                            entries.add(ModBlocks.MUTATED_END_WOODEN_DOOR);
                            entries.add(ModBlocks.MUTATED_END_WOODEN_TRAPDOOR);
                            entries.add(ModBlocks.MUTATED_END_WOODEN_PRESSURE_PLATE);
                            entries.add(ModBlocks.MUTATED_END_WOODEN_BUTTON);
                            entries.add(MODItems.MUTATED_END_WOODEN_SIGN);
                            entries.add(MODItems.MUTATED_END_WOODEN_HANGING_SIGN);
                            entries.add(MODItems.MUTATED_END_WOODEN_BOAT);
                            entries.add(MODItems.MUTATED_END_WOODEN_CHEST_BOAT);
                            entries.add(ModBlocks.MUTATED_END_STICK);
                            entries.add(MODItems.MUTATED_END_WOODEN_SWORD);
                            entries.add(MODItems.MUTATED_END_WOODEN_SHOVEL);
                            entries.add(MODItems.MUTATED_END_WOODEN_PICKAXE);
                            entries.add(MODItems.MUTATED_END_WOODEN_AXE);
                            entries.add(MODItems.MUTATED_END_WOODEN_HOE);

                            entries.add(MODItems.VOID_RESIN);
                            entries.add(ModBlocks.VOID_RESIN_BLOCK);
                            entries.add(ModBlocks.VOID_LEAVES);
                            entries.add(ModBlocks.VOID_ROOTS);
                            entries.add(ModBlocks.VOID_TREE_SAPLING);
                            entries.add(MODItems.VOID_FRUIT);
                            entries.add(ModBlocks.VOID_VINE_BODY);
                            entries.add(ModBlocks.VOID_VINE_HEAD);
                            entries.add(ModBlocks.FRUITING_VOID_VINE_HEAD);
                            entries.add(ModBlocks.PHOSPHOR);
                            entries.add(ModBlocks.VOID_LOG);
                            entries.add(ModBlocks.VOID_WOOD);
                            entries.add(ModBlocks.STRIPPED_VOID_LOG);
                            entries.add(ModBlocks.STRIPPED_VOID_WOOD);
                            entries.add(ModBlocks.VOID_PLANKS);
                            entries.add(ModBlocks.VOID_WOODEN_STAIRS);
                            entries.add(ModBlocks.VOID_WOODEN_SLAB);
                            entries.add(ModBlocks.VOID_WOODEN_FENCE);
                            entries.add(ModBlocks.VOID_WOODEN_FENCE_GATE);
                            entries.add(ModBlocks.VOID_WOODEN_DOOR);
                            entries.add(ModBlocks.VOID_WOODEN_TRAPDOOR);
                            entries.add(ModBlocks.VOID_WOODEN_PRESSURE_PLATE);
                            entries.add(ModBlocks.VOID_WOODEN_BUTTON);
                            entries.add(ModBlocks.VOID_WOODEN_SIGN);
                            entries.add(ModBlocks.VOID_WOODEN_HANGING_SIGN);
                            entries.add(MODItems.VOID_WOODEN_BOAT);
                            entries.add(MODItems.VOID_WOODEN_CHEST_BOAT);
                            entries.add(ModBlocks.VOID_STICK);
                            entries.add(MODItems.VOID_WOODEN_SWORD);
                            entries.add(MODItems.VOID_WOODEN_SHOVEL);
                            entries.add(MODItems.VOID_WOODEN_PICKAXE);
                            entries.add(MODItems.VOID_WOODEN_AXE);
                            entries.add(MODItems.VOID_WOODEN_HOE);
                        })
                        .build()
        );
    }
}
