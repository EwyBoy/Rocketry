package com.ewyboy.rocketry.common.compatibilities;

import com.ewyboy.rocketry.common.compatibilities.waila.WailaCompatibility;
import net.minecraftforge.fml.common.Loader;

/** Created by EwyBoy **/
public class CompatibilityHandler {
    public static void registerWaila() {
        if (Loader.isModLoaded("Waila")) WailaCompatibility.register();
    }
}
