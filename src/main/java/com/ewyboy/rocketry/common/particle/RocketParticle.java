package com.ewyboy.rocketry.common.particle;

import net.minecraft.world.World;

public class RocketParticle extends BaseParticle {

    public RocketParticle(World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        super(world, x, y, z, velocityX, velocityY, velocityZ);
        setTexture("SmokeParticle");
        setGravity(0.675f);
        setScale(5);
        setMaxAge(30);
        setRBGColorF(0.15f,0.15f,0.15f);
        setAlphaF(0.25f);
    }
}
