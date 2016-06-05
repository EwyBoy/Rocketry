package com.ewyboy.rocketry.proxy;

import java.io.File;

public interface IProxy {
    /**
     * Register Blocks
     */
    void registerBlocks();

    /**
     * Register Items
     */
    void registerItems();

    /**
     * Register Ore Dictionary
     */
    void registerOreDict();

    /**
     * Register Furnace Recipes
     */
    void registerFurnaceRecipes();

    /**
     * Register Recipes
     */
    void registerRecipes();

    /**
     * Register Events
     */
    void registerEvents();

    /**
     * Register GUIs
     */
    void registerGUIs();

    /**
     * Register Renderers
     */
    void registerRenderers();

    /**
     * Register World Gen
     */
    void registerWorldGen();

    /**
     * Register Config
     */
    void registerConfiguration(File configFile);

    /**
     * Register Fluids
     */
    void registerFluids();
}