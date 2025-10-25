package com.qiuyu.fantasyforest.tag;

import com.qiuyu.fantasyforest.FantasyForest;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModBlockTags {
    public static final TagKey<Block> END_LOGS = of("end_logs");
    public static final TagKey<Block> VOID_LOGS = of("void_logs");
    public static final TagKey<Block> MUTATED_END_LOGS = of("mutated_end_logs");
    public static final TagKey<Block> STICKS = of("sticks");

    private static TagKey<Block> of(String id){
        return TagKey.of(RegistryKeys.BLOCK,new Identifier(FantasyForest.MOD_ID,id));
    }
}
