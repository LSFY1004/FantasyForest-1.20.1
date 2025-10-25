//package com.qiuyu.fantasyforest.world.biome;
//
//import com.qiuyu.fantasyforest.FantasyForest;
//import com.qiuyu.fantasyforest.world.biome.surface.ModMaterialRules;
//import net.minecraft.util.Identifier;
//import terrablender.api.Regions;
//import terrablender.api.SurfaceRuleManager;
//import terrablender.api.TerraBlenderApi;
//
//public class ModTerraBlenderAPI implements TerraBlenderApi {
//    @Override
//    public void onTerraBlenderInitialized() {
//        Regions.register(new ModOverworldRegion(new Identifier(FantasyForest.MOD_ID,"overworld"),0));
//        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD,FantasyForest.MOD_ID, ModMaterialRules.makeRules());
//    }
//}
