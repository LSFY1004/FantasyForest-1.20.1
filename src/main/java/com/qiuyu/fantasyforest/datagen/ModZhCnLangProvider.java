package com.qiuyu.fantasyforest.datagen;

import com.qiuyu.fantasyforest.block.ModBlocks;
import com.qiuyu.fantasyforest.effect.ModEffects;
import com.qiuyu.fantasyforest.item.MODItems;
import com.qiuyu.fantasyforest.item.ModItemGroups;
import com.qiuyu.fantasyforest.world.biome.ModBiomes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class ModZhCnLangProvider extends FabricLanguageProvider {
    public ModZhCnLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput, "zh_cn");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItemGroups.Fantasy_Forest,"幻想森林");
        translationBuilder.add("tooltip.arboreal","§a本源共鸣§r");
        translationBuilder.add("tooltip.arboreal.shift","§a本源共鸣§7§o—Shift+右键瞬间收集同类木制方块。");
        translationBuilder.add("tooltip.fantasyforest.more","§7按下§rShift§7获取更多信息。§r");
        translationBuilder.add("tooltip.fantasyforest.permearoma","§3芳香§r");
        translationBuilder.add("tooltip.fantasyforest.permearoma.shift","§3芳香§7§o—降低受击生物们感知到你的范围。");
        translationBuilder.add("tooltip.fantasyforest.end_wood","§5空间阈限§r");
        translationBuilder.add("tooltip.fantasyforest.end_wood.shift","§5空间阈限§7§o—短暂时间内会将离开一定范围的受击者强制传送回来。");
        translationBuilder.add("tooltip.fantasyforest.mutated_end_wood","§2空间扭曲§r");
        translationBuilder.add("tooltip.fantasyforest.mutated_end_wood.shift","§2空间扭曲§7§o—受击者会被传送到下方数格。");
        translationBuilder.add("tooltip.fantasyforest.void_wood","§8虚空§r");
        translationBuilder.add("tooltip.fantasyforest.void_wood.shift","§8虚空§7§o—短暂间隔后，受击者将持续受到虚空伤害。");

        translationBuilder.add("biome."+ModBiomes.LAND_OF_REVELATION.getValue(),"源识地");
        translationBuilder.add("biome."+ModBiomes.END_TREE_ISLAND.getValue(),"末影树岛");

        translationBuilder.add(MODItems.SEED_OF_REVELATION,"启示之种");

        translationBuilder.add(ModBlocks.ARBOREAL_CRAFTING_TABLE,"元素木工台");
        translationBuilder.add(ModBlocks.ENDER_TRANSMISSION_SUPPRESSOR,"末影传送抑制器");

        translationBuilder.add(ModBlocks.OAK_STICK,"橡木木棍");
        translationBuilder.add(ModBlocks.DARK_OAK_STICK,"深色橡木木棍");
        translationBuilder.add(ModBlocks.BAMBOO_STICK,"竹棍");
        translationBuilder.add(ModBlocks.CHERRY_STICK,"樱花木棍");
        translationBuilder.add(ModBlocks.CRIMSON_STICK,"绯红木棍");
        translationBuilder.add(ModBlocks.ACACIA_STICK,"金合欢木棍");
        translationBuilder.add(ModBlocks.WARPED_STICK,"诡异木棍");
        translationBuilder.add(ModBlocks.SPRUCE_STICK,"云杉木棍");
        translationBuilder.add(ModBlocks.BIRCH_STICK,"白桦木棍");
        translationBuilder.add(ModBlocks.JUNGLE_STICK,"丛林木棍");
        translationBuilder.add(ModBlocks.MANGROVE_STICK,"红树木棍");

        translationBuilder.add(ModBlocks.END_LICHEN,"末影地衣");
        translationBuilder.add(ModBlocks.DEAD_END_LICHEN,"失活的末影地衣");

        translationBuilder.add(ModBlocks.REVELATION_LEAVES,"启示之树叶");
        translationBuilder.add(ModBlocks.REVELATION_LOG,"启示之木原木");
        translationBuilder.add(ModBlocks.REVELATION_WOOD,"启示之木");
        translationBuilder.add(ModBlocks.STRIPPED_REVELATION_LOG,"去皮启示之木原木");
        translationBuilder.add(ModBlocks.STRIPPED_REVELATION_WOOD,"去皮启示之木");
        translationBuilder.add(ModBlocks.REVELATION_PLANKS,"启示之木板");
        translationBuilder.add(ModBlocks.REVELATION_STAIRS,"启示之木楼梯");
        translationBuilder.add(ModBlocks.REVELATION_SLAB,"启示之木台阶");
        translationBuilder.add(ModBlocks.REVELATION_BUTTON,"启示之木按钮");
        translationBuilder.add(ModBlocks.REVELATION_DOOR,"启示之木门");
        translationBuilder.add(ModBlocks.REVELATION_FENCE,"启示之木栅栏");
        translationBuilder.add(ModBlocks.REVELATION_TRAPDOOR,"启示之木活板门");
        translationBuilder.add(ModBlocks.REVELATION_FENCE_GATE,"启示之木栅栏门");
        translationBuilder.add(ModBlocks.REVELATION_PRESSURE_PLATE,"启示之木压力板");
        translationBuilder.add(MODItems.REVELATION_SIGN,"启示之木告示牌");
        translationBuilder.add(MODItems.REVELATION_HANGING_SIGN,"启示之木悬挂告示牌");
        translationBuilder.add(MODItems.REVELATION_BOAT,"启示之木船");
        translationBuilder.add(MODItems.REVELATION_CHEST_BOAT,"启示之木运输船");
        translationBuilder.add(ModBlocks.REVELATION_SAPLING,"启示之树苗");
        translationBuilder.add(ModBlocks.REVELATION_STICK,"启示之木棍");
        translationBuilder.add(ModBlocks.REVELATION_CORE,"启示之核心");
        translationBuilder.add(ModBlocks.REVELATION_CORE_SIDE,"启示之核心侧面");

        translationBuilder.add(MODItems.PERMEAROMA_LEAF_PIECES,"沁芳树叶片");
        translationBuilder.add(ModBlocks.PERMEAROMA_LEAVES,"沁芳树叶");
        translationBuilder.add(ModBlocks.PERMEAROMA_LOG,"沁芳木原木");
        translationBuilder.add(ModBlocks.PERMEAROMA_WOOD,"沁芳木");
        translationBuilder.add(ModBlocks.STRIPPED_PERMEAROMA_LOG,"去皮沁芳木原木");
        translationBuilder.add(ModBlocks.STRIPPED_PERMEAROMA_WOOD,"去皮沁芳木");
        translationBuilder.add(ModBlocks.PERMEAROMA_PLANKS,"沁芳木板");
        translationBuilder.add(ModBlocks.PERMEAROMA_STAIRS,"沁芳木楼梯");
        translationBuilder.add(ModBlocks.PERMEAROMA_SLAB,"沁芳木台阶");
        translationBuilder.add(ModBlocks.PERMEAROMA_BUTTON,"沁芳木按钮");
        translationBuilder.add(ModBlocks.PERMEAROMA_DOOR,"沁芳木门");
        translationBuilder.add(ModBlocks.PERMEAROMA_FENCE,"沁芳木栅栏");
        translationBuilder.add(ModBlocks.PERMEAROMA_TRAPDOOR,"沁芳木活板门");
        translationBuilder.add(ModBlocks.PERMEAROMA_FENCE_GATE,"沁芳木栅栏门");
        translationBuilder.add(ModBlocks.PERMEAROMA_PRESSURE_PLATE,"沁芳木压力板");
        translationBuilder.add(MODItems.PERMEAROMA_SIGN,"沁芳木告示牌");
        translationBuilder.add(MODItems.PERMEAROMA_HANGING_SIGN,"沁芳木悬挂告示牌");
        translationBuilder.add(MODItems.PERMEAROMA_BOAT,"沁芳木船");
        translationBuilder.add(MODItems.PERMEAROMA_CHEST_BOAT,"沁芳木运输船");
        translationBuilder.add(ModBlocks.PERMEAROMA_SAPLING,"沁芳树苗");
        translationBuilder.add(ModBlocks.PERMEAROMA_STICK,"沁芳木棍");
        translationBuilder.add(MODItems.PERMEAROMA_SWORD,"沁芳木剑");
        translationBuilder.add(MODItems.PERMEAROMA_PICKAXE,"沁芳木镐");
        translationBuilder.add(MODItems.PERMEAROMA_AXE,"沁芳木斧");
        translationBuilder.add(MODItems.PERMEAROMA_HOE,"沁芳木锄");
        translationBuilder.add(MODItems.PERMEAROMA_SHOVEL,"沁芳木锹");

        translationBuilder.add(ModBlocks.APHRODISIA_LEAVES,"情花树叶");
        translationBuilder.add(ModBlocks.APHRODISIA_LOG,"情花木原木");
        translationBuilder.add(ModBlocks.APHRODISIA_WOOD,"情花木");
        translationBuilder.add(ModBlocks.STRIPPED_APHRODISIA_LOG,"去皮情花木原木");
        translationBuilder.add(ModBlocks.STRIPPED_APHRODISIA_WOOD,"去皮情花木");
        translationBuilder.add(ModBlocks.APHRODISIA_PLANKS,"情花木板");
        translationBuilder.add(ModBlocks.APHRODISIA_STAIRS,"情花木楼梯");
        translationBuilder.add(ModBlocks.APHRODISIA_SLAB,"情花木台阶");
        translationBuilder.add(ModBlocks.APHRODISIA_BUTTON,"情花木按钮");
        translationBuilder.add(ModBlocks.APHRODISIA_DOOR,"情花木门");
        translationBuilder.add(ModBlocks.APHRODISIA_FENCE,"情花木栅栏");
        translationBuilder.add(ModBlocks.APHRODISIA_TRAPDOOR,"情花木活板门");
        translationBuilder.add(ModBlocks.APHRODISIA_FENCE_GATE,"情花木栅栏门");
        translationBuilder.add(ModBlocks.APHRODISIA_PRESSURE_PLATE,"情花木压力板");
        translationBuilder.add(MODItems.APHRODISIA_SIGN,"情花木告示牌");
        translationBuilder.add(MODItems.APHRODISIA_HANGING_SIGN,"情花木悬挂告示牌");
        translationBuilder.add(MODItems.APHRODISIA_BOAT,"情花木船");
        translationBuilder.add(MODItems.APHRODISIA_CHEST_BOAT,"情花木运输船");
        translationBuilder.add(ModBlocks.APHRODISIA_SAPLING,"情花树苗");
        translationBuilder.add(ModBlocks.APHRODISIA_STICK,"情花木棍");
        translationBuilder.add(MODItems.APHRODISIA_SWORD,"情花木剑");
        translationBuilder.add(MODItems.APHRODISIA_PICKAXE,"情花木镐");
        translationBuilder.add(MODItems.APHRODISIA_AXE,"情花木斧");
        translationBuilder.add(MODItems.APHRODISIA_HOE,"情花木锄");
        translationBuilder.add(MODItems.APHRODISIA_SHOVEL,"情花木锹");

        translationBuilder.add(MODItems.END_RESIN,"末影树脂");
        translationBuilder.add(ModBlocks.END_RESIN_BLOCK,"末影树脂块");
        translationBuilder.add(ModBlocks.END_LEAVES,"末影树叶");
        translationBuilder.add(ModBlocks.END_LOG,"末影木原木");
        translationBuilder.add(ModBlocks.END_WOOD,"末影木");
        translationBuilder.add(ModBlocks.STRIPPED_END_LOG,"去皮末影木原木");
        translationBuilder.add(ModBlocks.STRIPPED_END_WOOD,"去皮末影木");
        translationBuilder.add(ModBlocks.END_PLANKS,"末影木板");
        translationBuilder.add(ModBlocks.END_WOODEN_STAIRS,"末影木楼梯");
        translationBuilder.add(ModBlocks.END_WOODEN_SLAB,"末影木台阶");
        translationBuilder.add(ModBlocks.END_WOODEN_BUTTON,"末影木按钮");
        translationBuilder.add(ModBlocks.END_WOODEN_DOOR,"末影木门");
        translationBuilder.add(ModBlocks.END_WOODEN_FENCE,"末影木栅栏");
        translationBuilder.add(ModBlocks.END_WOODEN_TRAPDOOR,"末影木活板门");
        translationBuilder.add(ModBlocks.END_WOODEN_FENCE_GATE,"末影木栅栏门");
        translationBuilder.add(ModBlocks.END_WOODEN_PRESSURE_PLATE,"末影木压力板");
        translationBuilder.add(MODItems.END_WOODEN_SIGN,"末影木告示牌");
        translationBuilder.add(MODItems.END_WOODEN_HANGING_SIGN,"末影木悬挂告示牌");
        translationBuilder.add(MODItems.END_WOODEN_BOAT,"末影木船");
        translationBuilder.add(MODItems.END_WOODEN_CHEST_BOAT,"末影木运输船");
        translationBuilder.add(ModBlocks.END_TREE_SAPLING,"末影树苗");
        translationBuilder.add(ModBlocks.END_STICK,"末影木棍");
        translationBuilder.add(MODItems.END_WOODEN_SWORD,"末影木剑");
        translationBuilder.add(MODItems.END_WOODEN_PICKAXE,"末影木镐");
        translationBuilder.add(MODItems.END_WOODEN_AXE,"末影木斧");
        translationBuilder.add(MODItems.END_WOODEN_HOE,"末影木锄");
        translationBuilder.add(MODItems.END_WOODEN_SHOVEL,"末影木锹");

        translationBuilder.add(MODItems.MUTATED_END_RESIN,"变异末影树脂");
        translationBuilder.add(ModBlocks.MUTATED_END_RESIN_BLOCK,"变异末影树脂块");
        translationBuilder.add(ModBlocks.MUTATED_END_LOG,"变异末影木原木");
        translationBuilder.add(ModBlocks.MUTATED_END_WOOD,"变异末影木");
        translationBuilder.add(ModBlocks.STRIPPED_MUTATED_END_LOG,"去皮变异末影木原木");
        translationBuilder.add(ModBlocks.STRIPPED_MUTATED_END_WOOD,"去皮变异末影木");
        translationBuilder.add(ModBlocks.MUTATED_END_PLANKS,"变异末影木板");
        translationBuilder.add(ModBlocks.MUTATED_END_WOODEN_STAIRS,"变异末影木楼梯");
        translationBuilder.add(ModBlocks.MUTATED_END_WOODEN_SLAB,"变异末影木台阶");
        translationBuilder.add(ModBlocks.MUTATED_END_WOODEN_BUTTON,"变异末影木按钮");
        translationBuilder.add(ModBlocks.MUTATED_END_WOODEN_DOOR,"变异末影木门");
        translationBuilder.add(ModBlocks.MUTATED_END_WOODEN_FENCE,"变异末影木栅栏");
        translationBuilder.add(ModBlocks.MUTATED_END_WOODEN_TRAPDOOR,"变异末影木活板门");
        translationBuilder.add(ModBlocks.MUTATED_END_WOODEN_FENCE_GATE,"变异末影木栅栏门");
        translationBuilder.add(ModBlocks.MUTATED_END_WOODEN_PRESSURE_PLATE,"变异末影木压力板");
        translationBuilder.add(MODItems.MUTATED_END_WOODEN_SIGN,"变异末影木告示牌");
        translationBuilder.add(MODItems.MUTATED_END_WOODEN_HANGING_SIGN,"变异末影木悬挂告示牌");
        translationBuilder.add(MODItems.MUTATED_END_WOODEN_BOAT,"变异末影木船");
        translationBuilder.add(MODItems.MUTATED_END_WOODEN_CHEST_BOAT,"变异末影木运输船");
        translationBuilder.add(ModBlocks.MUTATED_END_STICK,"变异末影木棍");
        translationBuilder.add(MODItems.MUTATED_END_WOODEN_SWORD,"变异末影木剑");
        translationBuilder.add(MODItems.MUTATED_END_WOODEN_PICKAXE,"变异末影木镐");
        translationBuilder.add(MODItems.MUTATED_END_WOODEN_AXE,"变异末影木斧");
        translationBuilder.add(MODItems.MUTATED_END_WOODEN_HOE,"变异末影木锄");
        translationBuilder.add(MODItems.MUTATED_END_WOODEN_SHOVEL,"变异末影木锹");

        translationBuilder.add(MODItems.VOID_RESIN,"虚空树脂");
        translationBuilder.add(ModBlocks.VOID_RESIN_BLOCK,"虚空树脂块");
        translationBuilder.add(ModBlocks.VOID_LEAVES,"虚空树叶");
        translationBuilder.add(ModBlocks.VOID_ROOTS,"虚空树根");
        translationBuilder.add(ModBlocks.VOID_VINE_HEAD,"虚空藤头");
        translationBuilder.add(ModBlocks.VOID_VINE_BODY,"虚空藤身");
        translationBuilder.add(ModBlocks.PHOSPHOR,"磷光体");
        translationBuilder.add(ModBlocks.FRUITING_VOID_VINE_HEAD,"结果虚空藤头");
        translationBuilder.add(ModBlocks.VOID_TREE_SAPLING,"虚空树苗");
        translationBuilder.add(ModBlocks.VOID_LOG,"虚空木原木");
        translationBuilder.add(ModBlocks.VOID_WOOD,"虚空木");
        translationBuilder.add(ModBlocks.STRIPPED_VOID_LOG,"去皮虚空木原木");
        translationBuilder.add(ModBlocks.STRIPPED_VOID_WOOD,"去皮虚空木");
        translationBuilder.add(ModBlocks.VOID_PLANKS,"虚空木板");
        translationBuilder.add(ModBlocks.VOID_WOODEN_STAIRS,"虚空木楼梯");
        translationBuilder.add(ModBlocks.VOID_WOODEN_SLAB,"虚空木台阶");
        translationBuilder.add(ModBlocks.VOID_WOODEN_BUTTON,"虚空木按钮");
        translationBuilder.add(ModBlocks.VOID_WOODEN_DOOR,"虚空木门");
        translationBuilder.add(ModBlocks.VOID_WOODEN_FENCE,"虚空木栅栏");
        translationBuilder.add(ModBlocks.VOID_WOODEN_TRAPDOOR,"虚空木活板门");
        translationBuilder.add(ModBlocks.VOID_WOODEN_FENCE_GATE,"虚空木栅栏门");
        translationBuilder.add(ModBlocks.VOID_WOODEN_PRESSURE_PLATE,"虚空木压力板");
        translationBuilder.add(MODItems.VOID_WOODEN_SIGN,"虚空木告示牌");
        translationBuilder.add(MODItems.VOID_WOODEN_HANGING_SIGN,"虚空木悬挂告示牌");
        translationBuilder.add(MODItems.VOID_WOODEN_BOAT,"虚空木船");
        translationBuilder.add(MODItems.VOID_WOODEN_CHEST_BOAT,"虚空木运输船");
        translationBuilder.add(MODItems.VOID_FRUIT,"虚空果");
        translationBuilder.add(ModBlocks.VOID_STICK,"虚空木棍");
        translationBuilder.add(MODItems.VOID_WOODEN_SWORD,"虚空木剑");
        translationBuilder.add(MODItems.VOID_WOODEN_PICKAXE,"虚空木镐");
        translationBuilder.add(MODItems.VOID_WOODEN_AXE,"虚空木斧");
        translationBuilder.add(MODItems.VOID_WOODEN_HOE,"虚空木锄");
        translationBuilder.add(MODItems.VOID_WOODEN_SHOVEL,"虚空木锹");

        translationBuilder.add(ModEffects.SPATIAL_CONSTRAINT,"空间阈限");
        translationBuilder.add(ModEffects.VOID_EFFECT,"虚空");
        translationBuilder.add("death.attack.void","%1$s 被虚空吞噬了");
        translationBuilder.add("death.attack.void.player","%1$s 在逃离%2$s时被虚空瓦解了");
    }
}
