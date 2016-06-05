package com.ewyboy.rocketry.client.render;

import com.ewyboy.rocketry.client.RenderUtils;
import com.ewyboy.rocketry.client.model.ModelFluid;
import com.ewyboy.rocketry.common.tiles.TileTank;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

public class TankRenderer extends TileEntitySpecialRenderer<TileTank> {

    private static ModelFluid fluidModel = new ModelFluid();

    @Override
    public void renderTileEntityAt(TileTank te, double x, double y, double z, float partialTicks, int destroyStage) {
        renderFluid(te, x, y, z);
    }

    private void renderFluid(TileTank te, double x, double y, double z) {
        if (te.tank.getFluid() != null && te.tank.getFluidAmount() > 0) {
            ResourceLocation fluidTexture = RenderUtils.getTexture(te.tank.getFluid().getFluid());

            if (fluidTexture != null) {
                glPushMatrix();
                    GL11.glTranslatef((float) x + .5f, (float) y + 0, (float) z + .5f);
                    GL11.glRotatef(180, 1, 0, 0);
                    double fluidPercent = (int) (((double) te.tank.getFluidAmount() / (double) te.tank.getCapacity()) * 100);
                    GL11.glTranslated(0,(-(fluidPercent / 100)) / 2, 0);
                    GL11.glScaled(1.0, (fluidPercent / 100), 1.0);

                    bindTexture(fluidTexture);
                    fluidModel.renderAll();
                glPopMatrix();
            }
        }
    }
}
