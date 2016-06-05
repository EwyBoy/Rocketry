package com.ewyboy.rocketry.common.items.ores;

import com.ewyboy.rocketry.common.blocks.Blocks;
import com.ewyboy.rocketry.common.enums.EnumAlloys;
import com.ewyboy.rocketry.common.enums.EnumOreType;
import com.ewyboy.rocketry.common.items.ItemBase;
import com.ewyboy.rocketry.common.items.Items;
import com.ewyboy.rocketry.common.loaders.CreativeTabLoader;
import com.ewyboy.rocketry.common.utility.Reference;
import com.ewyboy.rocketry.common.utility.interfaces.IProvideRecipe;
import com.ewyboy.rocketry.common.utility.interfaces.IProvideSmelting;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.List;

public class ItemAlloyIngot extends ItemBase implements IProvideRecipe, IProvideSmelting {
    public ItemAlloyIngot() {
        super("ores/ingot");
        this.setHasSubtypes(true);
        this.setCreativeTab(CreativeTabLoader.Rocketry);
        this.setInternalName("alloy_ingot");
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i = 0; i < EnumAlloys.values().length; i++) {
            if (EnumAlloys.byMeta(i).isTypeSet(EnumOreType.INGOT)) {
                subItems.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        String name = super.getUnlocalizedName();
        String oreName = EnumAlloys.byMeta(stack.getItemDamage()).getUnlocalizedName();
        return name + "." + oreName;
    }

    @Override
    public void registerItemRenderer() {
        for (int i = 0; i < EnumAlloys.values().length; i++) {
            if (EnumAlloys.byMeta(i).isTypeSet(EnumOreType.INGOT)) {
                ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(Reference.ModInfo.ModID + ":ores/ingot-" + EnumAlloys.byMeta(i).getUnlocalizedName(), "inventory"));
            }
        }
    }

    @Override
    public void RegisterRecipes() {
        for (EnumAlloys ore : EnumAlloys.values()) {
            // Block -> 9x Ingots
            if (ore.isTypeSet(EnumOreType.INGOT) && ore.isTypeSet(EnumOreType.BLOCK)) {
                GameRegistry.addRecipe(new ShapelessOreRecipe(Items.ITEM_ALLOY_INGOT.getStack(9, ore.getMeta()), "block" + ore.getAlloyName()));
            }

            // 9x Nuggets -> Ingot
            if (ore.isTypeSet(EnumOreType.INGOT) && ore.isTypeSet(EnumOreType.NUGGET)) {
                GameRegistry.addRecipe(new ShapedOreRecipe(Items.ITEM_ALLOY_INGOT.getStack(1, ore.getMeta()),
                        "xxx",
                        "xxx",
                        "xxx",
                        'x', "nugget" + ore.getAlloyName())
                );
            }
        }
    }

    @Override
    public void RegisterSmelting() {
        for (int i = 0; i < EnumAlloys.values().length; i++) {
            // Register Ore -> Ingot
            if (EnumAlloys.byMeta(i).isTypeSet(EnumOreType.ORE) && EnumAlloys.byMeta(i).isTypeSet(EnumOreType.INGOT))
                GameRegistry.addSmelting(Blocks.BLOCK_ALLOY.getStack(1, i), Items.ITEM_ALLOY_INGOT.getStack(1, i), 0);

            // Register Dust -> Ingot
            if (EnumAlloys.byMeta(i).isTypeSet(EnumOreType.DUST) && EnumAlloys.byMeta(i).isTypeSet(EnumOreType.INGOT))
                GameRegistry.addSmelting(Items.ITEM_ALLOY_DUST.getStack(1, i), Items.ITEM_ALLOY_INGOT.getStack(1, i), 0);
        }
    }
}