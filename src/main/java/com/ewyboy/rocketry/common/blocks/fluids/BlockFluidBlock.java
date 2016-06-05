package com.ewyboy.rocketry.common.blocks.fluids;

import com.ewyboy.rocketry.common.loaders.CreativeTabLoader;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class BlockFluidBlock extends BlockFluidClassic {

    public BlockFluidBlock(Fluid fluid) {
        super(fluid, Material.LAVA);
        setCreativeTab(CreativeTabLoader.Rocketry);
    }

    @Override
    public String getUnlocalizedName() {
        Fluid fluid = FluidRegistry.getFluid(fluidName);
        if (fluid != null) {
            return fluid.getUnlocalizedName();
        }
        return super.getUnlocalizedName();
    }
}
