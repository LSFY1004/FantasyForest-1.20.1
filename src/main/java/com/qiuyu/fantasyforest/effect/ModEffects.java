package com.qiuyu.fantasyforest.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static StatusEffect VOID_EFFECT;
    public static StatusEffect SPATIAL_CONSTRAINT;
    public static void registerEffects() {
        VOID_EFFECT = Registry.register(
                Registries.STATUS_EFFECT,
                new Identifier("fantasyforest", "void"),
                new VoidEffect()
        );
        SPATIAL_CONSTRAINT = Registry.register(
                Registries.STATUS_EFFECT,
                new Identifier("fantasyforest","spatial_constraint"),
                new SpatialConstraint(StatusEffectCategory.HARMFUL,0x4B0082)
        );
    }
}
