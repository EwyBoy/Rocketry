package com.ewyboy.rocketry.common.blocks.bakedmodel;

import com.ewyboy.rocketry.common.utility.Reference;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;

public class BakedModelLoader implements ICustomModelLoader {

    public static final ExampleModel EXAMPLE_MODEL = new ExampleModel();


    @Override
    public boolean accepts(ResourceLocation modelLocation) {
        return modelLocation.getResourceDomain().equals(Reference.ModInfo.ModID) && "bakedmodelblock".equals(modelLocation.getResourcePath());
    }

    @Override
    public IModel loadModel(ResourceLocation modelLocation) throws Exception {
        return EXAMPLE_MODEL;
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {

    }
}
