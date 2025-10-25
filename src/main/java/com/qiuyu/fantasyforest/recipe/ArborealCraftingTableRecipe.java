package com.qiuyu.fantasyforest.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArborealCraftingTableRecipe implements Recipe<Inventory> {

    private final Identifier id;
    private final ItemStack output;
    private final List<Ingredient> recipeItems;
    private final int patternWidth;
    private final int patternHeight;

    public ArborealCraftingTableRecipe(Identifier id, ItemStack output, List<Ingredient> recipeItems, int patternWidth, int patternHeight) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.patternWidth = patternWidth;
        this.patternHeight = patternHeight;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        // 检查树苗槽位（槽位0）
        if (!recipeItems.get(0).test(inventory.getStack(0))) {
            return false;
        }

        // 修复：使用滑动窗口匹配算法，同时支持镜像
        for (int startRow = 0; startRow <= 3 - patternHeight; startRow++) {
            for (int startCol = 0; startCol <= 3 - patternWidth; startCol++) {
                // 尝试原始方向
                if (matchesAtPosition(inventory, startRow, startCol, false) &&
                        areOtherSlotsEmpty(inventory, startRow, startCol)) {
                    return true;
                }
                // 尝试镜像方向（水平翻转）
                if (matchesAtPosition(inventory, startRow, startCol, true) &&
                        areOtherSlotsEmpty(inventory, startRow, startCol)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 修改：添加镜像支持参数
    private boolean matchesAtPosition(Inventory inventory, int startRow, int startCol, boolean mirrored) {
        for (int recipeRow = 0; recipeRow < patternHeight; recipeRow++) {
            for (int recipeCol = 0; recipeCol < patternWidth; recipeCol++) {
                // 修复：计算正确的配方槽位
                int actualRecipeCol = mirrored ? (patternWidth - 1 - recipeCol) : recipeCol;
                int recipeSlot = 1 + recipeRow * 3 + actualRecipeCol;

                int inventorySlot = 1 + (startRow + recipeRow) * 3 + (startCol + recipeCol);

                Ingredient required = recipeItems.get(recipeSlot);
                ItemStack actual = inventory.getStack(inventorySlot);

                // 如果配方要求有原料但库存不匹配，返回false
                if (!required.isEmpty() && !required.test(actual)) {
                    return false;
                }
                // 修复：如果配方不要求原料但库存有物品，返回false
                if (required.isEmpty() && !actual.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    // 检查配方区域外的格子是否为空
    private boolean areOtherSlotsEmpty(Inventory inventory, int startRow, int startCol) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                // 跳过配方区域内的格子
                if (row >= startRow && row < startRow + patternHeight &&
                        col >= startCol && col < startCol + patternWidth) {
                    continue;
                }

                if (!inventory.getStack(1 + row * 3 + col).isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<ArborealCraftingTableRecipe>{
        public static final Type INSTANCE = new Type();
        public static final String ID = "arboreal_crafting_table";
    }

    public static class Serializer implements RecipeSerializer<ArborealCraftingTableRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "arboreal_crafting_table";

        @Override
        public ArborealCraftingTableRecipe read(Identifier id, JsonObject json) {
            // 读取树苗
            Ingredient sapling = Ingredient.fromJson(JsonHelper.getElement(json, "sapling"));

            // 读取图案
            JsonArray patternArray = JsonHelper.getArray(json, "pattern");
            String[] pattern = new String[patternArray.size()];
            int patternWidth = 0;

            // 计算图案的实际宽度和高度
            for (int i = 0; i < patternArray.size(); i++) {
                pattern[i] = JsonHelper.asString(patternArray.get(i), "pattern[" + i + "]");
                patternWidth = Math.max(patternWidth, pattern[i].length());
            }
            int patternHeight = patternArray.size();

            // 验证图案尺寸
            if (patternHeight > 3) {
                throw new JsonSyntaxException("Pattern is too many rows! 3 is maximum");
            }
            for (String line : pattern) {
                if (line.length() > 3) {
                    throw new JsonSyntaxException("Pattern row is too wide! 3 is maximum");
                }
            }

            // 读取键映射
            JsonObject keyObject = JsonHelper.getObject(json, "key");
            Map<String, Ingredient> keyMap = new HashMap<>();
            for (Map.Entry<String, JsonElement> entry : keyObject.entrySet()) {
                if (entry.getKey().length() != 1) {
                    throw new JsonSyntaxException("Invalid key entry: '" + entry.getKey() + "' is an invalid symbol (must be 1 character only).");
                }
                keyMap.put(entry.getKey(), Ingredient.fromJson(entry.getValue()));
            }

            // 使用修复的方法构建原料列表
            DefaultedList<Ingredient> inputs = buildInputsFromPattern(sapling, pattern, keyMap, patternHeight);

            // 读取结果
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "result"));

            // 传入图案尺寸
            return new ArborealCraftingTableRecipe(id, output, inputs, patternWidth, patternHeight);
        }

        // 修复：正确构建原料列表
        private DefaultedList<Ingredient> buildInputsFromPattern(Ingredient sapling, String[] pattern,
                                                                 Map<String, Ingredient> keyMap,
                                                                 int patternHeight) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(10, Ingredient.EMPTY);

            // 槽位0：树苗
            inputs.set(0, sapling);

            // 修复：正确构建原料列表，确保位置映射准确
            for (int row = 0; row < patternHeight; row++) {
                for (int col = 0; col < pattern[row].length(); col++) {
                    char symbol = pattern[row].charAt(col);
                    if (symbol != ' ' && keyMap.containsKey(String.valueOf(symbol))) {
                        // 修复：使用正确的槽位映射
                        int slotIndex = 1 + row * 3 + col;
                        inputs.set(slotIndex, keyMap.get(String.valueOf(symbol)));
                    }
                }
            }

            return inputs;
        }

        @Override
        public ArborealCraftingTableRecipe read(Identifier id, PacketByteBuf buf) {
            // 从数据包读取图案尺寸
            int patternWidth = buf.readVarInt();
            int patternHeight = buf.readVarInt();

            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(10, Ingredient.EMPTY);
            inputs.replaceAll(ignored -> Ingredient.fromPacket(buf));
            ItemStack output = buf.readItemStack();

            // 传入图案尺寸
            return new ArborealCraftingTableRecipe(id, output, inputs, patternWidth, patternHeight);
        }

        @Override
        public void write(PacketByteBuf buf, ArborealCraftingTableRecipe recipe) {
            // 写入图案尺寸
            buf.writeVarInt(recipe.patternWidth);
            buf.writeVarInt(recipe.patternHeight);

            for (Ingredient ingredient : recipe.recipeItems) {
                ingredient.write(buf);
            }
            buf.writeItemStack(recipe.output);
        }
    }
}