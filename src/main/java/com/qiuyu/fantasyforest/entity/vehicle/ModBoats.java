package com.qiuyu.fantasyforest.entity.vehicle;

import com.qiuyu.fantasyforest.FantasyForest;
import com.qiuyu.fantasyforest.block.ModBlocks;
import com.qiuyu.fantasyforest.item.MODItems;
import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModBoats {

    public static final Identifier REVELATION_BOAT = new Identifier(FantasyForest.MOD_ID,"revelation_boat");
    public static final Identifier REVELATION_CHEST_BOAT = new Identifier(FantasyForest.MOD_ID,"revelation_chest_boat");
    public static final RegistryKey<TerraformBoatType> REVELATION_BOAT_KEY = TerraformBoatTypeRegistry.createKey(REVELATION_BOAT);

    public static final Identifier PERMEAROMA_BOAT = new Identifier(FantasyForest.MOD_ID,"permearoma_boat");
    public static final Identifier PERMEAROMA_CHEST_BOAT = new Identifier(FantasyForest.MOD_ID,"permearoma_chest_boat");
    public static final RegistryKey<TerraformBoatType> PERMEAROMA_BOAT_KEY = TerraformBoatTypeRegistry.createKey(PERMEAROMA_BOAT);

    public static final Identifier END_WOODEN_BOAT = new Identifier(FantasyForest.MOD_ID,"end_wooden_boat");
    public static final Identifier END_WOODEN_CHEST_BOAT = new Identifier(FantasyForest.MOD_ID,"end_wooden_chest_boat");
    public static final RegistryKey<TerraformBoatType> END_WOODEN_BOAT_KEY = TerraformBoatTypeRegistry.createKey(END_WOODEN_BOAT);

    public static final Identifier MUTATED_END_WOODEN_BOAT = new Identifier(FantasyForest.MOD_ID,"mutated_end_wooden_boat");
    public static final Identifier MUTATED_END_WOODEN_CHEST_BOAT = new Identifier(FantasyForest.MOD_ID,"mutated_end_wooden_chest_boat");
    public static final RegistryKey<TerraformBoatType> MUTATED_END_WOODEN_BOAT_KEY = TerraformBoatTypeRegistry.createKey(MUTATED_END_WOODEN_BOAT);

    public static final Identifier VOID_WOODEN_BOAT = new Identifier(FantasyForest.MOD_ID,"void_wooden_boat");
    public static final Identifier VOID_WOODEN_CHEST_BOAT = new Identifier(FantasyForest.MOD_ID,"void_wooden_chest_boat");
    public static final RegistryKey<TerraformBoatType> VOID_WOODEN_BOAT_KEY = TerraformBoatTypeRegistry.createKey(VOID_WOODEN_BOAT);

    public static void registerBoats(){
        TerraformBoatType REVELATION_BOAT_TYPE = new TerraformBoatType.Builder()
                .item(MODItems.REVELATION_BOAT)
                .chestItem(MODItems.REVELATION_CHEST_BOAT)
                .planks(ModBlocks.REVELATION_PLANKS.asItem())
                .build();
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, REVELATION_BOAT_KEY, REVELATION_BOAT_TYPE);

        TerraformBoatType PERMEAROMA_BOAT_TYPE = new TerraformBoatType.Builder()
                .item(MODItems.PERMEAROMA_BOAT)
                .chestItem(MODItems.PERMEAROMA_CHEST_BOAT)
                .planks(ModBlocks.PERMEAROMA_PLANKS.asItem())
                .build();
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, PERMEAROMA_BOAT_KEY, PERMEAROMA_BOAT_TYPE);

        TerraformBoatType END_WOODEN_BOAT_TYPE = new TerraformBoatType.Builder()
                .item(MODItems.END_WOODEN_BOAT)
                .chestItem(MODItems.END_WOODEN_CHEST_BOAT)
                .planks(ModBlocks.END_PLANKS.asItem())
                .build();
        Registry.register(TerraformBoatTypeRegistry.INSTANCE,END_WOODEN_BOAT_KEY,END_WOODEN_BOAT_TYPE);

        TerraformBoatType MUTATED_END_WOODEN_BOAT_TYPE = new TerraformBoatType.Builder()
                .item(MODItems.MUTATED_END_WOODEN_BOAT)
                .chestItem(MODItems.MUTATED_END_WOODEN_CHEST_BOAT)
                .planks(ModBlocks.MUTATED_END_PLANKS.asItem())
                .build();
        Registry.register(TerraformBoatTypeRegistry.INSTANCE,MUTATED_END_WOODEN_BOAT_KEY,MUTATED_END_WOODEN_BOAT_TYPE);

        TerraformBoatType VOID_WOODEN_BOAT_TYPE = new TerraformBoatType.Builder()
                .item(MODItems.VOID_WOODEN_BOAT)
                .chestItem(MODItems.VOID_WOODEN_CHEST_BOAT)
                .planks(ModBlocks.VOID_PLANKS.asItem())
                .build();
        Registry.register(TerraformBoatTypeRegistry.INSTANCE,VOID_WOODEN_BOAT_KEY,VOID_WOODEN_BOAT_TYPE);
    }
}
