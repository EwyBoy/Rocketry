package com.ewyboy.rocketry.common.loaders;

import com.ewyboy.rocketry.common.blocks.misc.BlockPipe;
import com.ewyboy.rocketry.common.blocks.misc.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLoader {

    public static BlockEngine engine;
    public static BlockRefinery refinery;
    public static BlockPlatingPress platingPress;
    public static BlockCompressor compressor;
    public static BlockTank tank;
    public static BlockPipe bakedModelBlock;

    public static void loadBlocks() {
        engine = new BlockEngine();
        refinery = new BlockRefinery();
        platingPress = new BlockPlatingPress();
        compressor = new BlockCompressor();
        tank = new BlockTank();
        bakedModelBlock = new BlockPipe();
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        engine.initModel();
        refinery.initModel();
        platingPress.initModel();
        compressor.initModel();
        tank.registerBlockRenderer();
        bakedModelBlock.initModel();
    }

    @SideOnly(Side.CLIENT)
    public static void initItemModels() {
        bakedModelBlock.initItemModel();
        tank.registerBlockItemRenderer();
    }
}
