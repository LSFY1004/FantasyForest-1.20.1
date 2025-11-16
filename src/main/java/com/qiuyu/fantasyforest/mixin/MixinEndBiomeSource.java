//package com.qiuyu.fantasyforest.mixin;
//
//import com.qiuyu.fantasyforest.world.biome.ModBiomes;
//import net.minecraft.registry.entry.RegistryEntry;
//import net.minecraft.util.Identifier;
//import net.minecraft.util.math.random.Random;
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.biome.source.BiomeCoords;
//import net.minecraft.world.biome.source.TheEndBiomeSource;
//import net.minecraft.world.biome.source.util.MultiNoiseUtil;
//import net.minecraft.world.gen.densityfunction.DensityFunction;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Unique;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//@Mixin(TheEndBiomeSource.class)
//public abstract class MixinEndBiomeSource {
//    @Unique
//    private RegistryEntry<Biome> fantasyforest$cachedEndPlains = null;
//
//    @Unique
//    private static final long CENTER_RADIUS_SQUARED = 1000000L;
//
//    @Inject(
//            method = "getBiome",
//            at = @At("HEAD"),
//            cancellable = true
//    )
//    private void injectCustomEndBiome(
//            int biomeX, int biomeY, int biomeZ,
//            MultiNoiseUtil.MultiNoiseSampler noise,
//            CallbackInfoReturnable<RegistryEntry<Biome>> cir
//    ) {
//        // 1. 坐标转换和距离检查
//        int blockX = BiomeCoords.toBlock(biomeX);
//        int blockZ = BiomeCoords.toBlock(biomeZ);
//        long distSq = (long) blockX * (long) blockX + (long) blockZ * (long) blockZ;
//
//        // 跳过中心岛
//        if (distSq <= CENTER_RADIUS_SQUARED) return;
//
//        // 2. 使用坐标生成确定性随机种子
//        long seed = (long) biomeX * 3129871L ^ (long) biomeZ * 116129781L;
//        Random random = Random.create(seed);
//
//        // 3. 生成概率控制
//        if (random.nextFloat() > 0.05F) return;
//
//        // 4. 噪声条件控制
//        double noiseValue = noise.erosion().sample(
//                new DensityFunction.UnblendedNoisePos(blockX, biomeY, blockZ)
//        );
//        if (noiseValue < 0.25) return;
//
//        // 5. 获取并返回自定义群系
//        TheEndBiomeSource source = (TheEndBiomeSource) (Object) this;
//        RegistryEntry<Biome> customBiome = fantasyforest$getCustomBiome(source);
//        if (customBiome != null) {
//            cir.setReturnValue(customBiome);
//            cir.cancel();
//        }
//    }
//
//    @Unique
//    private RegistryEntry<Biome> fantasyforest$getCustomBiome(TheEndBiomeSource source) {
//        if (fantasyforest$cachedEndPlains == null) {
//            Identifier targetId = ModBiomes.END_TREE_ISLAND.getValue();
//            for (RegistryEntry<Biome> entry : source.getBiomes()) {
//                if (entry.getKey().isPresent() &&
//                        entry.getKey().get().getValue().equals(targetId)) {
//                    fantasyforest$cachedEndPlains = entry;
//                    break;
//                }
//            }
//        }
//        return fantasyforest$cachedEndPlains;
//    }
//}