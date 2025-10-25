package com.qiuyu.fantasyforest.recipe;

import com.qiuyu.fantasyforest.FantasyForest;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static void registerRecipes(){
        Registry.register(Registries.RECIPE_SERIALIZER,new Identifier(FantasyForest.MOD_ID,ArborealCraftingTableRecipe.Serializer.ID),
                ArborealCraftingTableRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE,new Identifier(FantasyForest.MOD_ID,ArborealCraftingTableRecipe.Type.ID),
                ArborealCraftingTableRecipe.Type.INSTANCE);
    }
}
