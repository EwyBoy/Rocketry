package com.ewyboy.rocketry.common.config;

import com.ewyboy.rocketry.Rocketry;
import com.ewyboy.rocketry.common.utility.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.io.File;
import java.util.Arrays;

public class Config extends GuiConfig {

    public static final String CONFIG_WORLDGEN = "worldgen";
    public static Configuration configuration;

    public Config(GuiScreen parentScreen) {
        super(
                parentScreen,
                Arrays.asList(new IConfigElement[] {
                        new ConfigElement(Rocketry.configuration.getCategory(CONFIG_WORLDGEN)),
                }),
                Reference.ModInfo.ModID, false, false, "Rocketry Configuration");
        titleLine2 = Rocketry.configuration.getConfigFile().getAbsolutePath();
    }

    public static Configuration initConfig(File configFile) {
        if (configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
        return configuration;
    }

    public static void loadConfiguration() {
        ConfigWorldGen.init(configuration);
        configuration.save();
    }
}