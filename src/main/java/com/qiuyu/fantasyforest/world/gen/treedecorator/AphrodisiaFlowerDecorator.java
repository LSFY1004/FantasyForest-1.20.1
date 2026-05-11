package com.qiuyu.fantasyforest.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import com.qiuyu.fantasyforest.block.custom.TreeFlowerBlock;
import com.qiuyu.fantasyforest.block.ModBlocks;  // 假设你的方块注册类
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class AphrodisiaFlowerDecorator extends TreeDecorator {
    public static final Codec<AphrodisiaFlowerDecorator> CODEC = Codec.floatRange(0.0F, 1.0F)
            .fieldOf("probability")
            .xmap(AphrodisiaFlowerDecorator::new, decorator -> decorator.probability)
            .codec();

    private final float probability;

    @Override
    protected TreeDecoratorType<?> getType() {
        return ModTreeDecoratorType.APHRODISIA_FLOWER;
    }

    public AphrodisiaFlowerDecorator(float probability) {
        this.probability = probability;
    }

    @Override
    public void generate(Generator generator) {
        Random random = generator.getRandom();
        generator.getLeavesPositions().forEach(leafPos -> {
            if (random.nextFloat() < probability) {
                // 树叶下方开始生成长串
                BlockPos startPos = leafPos.down();
                // 随机长度 1~4
                int length = 1 + random.nextInt(4);
                // 检查每个位置是否可放置（空气或可替换）
                for (int i = 0; i < length; i++) {
                    BlockPos currentPos = startPos.down(i);
                    if (!generator.isAir(currentPos)) {
                        // 遇到障碍，停止继续生成（这串可能比预期短）
                        break;
                    }
                    // 确定该花朵在当前串中的状态
                    int state;
                    if (length == 1) {
                        state = 0;          // 单花
                    } else {
                        if (i == 0) {
                            state = 1;      // 最顶端（紧贴树叶）
                        } else if (i == length - 1) {
                            state = 2;      // 最底端
                        } else {
                            state = 3;      // 中间
                        }
                    }
                    generator.replace(currentPos, ModBlocks.APHRODISIA_FLOWER.getDefaultState()
                            .with(TreeFlowerBlock.STATE, state));
                }
            }
        });
    }
}