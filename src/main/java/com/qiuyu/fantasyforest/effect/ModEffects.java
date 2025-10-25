package com.qiuyu.fantasyforest.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static StatusEffect VOID_EFFECT;
    public static void registerEffects() {
        VOID_EFFECT = Registry.register(
                Registries.STATUS_EFFECT,
                new Identifier("fantasyforest", "void"),
                new VoidEffect()
        );
    }
}
