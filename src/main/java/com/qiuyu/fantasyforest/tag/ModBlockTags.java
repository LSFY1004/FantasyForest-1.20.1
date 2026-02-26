package com.qiuyu.fantasyforest.tag;

import com.qiuyu.fantasyforest.FantasyForest;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModBlockTags {
    public static final TagKey<Block> END_WOODEN_BLOCKS = of("end_wooden_blocks");
    public static final TagKey<Block> VOID_WOODEN_BLOCKS = of("void_wooden_blocks");
    public static final TagKey<Block> MUTATED_END_WOODEN_BLOCKS = of("mutated_end_wooden_blocks");
    public static final TagKey<Block> PERMEAROMA_BLOCKS = of("permearoma_blocks");
    public static final TagKey<Block> REVELATION_BLOCKS = of("revelation_blocks");
    public static final TagKey<Block> STICKS = of("sticks");

    private static TagKey<Block> of(String id){
        return TagKey.of(RegistryKeys.BLOCK,new Identifier(FantasyForest.MOD_ID,id));
    }
}
