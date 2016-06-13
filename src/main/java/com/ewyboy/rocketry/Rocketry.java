package com.ewyboy.rocketry;

import com.ewyboy.rocketry.common.loaders.BlockLoader;
import com.ewyboy.rocketry.common.loaders.ItemLoader;
import com.ewyboy.rocketry.common.utility.Logger;
import com.ewyboy.rocketry.common.utility.Reference;
import com.ewyboy.rocketry.proxy.CommonProxy;
import com.google.common.base.Stopwatch;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Mod(modid = Reference.ModInfo.ModID, name = Reference.ModInfo.ModName, version = Reference.ModInfo.BuildVersion, acceptedMinecraftVersions = "["+ Reference.ModInfo.MinecraftVersion+"]")
public class Rocketry {

    public static FMLEventChannel packetHandler;

    @Mod.Instance(Reference.ModInfo.ModID)
    public static Rocketry instance;

    @SidedProxy(modId = Reference.ModInfo.ModID, clientSide = Reference.Path.clientProxyPath, serverSide = Reference.Path.commonProxyPath)
    public static CommonProxy proxy;

    public static Configuration configuration;

    private long launchTime;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Pre-Initialization started");
            packetHandler = NetworkRegistry.INSTANCE.newEventDrivenChannel(Reference.ModInfo.ModID);
                proxy.registerConfiguration(event.getSuggestedConfigurationFile());
                proxy.registerBlocks();
                proxy.registerItems();
                proxy.registerGUIs();
                proxy.registerFurnaceRecipes();
                proxy.registerOreDict();
                proxy.registerEvents();
                proxy.registerRenderers();
                proxy.registerWorldGen();
                proxy.registerFluids();
                proxy.registerCompatibilities();
                launchTime += watch.elapsed(TimeUnit.MILLISECONDS);
            Logger.info("Pre-Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
        Logger.info("Pre-Initialization process successfully done");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Initialization started");
                ItemLoader.loadItems();
                BlockLoader.loadBlocks();
                proxy.loadModels();
                proxy.loadItemModels();
                proxy.registerRecipes();
                launchTime += watch.elapsed(TimeUnit.MILLISECONDS);
            Logger.info("Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
        Logger.info("Initialization process successfully done");
    }

    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Post-Initialization started");
                Map<String, Fluid> fluids = FluidRegistry.getRegisteredFluids();
                for (String key : fluids.keySet()) {Fluid fluid = fluids.get(key); Logger.info(">>> Fluid Name: " + key + " (" + fluid.getUnlocalizedName() + ")");}
                launchTime += watch.elapsed(TimeUnit.MILLISECONDS);
            Logger.info("Post-Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
        Logger.info("Post-Initialization process successfully done");
        Logger.info("Total Initialization time was " + launchTime);
    }
}
