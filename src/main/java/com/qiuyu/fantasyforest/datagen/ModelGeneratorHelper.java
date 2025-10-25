package com.qiuyu.fantasyforest.datagen;

import com.google.gson.JsonObject;
import com.qiuyu.fantasyforest.FantasyForest;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ModelGeneratorHelper {

    private static final Set<Identifier> EXCLUDED_BLOCKS = new HashSet<>();

    static {
        // 添加所有木棍方块到排除列表
        String[] woodTypes = {"oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "mangrove", "cherry", "bamboo", "crimson", "warped"
        ,"permearoma", "end","mutated_end","void"};
        for (String woodType : woodTypes) {
            EXCLUDED_BLOCKS.add(new Identifier("fantasy-forest", woodType + "_stick"));
        }
    }
    private static final Map<Item, Identifier> BLOCK_ITEMS_TO_REGISTER = new ConcurrentHashMap<>();
    public static void registerGrassLikeBlock(Block block,BlockStateModelGenerator generator) {
        Identifier blockId = Registries.BLOCK.getId(block);
        String blockPath = blockId.getPath();
        // 创建模型ID
        Identifier modelId = new Identifier(FantasyForest.MOD_ID, "block/" + blockPath);

        // 创建模型JSON
        JsonObject modelJson = new JsonObject();
        modelJson.addProperty("parent", "minecraft:block/cube_bottom_top");

        JsonObject textures = new JsonObject();
        textures.addProperty("bottom", FantasyForest.MOD_ID + ":block/" + blockPath + "_bottom");
        textures.addProperty("side", FantasyForest.MOD_ID + ":block/" + blockPath + "_side");
        textures.addProperty("top", FantasyForest.MOD_ID + ":block/" + blockPath + "_top");

        modelJson.add("textures", textures);

        // 注册模型
        generator.modelCollector.accept(
                modelId,
                () -> modelJson
        );

        // 注册方块状态
        generator.blockStateCollector.accept(
                BlockStateModelGenerator.createSingletonBlockState(block, modelId)
        );

        Item item = block.asItem();
        if (item != Items.AIR) {
            BLOCK_ITEMS_TO_REGISTER.put(item, modelId);
        }

    }
    public static void registerBlockItemModels(ItemModelGenerator generator) {
        for (Map.Entry<Item, Identifier> entry : BLOCK_ITEMS_TO_REGISTER.entrySet()) {
            Item item = entry.getKey();

            // 检查该物品对应的方块是否在排除列表中
            if (item instanceof BlockItem blockItem) {
                Block block = blockItem.getBlock();
                Identifier blockId = Registries.BLOCK.getId(block);

                if (EXCLUDED_BLOCKS.contains(blockId)) {
                    continue; // 跳过这个物品的注册
                }
            }

            // 正常注册不在排除列表中的物品
            generator.register(entry.getKey(), new Model(Optional.of(entry.getValue()), Optional.empty()));
        }
    }
    public static void clearBlockItemsRegistry() {
        BLOCK_ITEMS_TO_REGISTER.clear();
    }
}