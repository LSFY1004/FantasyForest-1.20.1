// ModBlockEntities.java
package com.qiuyu.fantasyforest.block.entity;

import com.qiuyu.fantasyforest.FantasyForest;
import com.qiuyu.fantasyforest.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static BlockEntityType<ArborealCraftingTableEntity> ARBOREAL_CRAFTING_TABLE;

    public static void registerBlockEntities() {
        ARBOREAL_CRAFTING_TABLE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier(FantasyForest.MOD_ID, "arboreal_crafting_table"),
                FabricBlockEntityTypeBuilder.create(
                        ArborealCraftingTableEntity::new,
                        ModBlocks.ARBOREAL_CRAFTING_TABLE
                ).build()
        );
    }
}
