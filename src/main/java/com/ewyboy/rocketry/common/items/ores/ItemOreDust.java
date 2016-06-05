package com.ewyboy.rocketry.common.items.ores;

import com.ewyboy.rocketry.common.enums.EnumOreType;
import com.ewyboy.rocketry.common.enums.EnumOres;
import com.ewyboy.rocketry.common.items.ItemBase;
import com.ewyboy.rocketry.common.loaders.CreativeTabLoader;
import com.ewyboy.rocketry.common.utility.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

import java.util.List;

public class ItemOreDust extends ItemBase {

    public ItemOreDust() {
        super("ores/dust");
        this.setHasSubtypes(true);
        this.setCreativeTab(CreativeTabLoader.Rocketry);
        this.setInternalName("ore_dust");
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i = 0; i < EnumOres.values().length; i++) {
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.DUST)) {
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
        String oreName = EnumOres.byMeta(stack.getItemDamage()).getUnlocalizedName();
        return name + "." + oreName;
    }

    @Override
    public void registerItemRenderer() {
        for (int i = 0; i < EnumOres.values().length; i++) {
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.DUST)) {
                ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(Reference.ModInfo.ModID + ":ores/dust-" + EnumOres.byMeta(i).getUnlocalizedName(), "inventory"));
            }
        }
    }
}