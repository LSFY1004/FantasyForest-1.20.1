//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.qiuyu.fantasyforest.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import com.qiuyu.fantasyforest.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.VineBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class VoidVineDecorator extends TreeDecorator {
    public static final Codec<VoidVineDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(VoidVineDecorator::new, (treeDecorator) -> treeDecorator.probability).codec();
    private final float probability;

    protected TreeDecoratorType<?> getType() {
        return ModTreeDecoratorType.VOID_VINE;
    }

    public VoidVineDecorator(float probability) {
        this.probability = probability;
    }

    public void generate(TreeDecorator.Generator generator) {
        Random random = generator.getRandom();
        generator.getLeavesPositions().forEach((pos) -> {
            if (random.nextFloat() < this.probability) {
                BlockPos blockPos = pos.down();
                if (generator.isAir(blockPos)) {
                    placeVines(blockPos, generator);
                }
            }
        });
    }

    private static void placeVines(BlockPos pos, Generator generator) {
        // 创建虚空藤方块状态（默认状态 + 方向）
        BlockState vineState = ModBlocks.VOID_VINE_BODY.getDefaultState();

        // 放置起始藤蔓
        generator.replace(pos, vineState);

        // 向下延伸（最多4格）
        int extensionCount = 4;
        BlockPos currentPos = pos.down();

        while (extensionCount > 0 && generator.isAir(currentPos)) {
            generator.replace(currentPos, vineState);
            currentPos = currentPos.down();
            extensionCount--;
        }
        generator.replace(generator.isAir(currentPos)?currentPos:currentPos.up(), ModBlocks.VOID_VINE_HEAD.getDefaultState());
    }
}
