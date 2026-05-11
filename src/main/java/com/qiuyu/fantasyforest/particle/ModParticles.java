package com.qiuyu.fantasyforest.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final ParticleType<DefaultParticleType> DRIPPING_PARTICLE =
            FabricParticleTypes.simple();

    public static void registerParticles(){
        Registry.register(Registries.PARTICLE_TYPE,
                Identifier.of("fantasy-forest", "dripping"),
                DRIPPING_PARTICLE);
    }
}
