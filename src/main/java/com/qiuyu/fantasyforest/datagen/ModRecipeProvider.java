package com.qiuyu.fantasyforest.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.qiuyu.fantasyforest.FantasyForest;
import com.qiuyu.fantasyforest.block.ModBlocks;
import com.qiuyu.fantasyforest.item.MODItems;
import com.qiuyu.fantasyforest.recipe.ArborealCraftingTableRecipe;
import com.qiuyu.fantasyforest.tag.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,ModBlocks.ARBOREAL_CRAFTING_TABLE,1)
                .pattern(" s ")
                .pattern("xox")
                .pattern("111")
                .input('s', ItemTags.SAPLINGS)
                .input('x',ItemTags.PLANKS)
                .input('o', Blocks.CRAFTING_TABLE)
                .input('1',ItemTags.WOODEN_FENCES)
                .criterion(hasItem(Blocks.CRAFTING_TABLE),conditionsFromItem(Blocks.CRAFTING_TABLE))
                .offerTo(exporter,new Identifier(FantasyForest.MOD_ID,getItemPath(ModBlocks.ARBOREAL_CRAFTING_TABLE)));

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, MODItems.END_RESIN,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.END_RESIN_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, MODItems.MUTATED_END_RESIN,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.MUTATED_END_RESIN_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, MODItems.VOID_RESIN,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.VOID_RESIN_BLOCK);

        arborealWoodFamilyRecipes(ModItemTags.PERMEAROMA_ARBOREAL,ModBlocks.PERMEAROMA_PLANKS,ModBlocks.PERMEAROMA_STICK,
                MODItems.PERMEAROMA_SWORD,MODItems.PERMEAROMA_PICKAXE,MODItems.PERMEAROMA_AXE,MODItems.PERMEAROMA_HOE,MODItems.PERMEAROMA_SHOVEL,exporter);
        simpleWoodFamilyRecipes(ModBlocks.PERMEAROMA_LOG,ModBlocks.PERMEAROMA_WOOD,ModBlocks.STRIPPED_PERMEAROMA_LOG,ModBlocks.STRIPPED_PERMEAROMA_WOOD,
                ModBlocks.PERMEAROMA_PLANKS, ModBlocks.PERMEAROMA_SLAB, ModBlocks.PERMEAROMA_PRESSURE_PLATE, ModBlocks.PERMEAROMA_BUTTON,
                ModBlocks.PERMEAROMA_STAIRS, ModBlocks.PERMEAROMA_DOOR, ModBlocks.PERMEAROMA_TRAPDOOR, ModBlocks.PERMEAROMA_FENCE,
                ModBlocks.PERMEAROMA_FENCE_GATE, ModBlocks.PERMEAROMA_SIGN, ModBlocks.PERMEAROMA_HANGING_SIGN, MODItems.PERMEAROMA_BOAT, MODItems.PERMEAROMA_CHEST_BOAT, exporter);

        arborealWoodFamilyRecipes(ModItemTags.END_ARBOREAL,ModBlocks.END_PLANKS,ModBlocks.END_STICK,
                MODItems.END_WOODEN_SWORD,MODItems.END_WOODEN_PICKAXE,MODItems.END_WOODEN_AXE,MODItems.END_WOODEN_HOE,MODItems.END_WOODEN_SHOVEL,exporter);
        simpleWoodFamilyRecipes(ModBlocks.END_LOG,ModBlocks.END_WOOD,ModBlocks.STRIPPED_END_LOG,ModBlocks.STRIPPED_END_WOOD,
                ModBlocks.END_PLANKS, ModBlocks.END_WOODEN_SLAB, ModBlocks.END_WOODEN_PRESSURE_PLATE, ModBlocks.END_WOODEN_BUTTON,
                ModBlocks.END_WOODEN_STAIRS, ModBlocks.END_WOODEN_DOOR, ModBlocks.END_WOODEN_TRAPDOOR, ModBlocks.END_WOODEN_FENCE,
                ModBlocks.END_WOODEN_FENCE_GATE, ModBlocks.END_WOODEN_SIGN, ModBlocks.END_WOODEN_HANGING_SIGN, MODItems.END_WOODEN_BOAT, MODItems.END_WOODEN_CHEST_BOAT, exporter);

        arborealWoodFamilyRecipes(ModItemTags.MUTATED_END_ARBOREAL,ModBlocks.MUTATED_END_PLANKS,ModBlocks.MUTATED_END_STICK,
                MODItems.MUTATED_END_WOODEN_SWORD,MODItems.MUTATED_END_WOODEN_PICKAXE,MODItems.MUTATED_END_WOODEN_AXE,MODItems.MUTATED_END_WOODEN_HOE,MODItems.MUTATED_END_WOODEN_SHOVEL,exporter);
        simpleWoodFamilyRecipes(ModBlocks.MUTATED_END_LOG,ModBlocks.MUTATED_END_WOOD,ModBlocks.STRIPPED_MUTATED_END_LOG,ModBlocks.STRIPPED_MUTATED_END_WOOD,
                ModBlocks.MUTATED_END_PLANKS, ModBlocks.MUTATED_END_WOODEN_SLAB, ModBlocks.MUTATED_END_WOODEN_PRESSURE_PLATE, ModBlocks.MUTATED_END_WOODEN_BUTTON,
                ModBlocks.MUTATED_END_WOODEN_STAIRS, ModBlocks.MUTATED_END_WOODEN_DOOR, ModBlocks.MUTATED_END_WOODEN_TRAPDOOR, ModBlocks.MUTATED_END_WOODEN_FENCE,
                ModBlocks.MUTATED_END_WOODEN_FENCE_GATE, ModBlocks.MUTATED_END_WOODEN_SIGN, ModBlocks.MUTATED_END_WOODEN_HANGING_SIGN, MODItems.MUTATED_END_WOODEN_BOAT, MODItems.MUTATED_END_WOODEN_CHEST_BOAT, exporter);

        arborealWoodFamilyRecipes(ModItemTags.VOID_ARBOREAL,ModBlocks.VOID_PLANKS,ModBlocks.VOID_STICK,
                MODItems.VOID_WOODEN_SWORD,MODItems.VOID_WOODEN_PICKAXE,MODItems.VOID_WOODEN_AXE,MODItems.VOID_WOODEN_HOE,MODItems.VOID_WOODEN_SHOVEL,exporter);
        simpleWoodFamilyRecipes(ModBlocks.VOID_LOG,ModBlocks.VOID_WOOD,ModBlocks.STRIPPED_VOID_LOG,ModBlocks.STRIPPED_VOID_WOOD,
                ModBlocks.VOID_PLANKS, ModBlocks.VOID_WOODEN_SLAB, ModBlocks.VOID_WOODEN_PRESSURE_PLATE, ModBlocks.VOID_WOODEN_BUTTON,
                ModBlocks.VOID_WOODEN_STAIRS, ModBlocks.VOID_WOODEN_DOOR, ModBlocks.VOID_WOODEN_TRAPDOOR, ModBlocks.VOID_WOODEN_FENCE,
                ModBlocks.VOID_WOODEN_FENCE_GATE, ModBlocks.VOID_WOODEN_SIGN, ModBlocks.VOID_WOODEN_HANGING_SIGN, MODItems.VOID_WOODEN_BOAT, MODItems.VOID_WOODEN_CHEST_BOAT, exporter);
    }

    private void arborealWoodFamilyRecipes(TagKey<Item> arboreal, Block plank, Block stick,
                                           Item sword, Item pickaxe, Item axe, Item hoe, Item shovel, Consumer<RecipeJsonProvider> exporter){
        shapedArborealRecipe(exporter,arboreal, new String[]{
                        "1",
                        "1"}
                ,new char[]{'1'}, new Object[]{plank},
                getItemPath(stick), stick, 4);
        shapedArborealRecipe(exporter,arboreal, new String[]{
                        "###",
                        " 1 ",
                        " 1 "}
                ,new char[]{'#','1'}, new Object[]{plank,ModItemTags.STICKS},
                getItemPath(pickaxe), pickaxe, 1);
        shapedArborealRecipe(exporter,arboreal, new String[]{
                        "##",
                        "#1",
                        " 1"}
                ,new char[]{'#','1'}, new Object[]{plank,ModItemTags.STICKS},
                getItemPath(axe), axe, 1);
        shapedArborealRecipe(exporter,arboreal, new String[]{
                        "#",
                        "#",
                        "1"}
                ,new char[]{'#','1'}, new Object[]{plank,ModItemTags.STICKS},
                getItemPath(sword), sword, 1);
        shapedArborealRecipe(exporter,arboreal, new String[]{
                        "##",
                        " 1",
                        " 1"}
                ,new char[]{'#','1'}, new Object[]{plank,ModItemTags.STICKS},
                getItemPath(hoe), hoe, 1);
        shapedArborealRecipe(exporter,arboreal, new String[]{
                        "#",
                        "1",
                        "1"}
                ,new char[]{'#','1'}, new Object[]{plank,ModItemTags.STICKS},
                getItemPath(shovel), shovel, 1);
    }

    private void simpleWoodFamilyRecipes(Block log, Block wood, Block sLog, Block sWood, Block plank,
                                         Block slab, Block pressurePlate, Block button, Block stairs, Block door, Block trapdoor,
                                         Block fence, Block fenceGate, Block sign, Block hangingSign,
                                         Item boat, Item chest_boat, Consumer<RecipeJsonProvider> exporter){
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, plank, 4).input(Ingredient.ofItems(log,wood,sLog,sWood)).group("planks").criterion("has_logs", conditionsFromItem(log)).offerTo(exporter);
        offerSlabRecipe(exporter,RecipeCategory.BUILDING_BLOCKS,slab, plank);
        offerPressurePlateRecipe(exporter,pressurePlate, plank);
        offerShapelessRecipe(exporter,button, plank,"button",1);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS,stairs,4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', plank.asItem())
                .criterion(hasItem(plank),conditionsFromItem(plank))
                .offerTo(exporter,new Identifier(FantasyForest.MOD_ID,getItemPath(stairs)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS,door,3)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .input('#', plank.asItem())
                .criterion(hasItem(plank),conditionsFromItem(plank))
                .offerTo(exporter,new Identifier(FantasyForest.MOD_ID,getItemPath(door)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS,trapdoor,2)
                .pattern("###")
                .pattern("###")
                .input('#', plank.asItem())
                .criterion(hasItem(plank),conditionsFromItem(plank))
                .offerTo(exporter,new Identifier(FantasyForest.MOD_ID,getItemPath(trapdoor)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS,fence,3)
                .pattern("#1#")
                .pattern("#1#")
                .input('#', plank.asItem())
                .input('1', ModItemTags.STICKS)
                .criterion(hasItem(plank),conditionsFromItem(plank))
                .offerTo(exporter,new Identifier(FantasyForest.MOD_ID,getItemPath(fence)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS,fenceGate,1)
                .pattern("1#1")
                .pattern("1#1")
                .input('#', plank.asItem())
                .input('1', ModItemTags.STICKS)
                .criterion(hasItem(plank),conditionsFromItem(plank))
                .offerTo(exporter,new Identifier(FantasyForest.MOD_ID,getItemPath(fenceGate)));
        offerBoatRecipe(exporter,boat, plank);
        offerChestBoatRecipe(exporter,chest_boat,boat);
        offerHangingSignRecipe(exporter,hangingSign, plank);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,sign,1)
                .pattern("###")
                .pattern("###")
                .pattern(" 1 ")
                .input('#', plank.asItem())
                .input('1', ModItemTags.STICKS)
                .criterion(hasItem(plank),conditionsFromItem(plank))
                .offerTo(exporter,new Identifier(FantasyForest.MOD_ID,getItemPath(sign)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS,wood,3)
                .pattern("##")
                .pattern("##")
                .input('#', log.asItem())
                .criterion(hasItem(log),conditionsFromItem(log))
                .offerTo(exporter,new Identifier(FantasyForest.MOD_ID,getItemPath(wood)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, sWood,3)
                .pattern("##")
                .pattern("##")
                .input('#', sLog.asItem())
                .criterion(hasItem(log),conditionsFromItem(log))
                .offerTo(exporter,new Identifier(FantasyForest.MOD_ID,getItemPath(sWood)));
    }
    private void shapedArborealRecipe(Consumer<RecipeJsonProvider> exporter, Object sapling, String[] pattern, char[] keys, Object[] ingredients, String recipeName, ItemConvertible output, int outputCount) {
        // 构建配方JSON对象
        JsonObject json = new JsonObject();

        // 1. 设置配方类型
        json.addProperty("type", "fantasy-forest:arboreal_crafting_table");

        // 2. 添加树苗
        JsonObject saplingJson = new JsonObject();
        if (sapling instanceof ItemConvertible item) {
            saplingJson.addProperty("item", Registries.ITEM.getId(item.asItem()).toString());
        }
        if (sapling instanceof TagKey<?> tagKey) {
            saplingJson.addProperty("tag", tagKey.id().toString());
        }
        json.add("sapling", saplingJson);

        // 3. 添加合成图案
        JsonArray patternJson = new JsonArray();
        for (String line : pattern) {
            patternJson.add(line);
        }
        json.add("pattern", patternJson);

        // 4. 添加键映射
        JsonObject keyJson = new JsonObject();
        for (int i = 0; i < keys.length; i++) {
            JsonObject ingredientJson = new JsonObject();
            if (ingredients[i] instanceof ItemConvertible item) {
                ingredientJson.addProperty("item", Registries.ITEM.getId(item.asItem()).toString());
            }
            if (ingredients[i] instanceof TagKey<?> tagKey) {
                ingredientJson.addProperty("tag", tagKey.id().toString());
            }
            // 这里可以扩展以支持标签(Tag)或其他Ingredient类型
            keyJson.add(String.valueOf(keys[i]), ingredientJson);
        }
        json.add("key", keyJson);

        // 5. 添加合成结果
        JsonObject resultJson = new JsonObject();
        resultJson.addProperty("item", Registries.ITEM.getId(output.asItem()).toString());
        if (outputCount > 1) {
            resultJson.addProperty("count", outputCount);
        }
        json.add("result", resultJson);

        exporter.accept(new RecipeJsonProvider() {
            @Override
            public Identifier getRecipeId() {
                return new Identifier("fantasyforest", recipeName); // 请将 "fantasyforest" 替换为你的模组ID
            }

            @Override
            public RecipeSerializer<?> getSerializer() {
                // 返回你的自定义配方序列化器实例
                // 例如：return ArborealCraftingTableRecipe.Serializer.INSTANCE;
                // 请根据你的实际类名和路径修改
                return ArborealCraftingTableRecipe.Serializer.INSTANCE;
            }

            @Override
            public @Nullable JsonObject toAdvancementJson() {
                return null;
            }

            @Override
            public @Nullable Identifier getAdvancementId() {
                return null;
            }

            @Override
            public JsonObject toJson() {
                // 在这个方法中构建并返回配方的 JsonObject
                JsonObject json = new JsonObject();
                // 调用你已有的构建JSON的逻辑，或者直接将构建逻辑写在这里
                // 例如：buildRecipeJson(json);
                // 这里需要你将之前构建 JsonObject 的逻辑放置或调用到这里
                // 1. 设置配方类型 "type"
                json.addProperty("type", "fantasy-forest:arboreal_crafting_table");
                // 2. 添加 "sapling"
                JsonObject saplingJson = new JsonObject();
                if (sapling instanceof ItemConvertible item) {
                    saplingJson.addProperty("item", Registries.ITEM.getId(item.asItem()).toString());
                }
                if (sapling instanceof TagKey<?> tagKey) {
                    saplingJson.addProperty("tag", tagKey.id().toString());
                }
                json.add("sapling", saplingJson);
                // 3. 添加 "pattern"
                JsonArray patternJson = new JsonArray();
                for (String line : pattern) {
                    patternJson.add(line);
                }
                json.add("pattern", patternJson);
                // 4. 添加 "key"
                JsonObject keyJson = new JsonObject();
                for (int i = 0; i < keys.length; i++) {
                    JsonObject ingredientJson = new JsonObject();
                    if (ingredients[i] instanceof ItemConvertible item) {
                        ingredientJson.addProperty("item", Registries.ITEM.getId(item.asItem()).toString());
                    }
                    if (ingredients[i] instanceof TagKey<?> tagKey) {
                        ingredientJson.addProperty("tag", tagKey.id().toString());
                    }
                    keyJson.add(String.valueOf(keys[i]), ingredientJson);
                }
                json.add("key", keyJson);
                // 5. 添加 "result"
                JsonObject resultJson = new JsonObject();
                resultJson.addProperty("item", Registries.ITEM.getId(output.asItem()).toString());
                if (outputCount > 1) {
                    resultJson.addProperty("count", outputCount);
                }
                json.add("result", resultJson);

                return json; // 返回构建好的 JsonObject
            }

            // 通常还需要实现这个方法，可以直接调用 toJson()
            @Override
            public void serialize(JsonObject json) {
                // 如果需要，可以在这里处理序列化，或者留空，因为 toJson() 已经完成了工作
            }
        });
    }
}

