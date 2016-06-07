package com.ewyboy.rocketry.proxy;

import com.ewyboy.rocketry.common.loaders.BakedModelLoader;
import com.ewyboy.rocketry.common.loaders.BlockLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void loadModels() {
        ModelLoaderRegistry.registerLoader(new BakedModelLoader());
        BlockLoader.initModels();
    }

    @Override
    public void loadItemModels() {
        BlockLoader.initItemModels();
    }

}
