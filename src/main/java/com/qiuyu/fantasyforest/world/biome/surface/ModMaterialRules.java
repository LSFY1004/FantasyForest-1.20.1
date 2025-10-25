//package com.qiuyu.fantasyforest.world.biome.surface;
//
//import com.qiuyu.fantasyforest.FantasyForest;
//import com.qiuyu.fantasyforest.block.ModBlocks;
//import com.qiuyu.fantasyforest.world.biome.ModBiomes;
//import net.minecraft.block.Block;
//import net.minecraft.block.Blocks;
//import net.minecraft.util.Identifier;
//import net.minecraft.world.gen.surfacebuilder.MaterialRules;
//
//public class ModMaterialRules {
//    private static final MaterialRules.MaterialRule END_STONE = makeStateRule(Blocks.END_STONE);
//    private static final MaterialRules.MaterialRule END_LICHEN = makeStateRule(ModBlocks.END_LICHEN);
//    public static final Identifier END_PLAINS_SURFACE = new Identifier(FantasyForest.MOD_ID, "end_plains_surface");
//
//    public static MaterialRules.MaterialRule makeRules(){
////        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1,0);
//
//        return MaterialRules.sequence(
//                MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(ModBiomes.END_PLAINS),
//                        MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR,END_STONE)),
//                        MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING,END_STONE))
//                ,MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR,END_LICHEN)
//        );
//    }
//
//    private static MaterialRules.MaterialRule makeStateRule(Block block){
//        return MaterialRules.block(block.getDefaultState());
//    }
//}
