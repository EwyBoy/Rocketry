package com.ewyboy.rocketry.client.render;

import com.ewyboy.rocketry.common.tiles.TileCompressor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class CompressorRenderer extends TileEntitySpecialRenderer<TileCompressor> {

    @Override
    public void renderTileEntityAt(TileCompressor te, double x, double y, double z, float partialTicks, int destroyStage) {
        GlStateManager.pushMatrix();
            GlStateManager.translate(x, y, z);
            GlStateManager.disableRescaleNormal();
            renderItem(te, 0.275f);
            renderItem(te, 0.325f);
            renderItem(te, 0.375f);
            renderItem(te, 0.425f);
            renderItem(te, 0.475f);
            renderItem(te, 0.525f);
            renderItem(te, 0.575f);
            renderItem(te, 0.625f);
            renderItem(te, 0.675f);
        GlStateManager.popMatrix();
    }

    private void renderItem(TileCompressor te, float y) {
        ItemStack stack = te.getStack();
        if (stack != null) {
            RenderHelper.enableStandardItemLighting();
            GlStateManager.enableLighting();
            GlStateManager.pushMatrix();
                if (stack.getItem() instanceof ItemBlock) {
                    GlStateManager.translate(0.5f, 0.475f, 0.5f);
                    GlStateManager.rotate(-90, 1, 0, 0);
                    GlStateManager.scale(0.4f, 0.4f, 0.4f);
                } else {
                    GlStateManager.translate(0.5f, y, 0.5f);
                    GlStateManager.rotate(90, 1, 0, 0);
                    GlStateManager.scale(0.65f, 0.65f, 0.65f);
                }
                Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.NONE);
            GlStateManager.popMatrix();
        }
    }
}
