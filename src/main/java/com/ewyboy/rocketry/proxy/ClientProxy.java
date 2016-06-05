package com.ewyboy.rocketry.proxy;

import com.ewyboy.rocketry.common.loaders.BlockLoader;

public class ClientProxy extends CommonProxy {

    @Override
    public void loadModels() {
        BlockLoader.initModels();
    }
}
