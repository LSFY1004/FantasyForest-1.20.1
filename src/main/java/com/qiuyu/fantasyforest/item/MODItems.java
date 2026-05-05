package com.qiuyu.fantasyforest.item;

import com.qiuyu.fantasyforest.FantasyForest;
import com.qiuyu.fantasyforest.block.ModBlocks;
import com.qiuyu.fantasyforest.entity.vehicle.ModBoats;
import com.qiuyu.fantasyforest.item.customtool.*;
import com.qiuyu.fantasyforest.item.spetial.SeedOfRevelationItem;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import static com.qiuyu.fantasyforest.block.ModBlocks.*;

public class MODItems {

    public static final Item SEED_OF_REVELATION = registerItem("seed_of_revelation", new SeedOfRevelationItem(new Item.Settings()));

    public static final Item REVELATION_SIGN = registerItem("revelation_sign",
            new SignItem(new Item.Settings().maxCount(16),ModBlocks.REVELATION_SIGN, ModBlocks.REVELATION_WALL_SIGN));
    public static final Item REVELATION_HANGING_SIGN = registerItem("revelation_hanging_sign",
            new HangingSignItem(ModBlocks.REVELATION_HANGING_SIGN, ModBlocks.REVELATION_WALL_HANGING_SIGN,new Item.Settings().maxCount(16)));
    public static final Item REVELATION_BOAT = TerraformBoatItemHelper.registerBoatItem(
            ModBoats.REVELATION_BOAT,ModBoats.REVELATION_BOAT_KEY,false);
    public static final Item REVELATION_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(
            ModBoats.REVELATION_CHEST_BOAT,ModBoats.REVELATION_BOAT_KEY,true);

    public static final Item PERMEAROMA_LEAF_PIECES = registerItem("permearoma_leaf_pieces", new Item(new Item.Settings()));
    public static final Item PERMEAROMA_SIGN = registerItem("permearoma_sign",
            new SignItem(new Item.Settings().maxCount(16),ModBlocks.PERMEAROMA_SIGN, ModBlocks.PERMEAROMA_WALL_SIGN));
    public static final Item PERMEAROMA_HANGING_SIGN = registerItem("permearoma_hanging_sign",
            new HangingSignItem(ModBlocks.PERMEAROMA_HANGING_SIGN, ModBlocks.PERMEAROMA_WALL_HANGING_SIGN,new Item.Settings().maxCount(16)));
    public static final Item PERMEAROMA_BOAT = TerraformBoatItemHelper.registerBoatItem(
            ModBoats.PERMEAROMA_BOAT,ModBoats.PERMEAROMA_BOAT_KEY,false);
    public static final Item PERMEAROMA_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(
            ModBoats.PERMEAROMA_CHEST_BOAT,ModBoats.PERMEAROMA_BOAT_KEY,true);
    public static final Item PERMEAROMA_SWORD = registerItems("permearoma_sword",new ModSwordItem(ModToolMaterials.PERMEAROMA,
            3,-2.0f,new Item.Settings()));
    public static final Item PERMEAROMA_SHOVEL = registerItems("permearoma_shovel",new ModShovelItem(ModToolMaterials.PERMEAROMA,
            1.5F, -3.0F, new Item.Settings()));
    public static final Item PERMEAROMA_PICKAXE = registerItems("permearoma_pickaxe",new ModPickaxeItem(ModToolMaterials.PERMEAROMA,
            1, -2.8F, new Item.Settings()));
    public static final Item PERMEAROMA_AXE = registerItems("permearoma_axe",new ModAxeItem(ModToolMaterials.PERMEAROMA,
            6.0F, -3.2F, new Item.Settings()));
    public static final Item PERMEAROMA_HOE = registerItems("permearoma_hoe",new ModHoeItem(ModToolMaterials.PERMEAROMA,
            0, -3.0F, new Item.Settings()));

    public static final Item APHRODISIA_SIGN = registerItem("aphrodisia_sign",
            new SignItem(new Item.Settings().maxCount(16),ModBlocks.APHRODISIA_SIGN, ModBlocks.APHRODISIA_WALL_SIGN));
    public static final Item APHRODISIA_HANGING_SIGN = registerItem("aphrodisia_hanging_sign",
            new HangingSignItem(ModBlocks.APHRODISIA_HANGING_SIGN, ModBlocks.APHRODISIA_WALL_HANGING_SIGN,new Item.Settings().maxCount(16)));
    public static final Item APHRODISIA_BOAT = TerraformBoatItemHelper.registerBoatItem(
            ModBoats.APHRODISIA_BOAT,ModBoats.APHRODISIA_BOAT_KEY,false);
    public static final Item APHRODISIA_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(
            ModBoats.APHRODISIA_CHEST_BOAT,ModBoats.APHRODISIA_BOAT_KEY,true);
    public static final Item APHRODISIA_SWORD = registerItems("aphrodisia_sword",new ModSwordItem(ModToolMaterials.APHRODISIA,
            3,-2.0f,new Item.Settings()));
    public static final Item APHRODISIA_SHOVEL = registerItems("aphrodisia_shovel",new ModShovelItem(ModToolMaterials.APHRODISIA,
            1.5F, -3.0F, new Item.Settings()));
    public static final Item APHRODISIA_PICKAXE = registerItems("aphrodisia_pickaxe",new ModPickaxeItem(ModToolMaterials.APHRODISIA,
            1, -2.8F, new Item.Settings()));
    public static final Item APHRODISIA_AXE = registerItems("aphrodisia_axe",new ModAxeItem(ModToolMaterials.APHRODISIA,
            6.0F, -3.2F, new Item.Settings()));
    public static final Item APHRODISIA_HOE = registerItems("aphrodisia_hoe",new ModHoeItem(ModToolMaterials.APHRODISIA,
            0, -3.0F, new Item.Settings()));

    public static final Item END_RESIN = registerItem("end_resin", new Item(new Item.Settings()));
    public static final Item END_WOODEN_SIGN = registerItem("end_wooden_sign",
            new SignItem(new Item.Settings().maxCount(16),ModBlocks.END_WOODEN_SIGN, ModBlocks.END_WOODEN_WALL_SIGN));
    public static final Item END_WOODEN_HANGING_SIGN = registerItem("end_wooden_hanging_sign",
            new HangingSignItem(ModBlocks.END_WOODEN_HANGING_SIGN, ModBlocks.END_WOODEN_WALL_HANGING_SIGN,new Item.Settings().maxCount(16)));
    public static final Item END_WOODEN_BOAT = TerraformBoatItemHelper.registerBoatItem(
            ModBoats.END_WOODEN_BOAT,ModBoats.END_WOODEN_BOAT_KEY,false);
    public static final Item END_WOODEN_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(
            ModBoats.END_WOODEN_CHEST_BOAT,ModBoats.END_WOODEN_BOAT_KEY,true);
    public static final Item END_WOODEN_SWORD = registerItems("end_wooden_sword",new ModSwordItem(ModToolMaterials.END_WOODEN,
            3,-2.0f,new Item.Settings()));
    public static final Item END_WOODEN_SHOVEL = registerItems("end_wooden_shovel",new ModShovelItem(ModToolMaterials.END_WOODEN,
            1.5F, -3.0F, new Item.Settings()));
    public static final Item END_WOODEN_PICKAXE = registerItems("end_wooden_pickaxe",new ModPickaxeItem(ModToolMaterials.END_WOODEN,
            1, -2.8F, new Item.Settings()));
    public static final Item END_WOODEN_AXE = registerItems("end_wooden_axe",new ModAxeItem(ModToolMaterials.END_WOODEN,
            6.0F, -3.2F, new Item.Settings()));
    public static final Item END_WOODEN_HOE = registerItems("end_wooden_hoe",new ModHoeItem(ModToolMaterials.END_WOODEN,
            -4, 0, new Item.Settings()));



    public static final Item MUTATED_END_WOODEN_SIGN = registerItem("mutated_end_wooden_sign",
            new SignItem(new Item.Settings().maxCount(16),ModBlocks.MUTATED_END_WOODEN_SIGN, ModBlocks.MUTATED_END_WOODEN_WALL_SIGN));
    public static final Item MUTATED_END_WOODEN_HANGING_SIGN = registerItem("mutated_end_wooden_hanging_sign",
            new HangingSignItem(ModBlocks.MUTATED_END_WOODEN_HANGING_SIGN, ModBlocks.MUTATED_END_WOODEN_WALL_HANGING_SIGN,new Item.Settings().maxCount(16)));
    public static final Item MUTATED_END_WOODEN_BOAT = TerraformBoatItemHelper.registerBoatItem(
            ModBoats.MUTATED_END_WOODEN_BOAT,ModBoats.MUTATED_END_WOODEN_BOAT_KEY,false);
    public static final Item MUTATED_END_WOODEN_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(
            ModBoats.MUTATED_END_WOODEN_CHEST_BOAT,ModBoats.MUTATED_END_WOODEN_BOAT_KEY,true);
    public static final Item MUTATED_END_RESIN = registerItem("mutated_end_resin", new Item(new Item.Settings()));
    public static final Item MUTATED_END_WOODEN_SWORD = registerItems("mutated_end_wooden_sword",new ModSwordItem(ModToolMaterials.MUTATED_END_WOODEN,
            3,-2.0f,new Item.Settings()));
    public static final Item MUTATED_END_WOODEN_SHOVEL = registerItems("mutated_end_wooden_shovel",new ModShovelItem(ModToolMaterials.MUTATED_END_WOODEN,
            1.5F, -3.0F, new Item.Settings()));
    public static final Item MUTATED_END_WOODEN_PICKAXE = registerItems("mutated_end_wooden_pickaxe",new ModPickaxeItem(ModToolMaterials.MUTATED_END_WOODEN,
            1, -2.8F, new Item.Settings()));
    public static final Item MUTATED_END_WOODEN_AXE = registerItems("mutated_end_wooden_axe",new ModAxeItem(ModToolMaterials.MUTATED_END_WOODEN,
            6.0F, -3.2F, new Item.Settings()));
    public static final Item MUTATED_END_WOODEN_HOE = registerItems("mutated_end_wooden_hoe",new ModHoeItem(ModToolMaterials.MUTATED_END_WOODEN,
            -4, 0, new Item.Settings()));



    public static final Item VOID_FRUIT = registerItems("void_fruit",new Item(new Item.Settings().food(ModFoodComponents.VOID_FRUIT)));
    public static final Item VOID_RESIN = registerItem("void_resin", new Item(new Item.Settings()));
    public static final Item VOID_WOODEN_SIGN = registerItem("void_wooden_sign",
            new SignItem(new Item.Settings().maxCount(16),ModBlocks.VOID_WOODEN_SIGN, VOID_WOODEN_WALL_SIGN));
    public static final Item VOID_WOODEN_HANGING_SIGN = registerItem("void_wooden_hanging_sign",
            new HangingSignItem(ModBlocks.VOID_WOODEN_HANGING_SIGN, VOID_WOODEN_WALL_HANGING_SIGN,new Item.Settings().maxCount(16)));
    public static final Item VOID_WOODEN_BOAT = TerraformBoatItemHelper.registerBoatItem(
            ModBoats.VOID_WOODEN_BOAT,ModBoats.VOID_WOODEN_BOAT_KEY,false);
    public static final Item VOID_WOODEN_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(
            ModBoats.VOID_WOODEN_CHEST_BOAT,ModBoats.VOID_WOODEN_BOAT_KEY,true);
    public static final Item VOID_WOODEN_SWORD = registerItems("void_wooden_sword",new ModSwordItem(ModToolMaterials.VOID_WOODEN,
            3,-2.0f,new Item.Settings()));
    public static final Item VOID_WOODEN_SHOVEL = registerItems("void_wooden_shovel",new ModShovelItem(ModToolMaterials.VOID_WOODEN,
            1.5F, -3.0F, new Item.Settings()));
    public static final Item VOID_WOODEN_PICKAXE = registerItems("void_wooden_pickaxe",new ModPickaxeItem(ModToolMaterials.VOID_WOODEN,
            1, -2.8F, new Item.Settings()));
    public static final Item VOID_WOODEN_AXE = registerItems("void_wooden_axe",new ModAxeItem(ModToolMaterials.VOID_WOODEN,
            6.0F, -3.2F, new Item.Settings()));
    public static final Item VOID_WOODEN_HOE = registerItems("void_wooden_hoe",new ModHoeItem(ModToolMaterials.VOID_WOODEN,
            -4, 0, new Item.Settings()));



    public static Item registerItem(String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(FantasyForest.MOD_ID, id), item);
    }

    public static Item registerItems(String id, Item item) {
        return Registry.register(Registries.ITEM, RegistryKey.of(Registries.ITEM.getKey(),new Identifier(FantasyForest.MOD_ID, id)),item);
    }

    private static void addItemToItemGroup(FabricItemGroupEntries entries){

        entries.add(SEED_OF_REVELATION);
        entries.add(OAK_STICK);
        entries.add(SPRUCE_STICK);
        entries.add(BIRCH_STICK);
        entries.add(JUNGLE_STICK);
        entries.add(ACACIA_STICK);
        entries.add(DARK_OAK_STICK);
        entries.add(MANGROVE_STICK);
        entries.add(CHERRY_STICK);
        entries.add(CRIMSON_STICK);
        entries.add(WARPED_STICK);
        entries.add(REVELATION_STICK);
        entries.add(PERMEAROMA_STICK);
        entries.add(END_STICK);
        entries.add(MUTATED_END_STICK);
        entries.add(VOID_STICK);
        entries.add(PERMEAROMA_LEAF_PIECES);
        entries.add(END_RESIN);
        entries.add(VOID_RESIN);
    }

    private static void addItemToItemGroupB(FabricItemGroupEntries entries){

        entries.add(OAK_STICK);
        entries.add(SPRUCE_STICK);
        entries.add(BIRCH_STICK);
        entries.add(JUNGLE_STICK);
        entries.add(ACACIA_STICK);
        entries.add(DARK_OAK_STICK);
        entries.add(MANGROVE_STICK);
        entries.add(CHERRY_STICK);
        entries.add(CRIMSON_STICK);
        entries.add(WARPED_STICK);

        entries.add(REVELATION_LOG);
        entries.add(REVELATION_WOOD);
        entries.add(STRIPPED_REVELATION_LOG);
        entries.add(STRIPPED_REVELATION_WOOD);
        entries.add(REVELATION_PLANKS);
        entries.add(REVELATION_STAIRS);
        entries.add(REVELATION_SLAB);
        entries.add(REVELATION_FENCE);
        entries.add(REVELATION_FENCE_GATE);
        entries.add(REVELATION_DOOR);
        entries.add(REVELATION_TRAPDOOR);
        entries.add(REVELATION_PRESSURE_PLATE);
        entries.add(REVELATION_BUTTON);
        entries.add(REVELATION_STICK);

        entries.add(PERMEAROMA_LOG);
        entries.add(PERMEAROMA_WOOD);
        entries.add(STRIPPED_PERMEAROMA_LOG);
        entries.add(STRIPPED_PERMEAROMA_WOOD);
        entries.add(PERMEAROMA_PLANKS);
        entries.add(PERMEAROMA_STAIRS);
        entries.add(PERMEAROMA_SLAB);
        entries.add(PERMEAROMA_FENCE);
        entries.add(PERMEAROMA_FENCE_GATE);
        entries.add(PERMEAROMA_DOOR);
        entries.add(PERMEAROMA_TRAPDOOR);
        entries.add(PERMEAROMA_PRESSURE_PLATE);
        entries.add(PERMEAROMA_BUTTON);
        entries.add(PERMEAROMA_STICK);

        entries.add(APHRODISIA_LOG);
        entries.add(APHRODISIA_WOOD);
        entries.add(STRIPPED_APHRODISIA_LOG);
        entries.add(STRIPPED_APHRODISIA_WOOD);
        entries.add(APHRODISIA_PLANKS);
        entries.add(APHRODISIA_STAIRS);
        entries.add(APHRODISIA_SLAB);
        entries.add(APHRODISIA_FENCE);
        entries.add(APHRODISIA_FENCE_GATE);
        entries.add(APHRODISIA_DOOR);
        entries.add(APHRODISIA_TRAPDOOR);
        entries.add(APHRODISIA_PRESSURE_PLATE);
        entries.add(APHRODISIA_BUTTON);
        entries.add(APHRODISIA_STICK);

        entries.add(END_RESIN_BLOCK);
        entries.add(END_LOG);
        entries.add(END_WOOD);
        entries.add(STRIPPED_END_LOG);
        entries.add(STRIPPED_END_WOOD);
        entries.add(END_PLANKS);
        entries.add(END_WOODEN_STAIRS);
        entries.add(END_WOODEN_SLAB);
        entries.add(END_WOODEN_FENCE);
        entries.add(END_WOODEN_FENCE_GATE);
        entries.add(END_WOODEN_DOOR);
        entries.add(END_WOODEN_TRAPDOOR);
        entries.add(END_WOODEN_PRESSURE_PLATE);
        entries.add(END_WOODEN_BUTTON);
        entries.add(END_STICK);

        entries.add(MUTATED_END_RESIN_BLOCK);
        entries.add(MUTATED_END_LOG);
        entries.add(MUTATED_END_WOOD);
        entries.add(STRIPPED_MUTATED_END_LOG);
        entries.add(STRIPPED_MUTATED_END_WOOD);
        entries.add(MUTATED_END_PLANKS);
        entries.add(MUTATED_END_WOODEN_STAIRS);
        entries.add(MUTATED_END_WOODEN_SLAB);
        entries.add(MUTATED_END_WOODEN_FENCE);
        entries.add(MUTATED_END_WOODEN_FENCE_GATE);
        entries.add(MUTATED_END_WOODEN_DOOR);
        entries.add(MUTATED_END_WOODEN_TRAPDOOR);
        entries.add(MUTATED_END_WOODEN_PRESSURE_PLATE);
        entries.add(MUTATED_END_WOODEN_BUTTON);
        entries.add(MUTATED_END_STICK);

        entries.add(VOID_RESIN_BLOCK);
        entries.add(VOID_LOG);
        entries.add(VOID_WOOD);
        entries.add(STRIPPED_VOID_LOG);
        entries.add(STRIPPED_VOID_WOOD);
        entries.add(VOID_PLANKS);
        entries.add(VOID_WOODEN_STAIRS);
        entries.add(VOID_WOODEN_SLAB);
        entries.add(VOID_WOODEN_FENCE);
        entries.add(VOID_WOODEN_FENCE_GATE);
        entries.add(VOID_WOODEN_DOOR);
        entries.add(VOID_WOODEN_TRAPDOOR);
        entries.add(VOID_WOODEN_PRESSURE_PLATE);
        entries.add(VOID_WOODEN_BUTTON);
        entries.add(VOID_STICK);
    }

    private static void addItemToItemGroupC(FabricItemGroupEntries entries){
        entries.add(PERMEAROMA_SWORD);
        entries.add(APHRODISIA_SWORD);
        entries.add(END_WOODEN_SWORD);
        entries.add(MUTATED_END_WOODEN_SWORD);
        entries.add(VOID_WOODEN_SWORD);
        entries.add(PERMEAROMA_AXE);
        entries.add(APHRODISIA_AXE);
        entries.add(END_WOODEN_AXE);
        entries.add(MUTATED_END_WOODEN_AXE);
        entries.add(VOID_WOODEN_AXE);
    }

    private static void addItemToItemGroupN(FabricItemGroupEntries entries){
        entries.add(END_LICHEN);
        entries.add(DEAD_END_LICHEN);

        entries.add(REVELATION_LOG);
        entries.add(PERMEAROMA_LOG);
        entries.add(APHRODISIA_LOG);
        entries.add(END_LOG);
        entries.add(MUTATED_END_LOG);
        entries.add(VOID_LOG);
        entries.add(VOID_ROOTS);

        entries.add(REVELATION_LEAVES);
        entries.add(PERMEAROMA_LEAVES);
        entries.add(APHRODISIA_LEAVES);
        entries.add(END_LEAVES);
        entries.add(VOID_LEAVES);

        entries.add(REVELATION_SAPLING);
        entries.add(PERMEAROMA_SAPLING);
        entries.add(END_TREE_SAPLING);
        entries.add(VOID_TREE_SAPLING);

        entries.add(REVELATION_CORE);
        entries.add(REVELATION_CORE_SIDE);

        entries.add(VOID_VINE_BODY);
        entries.add(VOID_VINE_HEAD);
        entries.add(FRUITING_VOID_VINE_HEAD);
        entries.add(PHOSPHOR);
    }

    private static void addItemToItemGroupF(FabricItemGroupEntries entries){

        entries.add(VOID_FRUIT);
    }

    private static void addItemToItemGroupG(FabricItemGroupEntries entries){
        entries.add(ARBOREAL_CRAFTING_TABLE);
        entries.add(ENDER_TRANSMISSION_SUPPRESSOR);
        entries.add(REVELATION_SIGN);
        entries.add(REVELATION_HANGING_SIGN);
        entries.add(PERMEAROMA_SIGN);
        entries.add(PERMEAROMA_HANGING_SIGN);
        entries.add(APHRODISIA_SIGN);
        entries.add(APHRODISIA_HANGING_SIGN);
        entries.add(END_WOODEN_SIGN);
        entries.add(END_WOODEN_HANGING_SIGN);
        entries.add(MUTATED_END_WOODEN_SIGN);
        entries.add(MUTATED_END_WOODEN_HANGING_SIGN);
        entries.add(VOID_WOODEN_SIGN);
        entries.add(VOID_WOODEN_HANGING_SIGN);
    }

    private static void addItemToItemGroupT(FabricItemGroupEntries entries){
        entries.add(PERMEAROMA_HOE);
        entries.add(PERMEAROMA_PICKAXE);
        entries.add(PERMEAROMA_AXE);
        entries.add(PERMEAROMA_SHOVEL);
        entries.add(APHRODISIA_HOE);
        entries.add(APHRODISIA_PICKAXE);
        entries.add(APHRODISIA_AXE);
        entries.add(APHRODISIA_SHOVEL);
        entries.add(END_WOODEN_HOE);
        entries.add(END_WOODEN_PICKAXE);
        entries.add(END_WOODEN_AXE);
        entries.add(END_WOODEN_SHOVEL);
        entries.add(MUTATED_END_WOODEN_HOE);
        entries.add(MUTATED_END_WOODEN_PICKAXE);
        entries.add(MUTATED_END_WOODEN_AXE);
        entries.add(MUTATED_END_WOODEN_SHOVEL);
        entries.add(VOID_WOODEN_HOE);
        entries.add(VOID_WOODEN_PICKAXE);
        entries.add(VOID_WOODEN_AXE);
        entries.add(VOID_WOODEN_SHOVEL);
        entries.add(PERMEAROMA_BOAT);
        entries.add(PERMEAROMA_CHEST_BOAT);
        entries.add(END_WOODEN_BOAT);
        entries.add(END_WOODEN_CHEST_BOAT);
        entries.add(MUTATED_END_WOODEN_BOAT);
        entries.add(MUTATED_END_WOODEN_CHEST_BOAT);
        entries.add(VOID_WOODEN_BOAT);
        entries.add(VOID_WOODEN_CHEST_BOAT);
    }

    private static void addItemToItemGroupR(FabricItemGroupEntries entries){
        entries.add(PHOSPHOR);
    }

    public static void registerItems(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(MODItems::addItemToItemGroupC);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(MODItems::addItemToItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(MODItems::addItemToItemGroupB);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(MODItems::addItemToItemGroupN);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(MODItems::addItemToItemGroupF);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(MODItems::addItemToItemGroupG);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(MODItems::addItemToItemGroupT);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(MODItems::addItemToItemGroupR);
    }

}
