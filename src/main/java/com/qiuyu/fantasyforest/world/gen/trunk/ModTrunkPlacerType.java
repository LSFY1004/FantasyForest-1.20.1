package com.qiuyu.fantasyforest.world.gen.trunk;

import com.mojang.serialization.Codec;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public record ModTrunkPlacerType<P extends TrunkPlacer>(Codec<P> codec) {

    public static final TrunkPlacerType<PermearomaTrunkPlacer> PERMEAROMA_TRUNK_PLACER = register("permearoma_trunk_placer", PermearomaTrunkPlacer.CODEC);
    public static final TrunkPlacerType<EndTreeTrunkPlacer> END_TREE_TRUNK_PLACER = register("end_tree_trunk_placer", EndTreeTrunkPlacer.CODEC);
    public static final TrunkPlacerType<LargeEndTreeTrunkPlacer> LARGE_END_TREE_TRUNK_PLACER = register("large_end_tree_trunk_placer", LargeEndTreeTrunkPlacer.CODEC);
    public static final TrunkPlacerType<VoidTreeTrunkPlacer> VOID_TREE_TRUNK_PLACER = register("void_tree_trunk_placer", VoidTreeTrunkPlacer.CODEC);
    public static final TrunkPlacerType<LargeVoidTreeTrunkPlacer> LARGE_VOID_TREE_TRUNK_PLACER = register("large_void_tree_trunk_placer", LargeVoidTreeTrunkPlacer.CODEC);

    private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String id, Codec<P> codec) {
        return Registry.register(Registries.TRUNK_PLACER_TYPE, id, new TrunkPlacerType<>(codec));
    }

    public static void register() {

    }
}
