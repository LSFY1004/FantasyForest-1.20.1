package com.qiuyu.fantasyforest.tag;

import com.qiuyu.fantasyforest.FantasyForest;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModItemTags {
    public static final TagKey<Item> END_LOG = of("end_log");
    public static final TagKey<Item> VOID_LOG = of("void_log");
    public static final TagKey<Item> STICKS = of("sticks");
    public static final TagKey<Item> PERMEAROMA_ARBOREAL = of("permearoma_arboreal");
    public static final TagKey<Item> END_ARBOREAL = of("end_arboreal");
    public static final TagKey<Item> MUTATED_END_ARBOREAL = of("mutated_end_arboreal");
    public static final TagKey<Item> VOID_ARBOREAL = of("void_arboreal");

    private static TagKey<Item> of(String id){
        return TagKey.of(RegistryKeys.ITEM,new Identifier(FantasyForest.MOD_ID,id));
    }
}
