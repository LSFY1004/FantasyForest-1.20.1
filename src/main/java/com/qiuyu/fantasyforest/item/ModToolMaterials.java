package com.qiuyu.fantasyforest.item;

import com.qiuyu.fantasyforest.block.ModBlocks;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {
    REVELATION(0, 59, 2.0F, 0.0F, 15, () -> Ingredient.ofItems(ModBlocks.REVELATION_PLANKS),true),
    PERMEAROMA(0, 59, 2.0F, 0.0F, 15, () -> Ingredient.ofItems(ModBlocks.PERMEAROMA_PLANKS),true),
    END_WOODEN(3, 1561, 9.0F, 4.0F, 22, () -> Ingredient.ofItems(ModBlocks.END_PLANKS),true),
    MUTATED_END_WOODEN(3, 1561, 9.0F, 4.0F, 22, () -> Ingredient.ofItems(ModBlocks.MUTATED_END_PLANKS),true),
    VOID_WOODEN(4, 2031, 12.0F, 4.0F, 30, () -> Ingredient.ofItems(ModBlocks.VOID_WOOD),true);

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;
    private final boolean isSpecial;

    ModToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient,boolean isSpecial) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
        this.isSpecial = isSpecial;
    }

    public int getDurability() {
        return this.itemDurability;
    }

    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public int getMiningLevel() {
        return this.miningLevel;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public boolean isSpecial() {return isSpecial;}
}
