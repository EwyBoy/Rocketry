package com.ewyboy.rocketry.common.blocks.misc;

import com.ewyboy.rocketry.client.render.TankRenderer;
import com.ewyboy.rocketry.common.compatibilities.waila.IWailaUser;
import com.ewyboy.rocketry.common.loaders.CreativeTabLoader;
import com.ewyboy.rocketry.common.tiles.TileTank;
import com.ewyboy.rocketry.common.utility.Reference;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

public class BlockTank extends Block implements ITileEntityProvider, IWailaUser {
    
    public BlockTank() {
        super(Material.GLASS);
        setUnlocalizedName(Reference.Blocks.tank);
        setRegistryName(Reference.Blocks.tank);
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this), getRegistryName());
        GameRegistry.registerTileEntity(TileTank.class, "tank");
        setCreativeTab(CreativeTabLoader.Rocketry);
    }

    private TileTank getTE(IBlockAccess world, BlockPos pos) {
        return (TileTank) world.getTileEntity(pos);
    }

    @Override
    public int getLightValue(@Nonnull IBlockState state, IBlockAccess world, @Nonnull BlockPos pos) {
        TileEntity te = world.getTileEntity(pos);
        if(!(te instanceof TileTank)) {
            return 0;
        }
        TileTank tank = (TileTank) te;
        return tank.getBrightness();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileTank te = getTE(world, pos);
        if (te instanceof TileTank) {
            ItemStack input = player.getHeldItem(hand);
            if (FluidUtil.interactWithFluidHandler(input, te.tank, player)) return true;
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState blockState) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState blockState) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileTank.class, new TankRenderer());
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileTank();
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        BlockPos pos = accessor.getPosition();
        World world = accessor.getWorld();
        EntityPlayer player = accessor.getPlayer();

        TileTank te = getTE(world, pos);

        if (player.isSneaking()) {
            if (te instanceof TileTank) {
                if (te.tank.getFluid() != null) {
                    currenttip.add("Liquid: " + te.tank.getFluid().getLocalizedName());
                } else {
                    currenttip.add("Liquid: " + "EMPTY");
                }
                currenttip.add(te.tank.getFluidAmount() + "/" + te.tank.getCapacity() + " mB");
            }
        }
        return currenttip;
    }


    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
