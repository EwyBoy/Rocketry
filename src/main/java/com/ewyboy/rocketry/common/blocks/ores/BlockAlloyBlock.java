package com.ewyboy.rocketry.common.blocks.ores;

import com.ewyboy.rocketry.common.blocks.BlockBase;
import com.ewyboy.rocketry.common.blocks.Blocks;
import com.ewyboy.rocketry.common.enums.EnumAlloys;
import com.ewyboy.rocketry.common.enums.EnumOreType;
import com.ewyboy.rocketry.common.loaders.CreativeTabLoader;
import com.ewyboy.rocketry.common.utility.interfaces.IProvideRecipe;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.List;

public class BlockAlloyBlock extends BlockBase implements IProvideRecipe {

    public static final PropertyEnum<EnumAlloys> MATERIAL = PropertyEnum.create("material", EnumAlloys.class);

    public BlockAlloyBlock() {
        super(Material.ROCK, "ores/oreBlock");
        this.setDefaultState(this.blockState.getBaseState().withProperty(MATERIAL, EnumAlloys.byMeta(0)));
        this.setCreativeTab(CreativeTabLoader.Rocketry);
        this.setInternalName("alloy_block");
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(MATERIAL, EnumAlloys.byMeta(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return (state.getValue(MATERIAL)).getMeta();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, MATERIAL);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (int i = 0; i < EnumAlloys.values().length; i++) {
            if (EnumAlloys.byMeta(i).isTypeSet(EnumOreType.BLOCK)) {
                list.add(new ItemStack(itemIn, 1, i));
            }
        }
    }

    @Override
    public void RegisterRecipes() {
        for (EnumAlloys ore : EnumAlloys.values()) {
            if (ore.isTypeSet(EnumOreType.BLOCK) && ore.isTypeSet(EnumOreType.INGOT)) {
                GameRegistry.addRecipe(new ShapedOreRecipe(Blocks.BLOCK_ALLOY_BLOCK.getStack(1, ore.getMeta()),
                        "xxx",
                        "xxx",
                        "xxx",
                        'x', "ingot" + ore.getAlloyName())
                );
            }
        }
    }
}