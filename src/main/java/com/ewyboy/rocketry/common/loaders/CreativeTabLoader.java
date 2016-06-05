package com.ewyboy.rocketry.common.loaders;

import com.ewyboy.rocketry.common.utility.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.ModInfo.ModID)
public class CreativeTabLoader {

    public static CreativeTabs Rocketry = new CreativeTabs(Reference.ModInfo.ModName) {
        public ItemStack getIconItemStack(){return new ItemStack(Items.FIREWORKS);}
        @Override
        public Item getTabIconItem() {return null;}
    };
}
