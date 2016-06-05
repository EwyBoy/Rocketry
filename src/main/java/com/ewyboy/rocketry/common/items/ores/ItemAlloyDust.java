package com.ewyboy.rocketry.common.items.ores;

import com.ewyboy.rocketry.common.enums.EnumAlloys;
import com.ewyboy.rocketry.common.enums.EnumOreType;
import com.ewyboy.rocketry.common.items.ItemBase;
import com.ewyboy.rocketry.common.loaders.CreativeTabLoader;
import com.ewyboy.rocketry.common.utility.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

import java.util.List;

public class ItemAlloyDust extends ItemBase {

    public ItemAlloyDust() {
        super("ores/dust");
        this.setHasSubtypes(true);
        this.setCreativeTab(CreativeTabLoader.Rocketry);
        this.setInternalName("alloy_dust");
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i = 0; i < EnumAlloys.values().length; i++) {
            if (EnumAlloys.byMeta(i).isTypeSet(EnumOreType.DUST)) {
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
            if (EnumAlloys.byMeta(i).isTypeSet(EnumOreType.DUST)) {
                ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(Reference.ModInfo.ModID + ":ores/dust-" + EnumAlloys.byMeta(i).getUnlocalizedName(), "inventory"));
            }
        }
    }
}