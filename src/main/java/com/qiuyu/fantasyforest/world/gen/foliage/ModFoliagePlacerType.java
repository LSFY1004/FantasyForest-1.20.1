package com.qiuyu.fantasyforest.world.gen.foliage;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class ModFoliagePlacerType {
    public static final FoliagePlacerType<EllipsoidFoliagePlacer> ELLIPSOID_FOLIAGE_PLACER =
            Registry.register(Registries.FOLIAGE_PLACER_TYPE,
                    new Identifier("fantasyforest", "ellipsoid_foliage_placer"),
                    new FoliagePlacerType<>(EllipsoidFoliagePlacer.CODEC));

    public static void register() {

    }
}
