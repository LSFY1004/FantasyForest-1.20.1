package com.qiuyu.fantasyforest;

import com.qiuyu.fantasyforest.block.ModBlocks;
import com.qiuyu.fantasyforest.entity.vehicle.ModBoats;
import com.qiuyu.fantasyforest.screen.ModScreenHandlers;
import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

public class FantasyForestClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.ARBOREAL_CRAFTING_TABLE_SCREEN_HANDLER, ArborealCraftingTableScreen::new);

        KeyHelper.register(new ClientKeyChecker());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.REVELATION_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.REVELATION_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.REVELATION_SAPLING, RenderLayer.getCutout());
        TerraformBoatClientHelper.registerModelLayers(ModBoats.REVELATION_BOAT,false);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PERMEAROMA_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PERMEAROMA_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PERMEAROMA_SAPLING, RenderLayer.getCutout());
        TerraformBoatClientHelper.registerModelLayers(ModBoats.PERMEAROMA_BOAT,false);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.END_WOODEN_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.END_WOODEN_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.END_TREE_SAPLING, RenderLayer.getCutout());
        TerraformBoatClientHelper.registerModelLayers(ModBoats.END_WOODEN_BOAT,false);

		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MUTATED_END_WOODEN_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MUTATED_END_WOODEN_TRAPDOOR, RenderLayer.getCutout());
        TerraformBoatClientHelper.registerModelLayers(ModBoats.MUTATED_END_WOODEN_BOAT,false);

		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VOID_WOODEN_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VOID_WOODEN_TRAPDOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VOID_TREE_SAPLING, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VOID_ROOTS,RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VOID_VINE_HEAD,RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VOID_VINE_BODY,RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRUITING_VOID_VINE_HEAD,RenderLayer.getCutout());
		TerraformBoatClientHelper.registerModelLayers(ModBoats.VOID_WOODEN_BOAT,false);
	}

}