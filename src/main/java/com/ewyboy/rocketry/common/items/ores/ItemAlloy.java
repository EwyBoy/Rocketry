package com.ewyboy.rocketry.common.items.ores;

import com.ewyboy.rocketry.common.enums.EnumAlloys;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemAlloy extends ItemBlock {

    public ItemAlloy(Block block) {
        super(block);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        String name = super.getUnlocalizedName();
        String materialName = EnumAlloys.byMeta(stack.getItemDamage()).getUnlocalizedName();
        return name + "." + materialName;
    }
}