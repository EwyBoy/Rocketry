package com.ewyboy.rocketry.common.loaders;

import com.ewyboy.rocketry.client.model.PipeModel;
import com.ewyboy.rocketry.common.utility.Reference;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;

/** Created by EwyBoy **/
public class BakedModelLoader implements ICustomModelLoader {

    public static final PipeModel PIPE_MODEL = new PipeModel();

    @Override
    public boolean accepts(ResourceLocation modelLocation) {
        return modelLocation.getResourceDomain().equals(Reference.ModInfo.ModID) && Reference.Blocks.pipe.equals(modelLocation.getResourcePath());
    }

    @Override
    public IModel loadModel(ResourceLocation modelLocation) throws Exception {
        return PIPE_MODEL;
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {

    }
}
