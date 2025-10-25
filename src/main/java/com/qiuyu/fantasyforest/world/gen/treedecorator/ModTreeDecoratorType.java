package com.qiuyu.fantasyforest.world.gen.treedecorator;

import com.qiuyu.fantasyforest.FantasyForest;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class ModTreeDecoratorType {
    // 定义自定义装饰器类型
    public static TreeDecoratorType<VoidVineDecorator> VOID_VINE;

    public static void registerTreeDecorators() {
        // 创建并注册自定义装饰器类型
        VOID_VINE = Registry.register(
                Registries.TREE_DECORATOR_TYPE,
                new Identifier(FantasyForest.MOD_ID,"void_vine_decorator"),
                new TreeDecoratorType<>(VoidVineDecorator.CODEC)
        );

        FantasyForest.LOGGER.info("Registering Void Vine Tree Decorator");
    }
}