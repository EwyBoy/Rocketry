package com.ewyboy.rocketry.common.particle;

import com.ewyboy.rocketry.common.utility.Reference;
import com.ewyboy.rocketry.common.utility.helpers.VertexBufferHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import static net.minecraft.client.renderer.GlStateManager.depthMask;
import static org.lwjgl.opengl.GL11.*;

public class BaseParticle extends Particle {

    public BaseParticle(World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        super(world, x, y, z, velocityX, velocityY, velocityZ);
    }

    @Override
    public void setMaxAge(int maxAge) {
        super.setMaxAge(maxAge);
    }

    public BaseParticle setGravity(float gravity) {
        particleGravity = gravity;
        return this;
    }
    public BaseParticle setScale(float scale) {
        particleScale = scale;
        return this;
    }

    public static String texture;
    public static String setTexture(String textureName) {
        texture = textureName;
        return textureName;
    }

    public static final ResourceLocation resourceLocation = new ResourceLocation(Reference.ModInfo.ModID + ":" + "textures/particles/" + setTexture(texture) + ".png");

    @Override
    public void renderParticle(VertexBuffer worldRenderer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        Minecraft.getMinecraft().renderEngine.bindTexture(resourceLocation);

        depthMask(false);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glAlphaFunc(GL_GREATER, 0.003921569f);
        glColor4f(this.getRedColorF(), this.getGreenColorF(), this.getBlueColorF(), this.particleAlpha);

        VertexBufferHelper vertexBufferHelper = new VertexBufferHelper(worldRenderer);

        vertexBufferHelper.startDrawingQuads();
        vertexBufferHelper.setBrightness(getBrightnessForRender(partialTicks));

        float scale = 0.1f * particleScale;

        float x = (float)(prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
        float y = (float)(prevPosY + (posY - prevPosY) * partialTicks - interpPosY);
        float z = (float)(prevPosZ + (posZ - prevPosZ) * partialTicks - interpPosZ);

        vertexBufferHelper.addVertexWithUV(x - 0 * scale - 0 * scale, y - 0 * scale, z - 0 * scale - 0 * scale, 0, 0);
        vertexBufferHelper.addVertexWithUV(x - 0 * scale + 0 * scale, y + 0 * scale, z - 0 * scale + 0 * scale, 1, 0);
        vertexBufferHelper.addVertexWithUV(x + 0 * scale + 0 * scale, y + 0 * scale, z + 0 * scale + 0 * scale, 1, 1);
        vertexBufferHelper.addVertexWithUV(x + 0 * scale - 0 * scale, y - 0 * scale, z + 0 * scale - 0 * scale, 0, 1);

        super.renderParticle(worldRenderer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);

        Tessellator.getInstance().draw();

        glDisable(GL_BLEND);
        glDepthMask(true);
        glAlphaFunc(GL_GREATER, 0.1f);
    }

    @Override
    public int getFXLayer() {
        return 3;
    }
}
