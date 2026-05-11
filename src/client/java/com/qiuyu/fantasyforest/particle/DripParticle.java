package com.qiuyu.fantasyforest.particle;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class DripParticle extends SpriteBillboardParticle {

    protected DripParticle(ClientWorld world, double x, double y, double z,
                           double velX, double velY, double velZ,
                           SpriteProvider spriteProvider) {
        super(world, x, y, z, velX, velY, velZ);
        this.maxAge = 30 + random.nextInt(20);
        this.gravityStrength = 0.2f;
        this.velocityY = -0.08 - random.nextDouble() * 0.03;
        this.velocityX = velX + (random.nextDouble() - 0.5) * 0.02;
        this.velocityZ = velZ + (random.nextDouble() - 0.5) * 0.02;
        this.scale(0.8f);
        this.setSprite(spriteProvider.getSprite(random));
    }

    @Override
    public void tick() {
        super.tick();
        this.velocityY -= this.gravityStrength;
        this.setVelocity(this.velocityX, this.velocityY, this.velocityZ);
        if (this.onGround) {
            this.markDead();
        }
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(DefaultParticleType type, ClientWorld world,
                                       double x, double y, double z,
                                       double velX, double velY, double velZ) {
            return new DripParticle(world, x, y, z, velX, velY, velZ, spriteProvider);
        }
    }
}