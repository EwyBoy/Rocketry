package com.ewyboy.rocketry.proxy;

import com.ewyboy.rocketry.Rocketry;
import com.ewyboy.rocketry.common.blocks.Blocks;
import com.ewyboy.rocketry.common.blocks.fluids.BlockFluidBlock;
import com.ewyboy.rocketry.common.config.Config;
import com.ewyboy.rocketry.common.enums.EnumAlloys;
import com.ewyboy.rocketry.common.enums.EnumOreType;
import com.ewyboy.rocketry.common.enums.EnumOres;
import com.ewyboy.rocketry.common.items.Items;
import com.ewyboy.rocketry.common.utility.Reference;
import com.ewyboy.rocketry.common.utility.helpers.FluidHelper;
import com.ewyboy.rocketry.common.utility.interfaces.IProvideEvent;
import com.ewyboy.rocketry.common.utility.interfaces.IProvideRecipe;
import com.ewyboy.rocketry.common.utility.interfaces.IProvideSmelting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.io.File;

public abstract class CommonProxy implements IProxy {

    public void loadModels() {}

    @Override
    public void registerBlocks() {
        Blocks.registerBlocks();
    }

    @Override
    public void registerItems() {
        Items.registerItems();
    }

    @Override
    public void registerOreDict() {
        for (EnumOres ores : EnumOres.values()) {
            int meta = ores.getMeta();
            String oreName = ores.getOreName();

            // Register Ore
            if (ores.isTypeSet(EnumOreType.ORE))
                OreDictionary.registerOre("ore" + oreName, Blocks.BLOCK_ORE.getStack(1, meta));

            // Register Ore Block
            if (ores.isTypeSet(EnumOreType.BLOCK))
                OreDictionary.registerOre("block" + oreName, Blocks.BLOCK_ORE_BLOCK.getStack(1, meta));

            // Register Ingot
            if (ores.isTypeSet(EnumOreType.INGOT))
                OreDictionary.registerOre("ingot" + oreName, Items.ITEM_ORE_INGOT.getStack(1, meta));

            // Register Dusts
            if (ores.isTypeSet(EnumOreType.DUST))
                OreDictionary.registerOre("dust" + oreName, Items.ITEM_ORE_DUST.getStack(1, meta));

            // Register Nuggets
            if (ores.isTypeSet(EnumOreType.NUGGET))
                OreDictionary.registerOre("nugget" + oreName, Items.ITEM_ORE_NUGGET.getStack(1, meta));

            // Register Nuggets
            if (ores.isTypeSet(EnumOreType.PLATE))
                OreDictionary.registerOre("plate" + oreName, Items.ITEM_ORE_PLATE.getStack(1, meta));
        }

        for (EnumAlloys ores : EnumAlloys.values()) {
            int meta = ores.getMeta();
            String oreName = ores.getAlloyName();

            // Register Ore
            if (ores.isTypeSet(EnumOreType.ORE))
                OreDictionary.registerOre("ore" + oreName, Blocks.BLOCK_ALLOY.getStack(1, meta));

            // Register Ore Block
            if (ores.isTypeSet(EnumOreType.BLOCK))
                OreDictionary.registerOre("block" + oreName, Blocks.BLOCK_ALLOY_BLOCK.getStack(1, meta));

            // Register Ingot
            if (ores.isTypeSet(EnumOreType.INGOT))
                OreDictionary.registerOre("ingot" + oreName, Items.ITEM_ALLOY_INGOT.getStack(1, meta));

            // Register Dusts
            if (ores.isTypeSet(EnumOreType.DUST))
                OreDictionary.registerOre("dust" + oreName, Items.ITEM_ALLOY_DUST.getStack(1, meta));

            // Register Nuggets
            if (ores.isTypeSet(EnumOreType.NUGGET))
                OreDictionary.registerOre("nugget" + oreName, Items.ITEM_ALLOY_NUGGET.getStack(1, meta));

            // Register Nuggets
            if (ores.isTypeSet(EnumOreType.NUGGET))
                OreDictionary.registerOre("plate" + oreName, Items.ITEM_ALLOY_PLATE.getStack(1, meta));
        }

       /* // Register Gears
        for (EnumMaterialsGear gear : EnumMaterialsGear.values()) {
            OreDictionary.registerOre("gear" + gear.getOreName(), Items.ITEM_MATERIAL_GEAR.getStack(1, gear.getMeta()));
        }*/
    }

    @Override
    public void registerFluids() {
        //todo: make this better once I figure out fluids...
        for (EnumOres ores : EnumOres.values()) {
            int meta = ores.getMeta();
            String oreName = ores.getName();

            if (ores.isTypeSet(EnumOreType.FLUID)) {
                Fluid fluid = FluidHelper.createFluid(oreName, Reference.ModInfo.ModID + ":fluids." + oreName, false);
                FluidRegistry.addBucketForFluid(fluid);

                FluidHelper.registerFluidBlock(new BlockFluidBlock(fluid));
            }
        }
    }

    @Override
    public void registerFurnaceRecipes() {
        for (Items item : Items.values()) {
            if (item.getItem() instanceof IProvideSmelting)
                ((IProvideSmelting) item.getItem()).RegisterSmelting();
        }

        for (Blocks block : Blocks.values()) {
            if (block.getBlock() instanceof IProvideSmelting)
                ((IProvideSmelting) block.getBlock()).RegisterSmelting();
        }
    }

    @Override
    public void registerEvents() {
        for (Items item : Items.values()) {
            if (item.getItem() instanceof IProvideEvent)
                MinecraftForge.EVENT_BUS.register(item.getItem());
        }

        for (Blocks block : Blocks.values()) {
            if (block.getBlock() instanceof IProvideEvent)
                MinecraftForge.EVENT_BUS.register(block.getBlock());
        }
    }

    @Override
    public void registerRecipes() {
        for (Items item : Items.values()) {
            if (item.getItem() instanceof IProvideRecipe)
                ((IProvideRecipe) item.getItem()).RegisterRecipes();
        }

        for (Blocks block : Blocks.values()) {
            if (block.getBlock() instanceof IProvideRecipe)
                ((IProvideRecipe) block.getBlock()).RegisterRecipes();
        }
    }

    @Override
    public void registerGUIs() {
        //NetworkRegistry.INSTANCE.registerGuiHandler(Rocketry.instance, new GuiHandler());
    }

    @Override
    public void registerRenderers() {

    }

    @Override
    public void registerWorldGen() {
        //WorldGenInit.init();
    }

    @Override
    public void registerConfiguration(File configFile) {
        Rocketry.configuration = Config.initConfig(configFile);
    }
}
