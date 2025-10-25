package com.qiuyu.fantasyforest.block;

import com.qiuyu.fantasyforest.FantasyForest;
import com.qiuyu.fantasyforest.block.custom.*;
import com.qiuyu.fantasyforest.world.tree.*;
import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block ARBOREAL_CRAFTING_TABLE = register("arboreal_crafting_table",new ArborealCraftingTable(FabricBlockSettings.copyOf(Blocks.CRAFTING_TABLE)));

    public static final Block PERMEAROMA_LOG = register("permearoma_log", new PillarBlock(AbstractBlock.Settings.create().mapColor(MapColor.LICHEN_GREEN).strength(2.0F).instrument(Instrument.BASS).sounds(BlockSoundGroup.WOOD)));
    public static final Block PERMEAROMA_WOOD = register("permearoma_wood",new PillarBlock(AbstractBlock.Settings.copy(PERMEAROMA_LOG)));
    public static final Block STRIPPED_PERMEAROMA_LOG = register("stripped_permearoma_log", new PillarBlock(AbstractBlock.Settings.copy(PERMEAROMA_LOG)));
    public static final Block STRIPPED_PERMEAROMA_WOOD = register("stripped_permearoma_wood",new PillarBlock(AbstractBlock.Settings.copy(PERMEAROMA_LOG)));
    public static final Block PERMEAROMA_PLANKS = register("permearoma_planks", new Block(AbstractBlock.Settings.create().mapColor(MapColor.PALE_GREEN).strength(2.0F).instrument(Instrument.BASS).sounds(BlockSoundGroup.WOOD)));
    public static final Block PERMEAROMA_LEAVES = register("permearoma_leaves", new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    public static final Block PERMEAROMA_STAIRS = register("permearoma_stairs",
            new StairsBlock(PERMEAROMA_PLANKS.getDefaultState(),AbstractBlock.Settings.copy(PERMEAROMA_PLANKS)));
    public static final Block PERMEAROMA_SLAB = register("permearoma_slab",
            new SlabBlock(AbstractBlock.Settings.copy(PERMEAROMA_PLANKS)));
    public static final Block PERMEAROMA_BUTTON = register("permearoma_button",
            new ButtonBlock(AbstractBlock.Settings.copy(PERMEAROMA_PLANKS),BlockSetType.OAK,40,true));
    public static final Block PERMEAROMA_PRESSURE_PLATE = register("permearoma_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,AbstractBlock.Settings.copy(PERMEAROMA_PLANKS),BlockSetType.OAK));
    public static final Block PERMEAROMA_FENCE = register("permearoma_fence",
            new FenceBlock(AbstractBlock.Settings.copy(PERMEAROMA_PLANKS)));
    public static final Block PERMEAROMA_FENCE_GATE = register("permearoma_fence_gate",
            new FenceGateBlock(AbstractBlock.Settings.copy(PERMEAROMA_PLANKS),WoodType.OAK));
    public static final Block PERMEAROMA_DOOR = register("permearoma_door",
            new DoorBlock(AbstractBlock.Settings.copy(PERMEAROMA_PLANKS).nonOpaque(),BlockSetType.OAK));
    public static final Block PERMEAROMA_TRAPDOOR = register("permearoma_trapdoor",
            new TrapdoorBlock(AbstractBlock.Settings.copy(PERMEAROMA_PLANKS).nonOpaque(),BlockSetType.OAK));
    public static final Identifier PERMEAROMA_SIGN_TEXTURE = new Identifier(FantasyForest.MOD_ID,"entity/signs/permearoma");
    public static final Identifier PERMEAROMA_HANGING_SIGN_TEXTURE = new Identifier(FantasyForest.MOD_ID,"entity/signs/hanging/permearoma");
    public static final Identifier PERMEAROMA_HANGING_SIGN_GUI = new Identifier(FantasyForest.MOD_ID,"textures/gui/hanging-signs/permearoma");
    public static final Block PERMEAROMA_SIGN = Registry.register(Registries.BLOCK,new Identifier(FantasyForest.MOD_ID,"permearoma_sign"),
            new TerraformSignBlock(PERMEAROMA_SIGN_TEXTURE,AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).solid().instrument(Instrument.BASS).noCollision().strength(2.0F,10.0F).burnable()));
    public static final Block PERMEAROMA_WALL_SIGN = Registry.register(Registries.BLOCK,new Identifier(FantasyForest.MOD_ID,"permearoma_wall_sign"),
            new TerraformWallSignBlock(PERMEAROMA_SIGN_TEXTURE,AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).solid().instrument(Instrument.BASS).noCollision().strength(2.0F,10.0F).burnable()));
    public static final Block PERMEAROMA_HANGING_SIGN = Registry.register(Registries.BLOCK,new Identifier(FantasyForest.MOD_ID,"permearoma_hanging_sign"),
            new TerraformHangingSignBlock(PERMEAROMA_HANGING_SIGN_TEXTURE, PERMEAROMA_HANGING_SIGN_GUI,AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).solid().instrument(Instrument.BASS).noCollision().strength(2.0F,10.0F).burnable()));
    public static final Block PERMEAROMA_WALL_HANGING_SIGN = Registry.register(Registries.BLOCK,new Identifier(FantasyForest.MOD_ID,"permearoma_wall_hanging_sign"),
            new TerraformWallHangingSignBlock(PERMEAROMA_HANGING_SIGN_TEXTURE, PERMEAROMA_HANGING_SIGN_GUI,AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).solid().instrument(Instrument.BASS).noCollision().strength(2.0F,10.0F).burnable()));
    public static final Block PERMEAROMA_SAPLING = register("permearoma_sapling",
            new SaplingBlock(new PermearomaSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));

    public static final Block END_LICHEN = register("end_lichen",new EndLichenBlock(AbstractBlock.Settings.copy(Blocks.END_STONE)));
    public static final Block DEAD_END_LICHEN = register("dead_end_lichen",new DeadEndLichenBlock(AbstractBlock.Settings.copy(Blocks.END_STONE)));

    public static final Block END_RESIN_BLOCK = register("end_resin_block", new Block(AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_PURPLE).strength(0.5F).sounds(BlockSoundGroup.HONEY)));
    public static final Block END_LOG = register("end_log", new PillarBlock(AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_PURPLE).strength(5.0F,10.0F).instrument(Instrument.BASS).requiresTool().sounds(BlockSoundGroup.WOOD)));
    public static final Block END_WOOD = register("end_wood",new PillarBlock(AbstractBlock.Settings.copy(END_LOG)));
    public static final Block STRIPPED_END_LOG = register("stripped_end_log", new PillarBlock(AbstractBlock.Settings.copy(END_LOG)));
    public static final Block STRIPPED_END_WOOD = register("stripped_end_wood",new PillarBlock(AbstractBlock.Settings.copy(END_LOG)));
    public static final Block END_PLANKS = register("end_planks", new Block(AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_PURPLE).strength(2.0F,10.0F).instrument(Instrument.BASS).sounds(BlockSoundGroup.WOOD)));
    public static final Block END_LEAVES = register("end_leaves", new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    public static final Block END_WOODEN_STAIRS = register("end_wooden_stairs",
            new StairsBlock(END_PLANKS.getDefaultState(),AbstractBlock.Settings.copy(END_PLANKS)));
    public static final Block END_WOODEN_SLAB = register("end_wooden_slab",
            new SlabBlock(AbstractBlock.Settings.copy(END_PLANKS)));
    public static final Block END_WOODEN_BUTTON = register("end_wooden_button",
            new ButtonBlock(AbstractBlock.Settings.copy(END_PLANKS),BlockSetType.OAK,40,true));
    public static final Block END_WOODEN_PRESSURE_PLATE = register("end_wooden_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,AbstractBlock.Settings.copy(END_PLANKS),BlockSetType.OAK));
    public static final Block END_WOODEN_FENCE = register("end_wooden_fence",
            new FenceBlock(AbstractBlock.Settings.copy(END_PLANKS)));
    public static final Block END_WOODEN_FENCE_GATE = register("end_wooden_fence_gate",
            new FenceGateBlock(AbstractBlock.Settings.copy(END_PLANKS),WoodType.OAK));
    public static final Block END_WOODEN_DOOR = register("end_wooden_door",
            new DoorBlock(AbstractBlock.Settings.copy(END_PLANKS).nonOpaque(),BlockSetType.OAK));
    public static final Block END_WOODEN_TRAPDOOR = register("end_wooden_trapdoor",
            new TrapdoorBlock(AbstractBlock.Settings.copy(END_PLANKS).nonOpaque(),BlockSetType.OAK));
    public static final Identifier END_WODEN_SIGN_TEXTURE = new Identifier(FantasyForest.MOD_ID,"entity/signs/end_wooden");
    public static final Identifier END_WODEN_HANGING_SIGN_TEXTURE = new Identifier(FantasyForest.MOD_ID,"entity/signs/hanging/end_wooden");
    public static final Identifier END_WODEN_HANGING_SIGN_GUI = new Identifier(FantasyForest.MOD_ID,"textures/gui/hanging-signs/end_wooden");
    public static final Block END_WOODEN_SIGN = Registry.register(Registries.BLOCK,new Identifier(FantasyForest.MOD_ID,"end_wooden_sign"),
            new TerraformSignBlock(END_WODEN_SIGN_TEXTURE,AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).solid().instrument(Instrument.BASS).noCollision().strength(2.0F,10.0F).burnable()));
    public static final Block END_WOODEN_WALL_SIGN = Registry.register(Registries.BLOCK,new Identifier(FantasyForest.MOD_ID,"end_wooden_wall_sign"),
            new TerraformWallSignBlock(END_WODEN_SIGN_TEXTURE,AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).solid().instrument(Instrument.BASS).noCollision().strength(2.0F,10.0F).burnable()));
    public static final Block END_WOODEN_HANGING_SIGN = Registry.register(Registries.BLOCK,new Identifier(FantasyForest.MOD_ID,"end_wooden_hanging_sign"),
            new TerraformHangingSignBlock(END_WODEN_HANGING_SIGN_TEXTURE,END_WODEN_HANGING_SIGN_GUI,AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).solid().instrument(Instrument.BASS).noCollision().strength(2.0F,10.0F).burnable()));
    public static final Block END_WOODEN_WALL_HANGING_SIGN = Registry.register(Registries.BLOCK,new Identifier(FantasyForest.MOD_ID,"end_wooden_wall_hanging_sign"),
            new TerraformWallHangingSignBlock(END_WODEN_HANGING_SIGN_TEXTURE,END_WODEN_HANGING_SIGN_GUI,AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).solid().instrument(Instrument.BASS).noCollision().strength(2.0F,10.0F).burnable()));

    public static final Block END_TREE_SAPLING = register("end_tree_sapling",
            new EndTreeSaplingBlock(new EndTreeSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));

    public static final Block MUTATED_END_LOG = register("mutated_end_log", new PillarBlock(AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_GREEN).strength(5.0F,10.0F).instrument(Instrument.BASS).requiresTool().sounds(BlockSoundGroup.WOOD)));
    public static final Block MUTATED_END_WOOD = register("mutated_end_wood",new PillarBlock(AbstractBlock.Settings.copy(MUTATED_END_LOG)));
    public static final Block MUTATED_END_RESIN_BLOCK = register("mutated_end_resin_block", new Block(AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_GREEN).strength(0.5F).sounds(BlockSoundGroup.HONEY)));
    public static final Block STRIPPED_MUTATED_END_LOG = register("stripped_mutated_end_log", new PillarBlock(AbstractBlock.Settings.copy(MUTATED_END_LOG)));
    public static final Block STRIPPED_MUTATED_END_WOOD = register("stripped_mutated_end_wood",new PillarBlock(AbstractBlock.Settings.copy(MUTATED_END_LOG)));
    public static final Block MUTATED_END_PLANKS = register("mutated_end_planks", new Block(AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_GREEN).strength(2.0F,10.0F).instrument(Instrument.BASS).sounds(BlockSoundGroup.WOOD)));
    public static final Block MUTATED_END_WOODEN_STAIRS = register("mutated_end_wooden_stairs",
            new StairsBlock(MUTATED_END_PLANKS.getDefaultState(),AbstractBlock.Settings.copy(MUTATED_END_PLANKS)));
    public static final Block MUTATED_END_WOODEN_SLAB = register("mutated_end_wooden_slab",
            new SlabBlock(AbstractBlock.Settings.copy(MUTATED_END_PLANKS)));
    public static final Block MUTATED_END_WOODEN_BUTTON = register("mutated_end_wooden_button",
            new ButtonBlock(AbstractBlock.Settings.copy(MUTATED_END_PLANKS),BlockSetType.OAK,40,true));
    public static final Block MUTATED_END_WOODEN_PRESSURE_PLATE = register("mutated_end_wooden_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,AbstractBlock.Settings.copy(MUTATED_END_PLANKS),BlockSetType.OAK));
    public static final Block MUTATED_END_WOODEN_FENCE = register("mutated_end_wooden_fence",
            new FenceBlock(AbstractBlock.Settings.copy(MUTATED_END_PLANKS)));
    public static final Block MUTATED_END_WOODEN_FENCE_GATE = register("mutated_end_wooden_fence_gate",
            new FenceGateBlock(AbstractBlock.Settings.copy(MUTATED_END_PLANKS),WoodType.OAK));
    public static final Block MUTATED_END_WOODEN_DOOR = register("mutated_end_wooden_door",
            new DoorBlock(AbstractBlock.Settings.copy(MUTATED_END_PLANKS).nonOpaque(),BlockSetType.OAK));
    public static final Block MUTATED_END_WOODEN_TRAPDOOR = register("mutated_end_wooden_trapdoor",
            new TrapdoorBlock(AbstractBlock.Settings.copy(MUTATED_END_PLANKS).nonOpaque(),BlockSetType.OAK));
    public static final Identifier MUTATED_END_WODEN_SIGN_TEXTURE = new Identifier(FantasyForest.MOD_ID,"entity/signs/mutated_end_wooden");
    public static final Identifier MUTATED_END_WODEN_HANGING_SIGN_TEXTURE = new Identifier(FantasyForest.MOD_ID,"entity/signs/hanging/mutated_end_wooden");
    public static final Identifier MUTATED_END_WODEN_HANGING_SIGN_GUI = new Identifier(FantasyForest.MOD_ID,"textures/gui/hanging-signs/mutated_end_wooden");
    public static final Block MUTATED_END_WOODEN_SIGN = Registry.register(Registries.BLOCK,new Identifier(FantasyForest.MOD_ID,"mutated_end_wooden_sign"),
            new TerraformSignBlock(MUTATED_END_WODEN_SIGN_TEXTURE,AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).solid().instrument(Instrument.BASS).noCollision().strength(2.0F,10.0F).burnable()));
    public static final Block MUTATED_END_WOODEN_WALL_SIGN = Registry.register(Registries.BLOCK,new Identifier(FantasyForest.MOD_ID,"mutated_end_wooden_wall_sign"),
            new TerraformWallSignBlock(MUTATED_END_WODEN_SIGN_TEXTURE,AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).solid().instrument(Instrument.BASS).noCollision().strength(2.0F,10.0F).burnable()));
    public static final Block MUTATED_END_WOODEN_HANGING_SIGN = Registry.register(Registries.BLOCK,new Identifier(FantasyForest.MOD_ID,"mutated_end_wooden_hanging_sign"),
            new TerraformHangingSignBlock(MUTATED_END_WODEN_HANGING_SIGN_TEXTURE,MUTATED_END_WODEN_HANGING_SIGN_GUI,AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).solid().instrument(Instrument.BASS).noCollision().strength(2.0F,10.0F).burnable()));
    public static final Block MUTATED_END_WOODEN_WALL_HANGING_SIGN = Registry.register(Registries.BLOCK,new Identifier(FantasyForest.MOD_ID,"mutated_end_wooden_wall_hanging_sign"),
            new TerraformWallHangingSignBlock(MUTATED_END_WODEN_HANGING_SIGN_TEXTURE,MUTATED_END_WODEN_HANGING_SIGN_GUI,AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).solid().instrument(Instrument.BASS).noCollision().strength(2.0F,10.0F).burnable()));

    public static final Block VOID_RESIN_BLOCK = register("void_resin_block", new Block(AbstractBlock.Settings.create().mapColor(MapColor.GRAY).strength(0.5F).sounds(BlockSoundGroup.HONEY)));
    public static final Block VOID_LOG = register("void_log", new PillarBlock(AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_PURPLE).strength(50.0F,1200.0F).instrument(Instrument.BASS).requiresTool().sounds(BlockSoundGroup.WOOD)));
    public static final Block VOID_WOOD = register("void_wood",new PillarBlock(AbstractBlock.Settings.copy(VOID_LOG)));
    public static final Block STRIPPED_VOID_LOG = register("stripped_void_log", new PillarBlock(AbstractBlock.Settings.copy(VOID_LOG)));
    public static final Block STRIPPED_VOID_WOOD = register("stripped_void_wood",new PillarBlock(AbstractBlock.Settings.copy(VOID_LOG)));
    public static final Block VOID_PLANKS = register("void_planks", new Block(AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_PURPLE).strength(5.0F,1200.0F).instrument(Instrument.BASS).sounds(BlockSoundGroup.WOOD)));
    public static final Block VOID_LEAVES = register("void_leaves", new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    public static final Block VOID_VINE_BODY = register("void_vine_body",new VoidVineBodyBlock(AbstractBlock.Settings.create().mapColor(MapColor.GRAY).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CAVE_VINES).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block VOID_VINE_HEAD = register("void_vine_head",new VoidVineHeadBlock(AbstractBlock.Settings.create().mapColor(MapColor.GRAY).ticksRandomly().noCollision().breakInstantly().sounds(BlockSoundGroup.CAVE_VINES).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block FRUITING_VOID_VINE_HEAD = register("fruiting_void_vine_head",new FruitingVoidVineHeadBlock(AbstractBlock.Settings.copy(VOID_VINE_HEAD)));
    public static final Block PHOSPHOR = register("phosphor",new PhosphorBlock(AbstractBlock.Settings.create().mapColor(MapColor.GRAY).strength(0.5F).sounds(BlockSoundGroup.GLASS)));
    public static final Block VOID_ROOTS = register("void_roots",new MangroveRootsBlock(
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.SPRUCE_BROWN)
                    .instrument(Instrument.BASS)
                    .strength(5.0F,1200.0F)
                    .ticksRandomly()
                    .sounds(BlockSoundGroup.MANGROVE_ROOTS)
                    .suffocates(Blocks::never)
                    .blockVision(Blocks::never)
                    .nonOpaque()
                    .burnable()
    ));
    public static final Block VOID_WOODEN_STAIRS = register("void_wooden_stairs",
            new StairsBlock(VOID_PLANKS.getDefaultState(),AbstractBlock.Settings.copy(VOID_PLANKS)));
    public static final Block VOID_WOODEN_SLAB = register("void_wooden_slab",
            new SlabBlock(AbstractBlock.Settings.copy(VOID_PLANKS)));
    public static final Block VOID_WOODEN_BUTTON = register("void_wooden_button",
            new ButtonBlock(AbstractBlock.Settings.copy(VOID_PLANKS),BlockSetType.OAK,40,true));
    public static final Block VOID_WOODEN_PRESSURE_PLATE = register("void_wooden_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,AbstractBlock.Settings.copy(VOID_PLANKS),BlockSetType.OAK));
    public static final Block VOID_WOODEN_FENCE = register("void_wooden_fence",
            new FenceBlock(AbstractBlock.Settings.copy(VOID_PLANKS)));
    public static final Block VOID_WOODEN_FENCE_GATE = register("void_wooden_fence_gate",
            new FenceGateBlock(AbstractBlock.Settings.copy(VOID_PLANKS),WoodType.OAK));
    public static final Block VOID_WOODEN_DOOR = register("void_wooden_door",
            new DoorBlock(AbstractBlock.Settings.copy(VOID_PLANKS).nonOpaque(),BlockSetType.OAK));
    public static final Block VOID_WOODEN_TRAPDOOR = register("void_wooden_trapdoor",
            new TrapdoorBlock(AbstractBlock.Settings.copy(VOID_PLANKS).nonOpaque(),BlockSetType.OAK));
    public static final Block VOID_TREE_SAPLING = register("void_tree_sapling",
            new VoidTreeSaplingBlock(new VoidTreeSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));
    public static final Identifier VOID_WODEN_SIGN_TEXTURE = new Identifier(FantasyForest.MOD_ID,"entity/signs/void_wooden");
    public static final Identifier VOID_WODEN_HANGING_SIGN_TEXTURE = new Identifier(FantasyForest.MOD_ID,"entity/signs/hanging/void_wooden");
    public static final Identifier VOID_WODEN_HANGING_SIGN_GUI = new Identifier(FantasyForest.MOD_ID,"textures/gui/hanging-signs/void_wooden");
    public static final Block VOID_WOODEN_SIGN = Registry.register(Registries.BLOCK,new Identifier(FantasyForest.MOD_ID,"void_wooden_sign"),
            new TerraformSignBlock(VOID_WODEN_SIGN_TEXTURE,AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).solid().instrument(Instrument.BASS).noCollision().strength(5.0F,1200.0F).burnable()));
    public static final Block VOID_WOODEN_WALL_SIGN = Registry.register(Registries.BLOCK,new Identifier(FantasyForest.MOD_ID,"void_wooden_wall_sign"),
            new TerraformWallSignBlock(VOID_WODEN_SIGN_TEXTURE,AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).solid().instrument(Instrument.BASS).noCollision().strength(5.0F,1200.0F).burnable()));
    public static final Block VOID_WOODEN_HANGING_SIGN = Registry.register(Registries.BLOCK,new Identifier(FantasyForest.MOD_ID,"void_wooden_hanging_sign"),
            new TerraformHangingSignBlock(VOID_WODEN_HANGING_SIGN_TEXTURE,VOID_WODEN_HANGING_SIGN_GUI,AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).solid().instrument(Instrument.BASS).noCollision().strength(5.0F,1200.0F).burnable()));
    public static final Block VOID_WOODEN_WALL_HANGING_SIGN = Registry.register(Registries.BLOCK,new Identifier(FantasyForest.MOD_ID,"void_wooden_wall_hanging_sign"),
            new TerraformWallHangingSignBlock(VOID_WODEN_HANGING_SIGN_TEXTURE,VOID_WODEN_HANGING_SIGN_GUI,AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).solid().instrument(Instrument.BASS).noCollision().strength(5.0F,1200.0F).burnable()));

    public static final Block PERMEAROMA_STICK = registerBIDBlock("permearoma_stick",new StickBlock(AbstractBlock.Settings.copy(ModBlocks.PERMEAROMA_PLANKS).nonOpaque()));
    public static final Block END_STICK = registerBIDBlock("end_stick",new StickBlock(AbstractBlock.Settings.copy(ModBlocks.END_PLANKS).nonOpaque()));
    public static final Block MUTATED_END_STICK = registerBIDBlock("mutated_end_stick",new StickBlock(AbstractBlock.Settings.copy(ModBlocks.MUTATED_END_PLANKS).nonOpaque()));
    public static final Block VOID_STICK = registerBIDBlock("void_stick",new StickBlock(AbstractBlock.Settings.copy(ModBlocks.VOID_PLANKS).nonOpaque()));
    public static final Block OAK_STICK = registerBIDBlock("oak_stick",new StickBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block ACACIA_STICK = registerBIDBlock("acacia_stick",new StickBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_PLANKS).nonOpaque()));
    public static final Block BAMBOO_STICK = registerBIDBlock("bamboo_stick",new StickBlock(AbstractBlock.Settings.copy(Blocks.BAMBOO_PLANKS).nonOpaque()));
    public static final Block BIRCH_STICK = registerBIDBlock("birch_stick",new StickBlock(AbstractBlock.Settings.copy(Blocks.BIRCH_PLANKS).nonOpaque()));
    public static final Block DARK_OAK_STICK = registerBIDBlock("daro_oak_stick",new StickBlock(AbstractBlock.Settings.copy(Blocks.DARK_OAK_PLANKS).nonOpaque()));
    public static final Block CHERRY_STICK = registerBIDBlock("cherry_stick",new StickBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_PLANKS).nonOpaque()));
    public static final Block CRIMSON_STICK = registerBIDBlock("crimson_stick",new StickBlock(AbstractBlock.Settings.copy(Blocks.CRIMSON_PLANKS).nonOpaque()));
    public static final Block JUNGLE_STICK = registerBIDBlock("jungle_stick",new StickBlock(AbstractBlock.Settings.copy(Blocks.JUNGLE_PLANKS).nonOpaque()));
    public static final Block MANGROVE_STICK = registerBIDBlock("mangrove_stick",new StickBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_PLANKS).nonOpaque()));
    public static final Block SPRUCE_STICK = registerBIDBlock("spruce_stick",new StickBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS).nonOpaque()));
    public static final Block WARPED_STICK = registerBIDBlock("warped_stick",new StickBlock(AbstractBlock.Settings.copy(Blocks.WARPED_PLANKS).nonOpaque()));

    public static Block register(String id, Block block) {
        registerBlockItems(id, block);
        return Registry.register(Registries.BLOCK, new Identifier(FantasyForest.MOD_ID, id), block);
    }

    public static Block registerBIDBlock(String id, Block block) {
        registerBIDItems(id, block);
        return Registry.register(Registries.BLOCK, new Identifier(FantasyForest.MOD_ID, id), block);
    }

    private static void registerBIDItems(String id, Block block) {
        Registry.register(Registries.ITEM, new Identifier(FantasyForest.MOD_ID, id),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerBlockItems(String id, Block block){
        Registry.register(Registries.ITEM, new Identifier(FantasyForest.MOD_ID, id),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks(){

    }

    public static Block getStickBlock(String woodType) {
        return switch (woodType){
            case "permearoma" -> ModBlocks.PERMEAROMA_STICK;
            case "end"->ModBlocks.END_STICK;
            case "mutated_end"->ModBlocks.MUTATED_END_STICK;
            case "void"->ModBlocks.VOID_STICK;
            case "oak" -> ModBlocks.OAK_STICK;
            case "birch" -> ModBlocks.BIRCH_STICK;
            case "bamboo" -> ModBlocks.BAMBOO_STICK;
            case "dark_oak" -> ModBlocks.DARK_OAK_STICK;
            case "cherry" -> ModBlocks.CHERRY_STICK;
            case "crimson" -> ModBlocks.CRIMSON_STICK;
            case "spruce" -> ModBlocks.SPRUCE_STICK;
            case "mangrove" -> ModBlocks.MANGROVE_STICK;
            case "jungle" -> ModBlocks.JUNGLE_STICK;
            case "acacia" -> ModBlocks.ACACIA_STICK;
            case "warped" -> ModBlocks.WARPED_STICK;
            default -> throw new IllegalStateException("Unexpected value: " + woodType);
        };
    }
}
