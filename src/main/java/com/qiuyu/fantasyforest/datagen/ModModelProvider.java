package com.qiuyu.fantasyforest.datagen;

import com.qiuyu.fantasyforest.block.ModBlocks;
import com.qiuyu.fantasyforest.block.ModBlocksFamilies;
import com.qiuyu.fantasyforest.block.custom.StickBlock;
import com.qiuyu.fantasyforest.item.MODItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    private static final String[] WOOD_TYPES =
            {"oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "mangrove", "cherry", "bamboo", "crimson", "warped"};
    private static final String[] MOD_WOOD_TYPES =
            {"permearoma","end","mutated_end","void"};

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // 生成木棍方块的模型和方块状态
        for (String woodType : WOOD_TYPES) {
            generateStickBlockModels(blockStateModelGenerator, woodType, "minecraft");
            generateStickBlockState(blockStateModelGenerator, woodType);
        }
        for (String woodType : MOD_WOOD_TYPES) {
            generateStickBlockModels(blockStateModelGenerator, woodType, "fantasy-forest");
            generateStickBlockState(blockStateModelGenerator, woodType);
        }

        // 其他方块的注册保持不变
        ModBlocksFamilies.getFamilies()
                .filter(BlockFamily::shouldGenerateModels)
                .forEach(family -> blockStateModelGenerator.registerCubeAllModelTexturePool(family.getBaseBlock()).family(family));

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PERMEAROMA_LEAVES);
        blockStateModelGenerator.registerTintableCross(ModBlocks.PERMEAROMA_SAPLING,BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerLog(ModBlocks.PERMEAROMA_LOG).log(ModBlocks.PERMEAROMA_LOG).wood(ModBlocks.PERMEAROMA_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_PERMEAROMA_LOG).log(ModBlocks.STRIPPED_PERMEAROMA_LOG).wood(ModBlocks.STRIPPED_PERMEAROMA_WOOD);
        blockStateModelGenerator.registerHangingSign(ModBlocks.STRIPPED_PERMEAROMA_LOG,ModBlocks.PERMEAROMA_HANGING_SIGN,ModBlocks.PERMEAROMA_WALL_HANGING_SIGN);

        ModelGeneratorHelper.registerGrassLikeBlock(ModBlocks.END_LICHEN,blockStateModelGenerator);
        ModelGeneratorHelper.registerGrassLikeBlock(ModBlocks.DEAD_END_LICHEN,blockStateModelGenerator);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.END_RESIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.END_LEAVES);
        blockStateModelGenerator.registerTintableCross(ModBlocks.END_TREE_SAPLING,BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerLog(ModBlocks.END_LOG).log(ModBlocks.END_LOG).wood(ModBlocks.END_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_END_LOG).log(ModBlocks.STRIPPED_END_LOG).wood(ModBlocks.STRIPPED_END_WOOD);
        blockStateModelGenerator.registerHangingSign(ModBlocks.STRIPPED_END_LOG,ModBlocks.END_WOODEN_HANGING_SIGN,ModBlocks.END_WOODEN_WALL_HANGING_SIGN);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VOID_RESIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VOID_LEAVES);
        blockStateModelGenerator.registerTintableCross(ModBlocks.VOID_TREE_SAPLING,BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerTintableCross(ModBlocks.VOID_VINE_HEAD,BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerTintableCross(ModBlocks.VOID_VINE_BODY,BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerTintableCross(ModBlocks.FRUITING_VOID_VINE_HEAD,BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VOID_ROOTS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PHOSPHOR);
        blockStateModelGenerator.registerLog(ModBlocks.VOID_LOG).log(ModBlocks.VOID_LOG).wood(ModBlocks.VOID_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_VOID_LOG).log(ModBlocks.STRIPPED_VOID_LOG).wood(ModBlocks.STRIPPED_VOID_WOOD);
        blockStateModelGenerator.registerHangingSign(ModBlocks.STRIPPED_VOID_LOG,ModBlocks.VOID_WOODEN_HANGING_SIGN,ModBlocks.VOID_WOODEN_WALL_HANGING_SIGN);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MUTATED_END_RESIN_BLOCK);
        blockStateModelGenerator.registerLog(ModBlocks.MUTATED_END_LOG).log(ModBlocks.MUTATED_END_LOG).wood(ModBlocks.MUTATED_END_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_MUTATED_END_LOG).log(ModBlocks.STRIPPED_MUTATED_END_LOG).wood(ModBlocks.STRIPPED_MUTATED_END_WOOD);
        blockStateModelGenerator.registerHangingSign(ModBlocks.STRIPPED_MUTATED_END_LOG,ModBlocks.MUTATED_END_WOODEN_HANGING_SIGN,ModBlocks.MUTATED_END_WOODEN_WALL_HANGING_SIGN);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ARBOREAL_CRAFTING_TABLE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // 注册木棍方块物品模型
        for (String woodType : WOOD_TYPES) {
            itemModelGenerator.register(ModBlocks.getStickBlock(woodType).asItem(), Models.GENERATED);
        }
        for (String woodType : MOD_WOOD_TYPES) {
            itemModelGenerator.register(ModBlocks.getStickBlock(woodType).asItem(), Models.GENERATED);
        }

        // 其他物品注册保持不变
        itemModelGenerator.register(MODItems.SEED_OF_REVELATION,Models.GENERATED);

        itemModelGenerator.register(MODItems.PERMEAROMA_LEAF_PIECES,Models.GENERATED);
        itemModelGenerator.register(MODItems.PERMEAROMA_BOAT,Models.GENERATED);
        itemModelGenerator.register(MODItems.PERMEAROMA_CHEST_BOAT,Models.GENERATED);
        itemModelGenerator.register(MODItems.PERMEAROMA_AXE,Models.HANDHELD);
        itemModelGenerator.register(MODItems.PERMEAROMA_PICKAXE,Models.HANDHELD);
        itemModelGenerator.register(MODItems.PERMEAROMA_HOE,Models.HANDHELD);
        itemModelGenerator.register(MODItems.PERMEAROMA_SHOVEL,Models.HANDHELD);
        itemModelGenerator.register(MODItems.PERMEAROMA_SWORD,Models.HANDHELD);

        itemModelGenerator.register(MODItems.END_RESIN, Models.GENERATED);
        itemModelGenerator.register(MODItems.END_WOODEN_BOAT,Models.GENERATED);
        itemModelGenerator.register(MODItems.END_WOODEN_CHEST_BOAT,Models.GENERATED);
        itemModelGenerator.register(MODItems.END_WOODEN_AXE,Models.HANDHELD);
        itemModelGenerator.register(MODItems.END_WOODEN_PICKAXE,Models.HANDHELD);
        itemModelGenerator.register(MODItems.END_WOODEN_HOE,Models.HANDHELD);
        itemModelGenerator.register(MODItems.END_WOODEN_SHOVEL,Models.HANDHELD);
        itemModelGenerator.register(MODItems.END_WOODEN_SWORD,Models.HANDHELD);

        itemModelGenerator.register(MODItems.MUTATED_END_RESIN, Models.GENERATED);
        itemModelGenerator.register(MODItems.MUTATED_END_WOODEN_BOAT,Models.GENERATED);
        itemModelGenerator.register(MODItems.MUTATED_END_WOODEN_CHEST_BOAT,Models.GENERATED);
        itemModelGenerator.register(MODItems.MUTATED_END_WOODEN_AXE,Models.HANDHELD);
        itemModelGenerator.register(MODItems.MUTATED_END_WOODEN_PICKAXE,Models.HANDHELD);
        itemModelGenerator.register(MODItems.MUTATED_END_WOODEN_HOE,Models.HANDHELD);
        itemModelGenerator.register(MODItems.MUTATED_END_WOODEN_SHOVEL,Models.HANDHELD);
        itemModelGenerator.register(MODItems.MUTATED_END_WOODEN_SWORD,Models.HANDHELD);

        itemModelGenerator.register(MODItems.VOID_RESIN, Models.GENERATED);
        itemModelGenerator.register(MODItems.VOID_FRUIT, Models.GENERATED);
        itemModelGenerator.register(MODItems.VOID_WOODEN_BOAT,Models.GENERATED);
        itemModelGenerator.register(MODItems.VOID_WOODEN_CHEST_BOAT,Models.GENERATED);
        itemModelGenerator.register(MODItems.VOID_WOODEN_AXE,Models.HANDHELD);
        itemModelGenerator.register(MODItems.VOID_WOODEN_PICKAXE,Models.HANDHELD);
        itemModelGenerator.register(MODItems.VOID_WOODEN_HOE,Models.HANDHELD);
        itemModelGenerator.register(MODItems.VOID_WOODEN_SHOVEL,Models.HANDHELD);
        itemModelGenerator.register(MODItems.VOID_WOODEN_SWORD,Models.HANDHELD);

        ModelGeneratorHelper.registerBlockItemModels(itemModelGenerator);
        ModelGeneratorHelper.clearBlockItemsRegistry();
    }

    private void generateStickBlockModels(BlockStateModelGenerator generator, String woodType, String namespace) {
        Identifier plankTexture = new Identifier(namespace, "block/" + woodType + "_planks");
        TextureMap textureMap = new TextureMap().put(TextureKey.TEXTURE, plankTexture);

        // 使用相同的模板生成所有模型
        String[] modelTypes = {"stick_x", "stick_side"};

        for (String modelType : modelTypes) {
            Model model = new Model(
                    Optional.of(new Identifier("fantasy-forest", "block/templates/" + modelType)),
                    Optional.empty(),
                    TextureKey.TEXTURE
            );
            model.upload(
                    new Identifier("fantasy-forest", "block/" + woodType + "_" + modelType),
                    textureMap,
                    generator.modelCollector
            );
        }
    }

    private void generateStickBlockState(BlockStateModelGenerator generator, String woodType) {
        // 获取对应的木棍方块
        StickBlock stickBlock = (StickBlock) ModBlocks.getStickBlock(woodType);

        MultipartBlockStateSupplier supplier = MultipartBlockStateSupplier.create(stickBlock);

        // 基础主干模型
        supplier.with(
                BlockStateVariant.create().put(VariantSettings.MODEL,
                        new Identifier("fantasy-forest", "block/" + woodType + "_stick_x")));

        // 各个方向的连接部件 - 使用 StickBlock 类中定义的属性
        supplier.with(
                When.create().set(StickBlock.NORTH, true),
                BlockStateVariant.create().put(VariantSettings.MODEL,
                        new Identifier("fantasy-forest", "block/" + woodType + "_stick_side"))
        );
        supplier.with(
                When.create().set(StickBlock.EAST, true),
                BlockStateVariant.create()
                        .put(VariantSettings.MODEL, new Identifier("fantasy-forest", "block/" + woodType + "_stick_side"))
                        .put(VariantSettings.Y, VariantSettings.Rotation.R90)
        );
        supplier.with(
                When.create().set(StickBlock.SOUTH, true),
                BlockStateVariant.create()
                        .put(VariantSettings.MODEL, new Identifier("fantasy-forest", "block/" + woodType + "_stick_side"))
                        .put(VariantSettings.Y, VariantSettings.Rotation.R180)
        );
        supplier.with(
                When.create().set(StickBlock.WEST, true),
                BlockStateVariant.create()
                        .put(VariantSettings.MODEL, new Identifier("fantasy-forest", "block/" + woodType + "_stick_side"))
                        .put(VariantSettings.Y, VariantSettings.Rotation.R270)
        );
        supplier.with(
                When.create().set(StickBlock.UP, true),
                BlockStateVariant.create()
                        .put(VariantSettings.MODEL, new Identifier("fantasy-forest", "block/" + woodType + "_stick_side"))
                        .put(VariantSettings.X, VariantSettings.Rotation.R270)
        );
        supplier.with(
                When.create().set(StickBlock.DOWN, true),
                BlockStateVariant.create()
                        .put(VariantSettings.MODEL, new Identifier("fantasy-forest", "block/" + woodType + "_stick_side"))
                        .put(VariantSettings.X, VariantSettings.Rotation.R90)
        );

        // 注册方块状态
        generator.blockStateCollector.accept(supplier);
    }
}