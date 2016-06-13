package com.ewyboy.rocketry.common.blocks.misc;

import com.ewyboy.rocketry.common.compatibilities.waila.IWailaUser;
import com.ewyboy.rocketry.common.loaders.CreativeTabLoader;
import com.ewyboy.rocketry.common.tiles.TileTank;
import com.ewyboy.rocketry.common.utility.Platform;
import com.ewyboy.rocketry.common.utility.Reference;
import com.ewyboy.rocketry.common.utility.interfaces.IBlockRenderer;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class BlockTank extends Block implements ITileEntityProvider, IWailaUser, IBlockRenderer {

    protected String resourcePath = "";

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


    @Nonnull
    @Override
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

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockRenderer() {
        final String resourcePath = String.format("%s:%s", Reference.ModInfo.ModID, this.resourcePath);

        ModelLoader.setCustomStateMapper(this, new DefaultStateMapper() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation(resourcePath, getPropertyString(state.getProperties()));
            }
        });
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockItemRenderer() {
        final String resourcePath = String.format("%s:%s", Reference.ModInfo.ModID, this.resourcePath);

        List<ItemStack> subBlocks = new ArrayList<>();
        getSubBlocks(Item.getItemFromBlock(this), null, subBlocks);

        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), new ModelResourceLocation(resourcePath, "inventory"));
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
}
