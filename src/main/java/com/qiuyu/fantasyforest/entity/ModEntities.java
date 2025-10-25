package com.qiuyu.fantasyforest.entity;

import com.qiuyu.fantasyforest.FantasyForest;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<SeatEntity> SEAT = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(FantasyForest.MOD_ID,"seat"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC,SeatEntity::new).build());

    public static void registerEntities(){

    }
}
