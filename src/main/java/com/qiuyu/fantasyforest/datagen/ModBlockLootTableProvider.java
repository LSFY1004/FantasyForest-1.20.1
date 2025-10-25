package com.qiuyu.fantasyforest.datagen;

import com.qiuyu.fantasyforest.block.ModBlocks;
import com.qiuyu.fantasyforest.item.MODItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.item.ItemPredicate;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
    public ModBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.ARBOREAL_CRAFTING_TABLE);

        addDrop(ModBlocks.PERMEAROMA_LOG);
        addDrop(ModBlocks.PERMEAROMA_WOOD);
        addDrop(ModBlocks.STRIPPED_PERMEAROMA_LOG);
        addDrop(ModBlocks.STRIPPED_PERMEAROMA_WOOD);
        addDrop(ModBlocks.PERMEAROMA_PLANKS);
        addDrop(ModBlocks.PERMEAROMA_LEAVES,specialLeavesDrops(ModBlocks.PERMEAROMA_LEAVES,ModBlocks.PERMEAROMA_SAPLING,MODItems.PERMEAROMA_LEAF_PIECES,0.01F, 0.02F, 0.04F, 0.08F));
        addDrop(ModBlocks.PERMEAROMA_SAPLING);
        addDrop(ModBlocks.PERMEAROMA_BUTTON);
        addDrop(ModBlocks.PERMEAROMA_STAIRS);
        addDrop(ModBlocks.PERMEAROMA_FENCE);
        addDrop(ModBlocks.PERMEAROMA_TRAPDOOR);
        addDrop(ModBlocks.PERMEAROMA_FENCE_GATE);
        addDrop(ModBlocks.PERMEAROMA_PRESSURE_PLATE);
        addDrop(ModBlocks.PERMEAROMA_SLAB,slabDrops(ModBlocks.PERMEAROMA_SLAB));
        addDrop(ModBlocks.PERMEAROMA_DOOR,doorDrops(ModBlocks.PERMEAROMA_DOOR));
        addDrop(ModBlocks.PERMEAROMA_SIGN);
        addDrop(ModBlocks.PERMEAROMA_HANGING_SIGN);

        addDrop(ModBlocks.END_LICHEN,drops(ModBlocks.END_LICHEN, Blocks.END_STONE));
        addDrop(ModBlocks.DEAD_END_LICHEN);

        addDrop(ModBlocks.END_RESIN_BLOCK);
        addDrop(ModBlocks.END_LOG,endLogDrops(ModBlocks.END_LOG));
        addDrop(ModBlocks.END_WOOD,endLogDrops(ModBlocks.END_WOOD));
        addDrop(ModBlocks.STRIPPED_END_LOG,endLogDrops(ModBlocks.STRIPPED_END_LOG));
        addDrop(ModBlocks.STRIPPED_END_WOOD,endLogDrops(ModBlocks.STRIPPED_END_WOOD));
        addDrop(ModBlocks.END_PLANKS);
        addDrop(ModBlocks.END_LEAVES,leavesDrops(ModBlocks.END_LEAVES,ModBlocks.END_TREE_SAPLING, 0.01F, 0.02F, 0.04F, 0.08F));
        addDrop(ModBlocks.END_TREE_SAPLING);
        addDrop(ModBlocks.END_WOODEN_BUTTON);
        addDrop(ModBlocks.END_WOODEN_STAIRS);
        addDrop(ModBlocks.END_WOODEN_FENCE);
        addDrop(ModBlocks.END_WOODEN_TRAPDOOR);
        addDrop(ModBlocks.END_WOODEN_FENCE_GATE);
        addDrop(ModBlocks.END_WOODEN_PRESSURE_PLATE);
        addDrop(ModBlocks.END_WOODEN_SLAB,slabDrops(ModBlocks.END_WOODEN_SLAB));
        addDrop(ModBlocks.END_WOODEN_DOOR,doorDrops(ModBlocks.END_WOODEN_DOOR));
        addDrop(ModBlocks.END_WOODEN_SIGN);
        addDrop(ModBlocks.END_WOODEN_HANGING_SIGN);

        addDrop(ModBlocks.MUTATED_END_RESIN_BLOCK);
        addDrop(ModBlocks.MUTATED_END_LOG,endLogDrops(ModBlocks.MUTATED_END_LOG));
        addDrop(ModBlocks.MUTATED_END_WOOD,endLogDrops(ModBlocks.MUTATED_END_WOOD));
        addDrop(ModBlocks.STRIPPED_MUTATED_END_LOG,endLogDrops(ModBlocks.STRIPPED_MUTATED_END_LOG));
        addDrop(ModBlocks.STRIPPED_MUTATED_END_WOOD,endLogDrops(ModBlocks.STRIPPED_MUTATED_END_WOOD));
        addDrop(ModBlocks.MUTATED_END_PLANKS);
        addDrop(ModBlocks.MUTATED_END_WOODEN_BUTTON);
        addDrop(ModBlocks.MUTATED_END_WOODEN_STAIRS);
        addDrop(ModBlocks.MUTATED_END_WOODEN_FENCE);
        addDrop(ModBlocks.MUTATED_END_WOODEN_TRAPDOOR);
        addDrop(ModBlocks.MUTATED_END_WOODEN_FENCE_GATE);
        addDrop(ModBlocks.MUTATED_END_WOODEN_PRESSURE_PLATE);
        addDrop(ModBlocks.MUTATED_END_WOODEN_SLAB,slabDrops(ModBlocks.MUTATED_END_WOODEN_SLAB));
        addDrop(ModBlocks.MUTATED_END_WOODEN_DOOR,doorDrops(ModBlocks.MUTATED_END_WOODEN_DOOR));
        addDrop(ModBlocks.MUTATED_END_WOODEN_SIGN);
        addDrop(ModBlocks.MUTATED_END_WOODEN_HANGING_SIGN);

        addDrop(ModBlocks.VOID_RESIN_BLOCK);
        addDrop(ModBlocks.VOID_LOG);
        addDrop(ModBlocks.VOID_WOOD);
        addDrop(ModBlocks.STRIPPED_VOID_LOG);
        addDrop(ModBlocks.STRIPPED_VOID_WOOD);
        addDrop(ModBlocks.VOID_PLANKS);
        addDrop(ModBlocks.VOID_LEAVES,leavesDrops(ModBlocks.VOID_LEAVES,ModBlocks.VOID_TREE_SAPLING, 0.005F, 0.01F, 0.02F, 0.04F));
        addDrop(ModBlocks.VOID_TREE_SAPLING);
        addDrop(ModBlocks.VOID_WOODEN_SIGN);
        addDrop(ModBlocks.VOID_WOODEN_HANGING_SIGN);
        addDrop(ModBlocks.VOID_WOODEN_BUTTON);
        addDrop(ModBlocks.VOID_WOODEN_STAIRS);
        addDrop(ModBlocks.VOID_WOODEN_FENCE);
        addDrop(ModBlocks.VOID_WOODEN_TRAPDOOR);
        addDrop(ModBlocks.VOID_WOODEN_FENCE_GATE);
        addDrop(ModBlocks.VOID_WOODEN_PRESSURE_PLATE);
        addDrop(ModBlocks.VOID_WOODEN_SLAB,slabDrops(ModBlocks.VOID_WOODEN_SLAB));
        addDrop(ModBlocks.VOID_WOODEN_DOOR,doorDrops(ModBlocks.VOID_WOODEN_DOOR));
        addDrop(ModBlocks.VOID_ROOTS);
        addDrop(ModBlocks.VOID_VINE_BODY,dropsWithShears(ModBlocks.VOID_VINE_BODY));
        addDrop(ModBlocks.VOID_VINE_HEAD,dropsWithShears(ModBlocks.VOID_VINE_HEAD));
        addDrop(ModBlocks.FRUITING_VOID_VINE_HEAD,dropsWithShears(ModBlocks.FRUITING_VOID_VINE_HEAD));
        addDrop(ModBlocks.PHOSPHOR,dropsWithSilkTouch(ModBlocks.PHOSPHOR));
    }

    private LootTable.Builder endLogDrops(Block endLog) {
        return LootTable.builder()
                .pool(new LootPool.Builder()
                        .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().items(Items.DIAMOND_AXE, Items.NETHERITE_AXE)))
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(ItemEntry.builder(endLog))
                );
    }

    private LootTable.Builder specialLeavesDrops(Block leaves, Block drop, Item extra, float... chance) {
        return dropsWithSilkTouchOrShears(leaves, ((LeafEntry.Builder<?>)this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(drop))).conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, chance))).pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS).with(((LeafEntry.Builder<?>)this.applyExplosionDecay(leaves, ItemEntry.builder(extra).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))).conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, LEAVES_STICK_DROP_CHANCE))));
    }
}
