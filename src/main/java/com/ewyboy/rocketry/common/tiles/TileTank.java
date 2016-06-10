package com.ewyboy.rocketry.common.tiles;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fluids.*;

import static sun.java2d.opengl.OGLRenderQueue.sync;

public class TileTank extends TileEntityBase implements IFluidHandler, ITickable {

    public FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 8);
    private int prevAmount = tank.getFluidAmount();

    private void save() {
        markDirty();
        if (Math.abs(prevAmount - tank.getFluidAmount()) >= 1000) {
            prevAmount = tank.getFluidAmount();
            if (!worldObj.isRemote) {
                sync();
            }
        }
    }

    @Override
    public void update() {
        if (tank.getFluid() != null && tank.getFluidAmount() > 0) {
            TileEntity te = worldObj.getTileEntity(pos.down());
            if (te != null && te instanceof IFluidHandler) {
                IFluidHandler fluidHandler = (IFluidHandler) te;
                if (fluidHandler.canFill(EnumFacing.UP, tank.getFluid().getFluid())) {
                    drain(EnumFacing.DOWN, fluidHandler.fill(EnumFacing.UP, drain(EnumFacing.DOWN, tank.getCapacity(), false), true), true);
                }
            }
        }
    }

 /*   @Override
    public void onLoad() {
        if (worldObj.isRemote) {
            ShadowMC.network.sendToServer(new PacketRequestTEUpdate(this));
        }
    }*/

    @Override
    public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
        if (canFill(from, null)) {
            int filled = tank.fill(resource, doFill);
            if (doFill) save();
            return filled;
        }
        return 0;
    }


    @Override
    public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain) {
        if (canDrain(from, null)) {
            if (resource == null || !resource.isFluidEqual(tank.getFluid())) {
                return null;
            }
            FluidStack stack = tank.drain(resource.amount, doDrain);
            if (doDrain) save();
            return stack;
        }
        return null;
    }

    @Override
    public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain) {
        if (canDrain(from, null)) {
            FluidStack stack = tank.drain(maxDrain, doDrain);
            if (doDrain) save();
            return stack;
        }
        return null;
    }

    @Override
    public boolean canFill(EnumFacing from, Fluid fluid) {
        return  from == EnumFacing.UP || from == EnumFacing.DOWN || from == null;
    }

    @Override
    public boolean canDrain(EnumFacing from, Fluid fluid) {
        return from == EnumFacing.DOWN || from == EnumFacing.UP || from == null;
    }

    @Override
    public FluidTankInfo[] getTankInfo(EnumFacing from) {
        return new FluidTankInfo[]{tank.getInfo()};
    }

}
