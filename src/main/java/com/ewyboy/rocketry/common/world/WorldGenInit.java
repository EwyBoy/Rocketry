package com.ewyboy.rocketry.common.world;

import com.ewyboy.rocketry.common.blocks.Blocks;
import com.ewyboy.rocketry.common.config.ConfigWorldGen;
import com.ewyboy.rocketry.common.enums.EnumOreType;
import com.ewyboy.rocketry.common.enums.EnumOres;

public class WorldGenInit {
    public static void init() {
        for (EnumOres ore : EnumOres.byType(EnumOreType.ORE)) {
            ConfigWorldGen.OreConfig config = ConfigWorldGen.OreWorldGen.get(ore);
            WorldGen.addOreGen(ore.getName(), Blocks.BLOCK_ORE.getBlock().getStateFromMeta(ore.getMeta()), config);
        }
    }
}
