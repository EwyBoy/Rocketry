package com.ewyboy.rocketry.common.items;

import com.ewyboy.rocketry.common.items.ores.*;
import com.ewyboy.rocketry.common.utility.helpers.RegistrationHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public enum Items {

    ITEM_ORE_INGOT(ItemOreIngot.class),
    ITEM_ALLOY_INGOT(ItemAlloyIngot.class),
    ITEM_ORE_DUST(ItemOreDust.class),
    ITEM_ALLOY_DUST(ItemAlloyDust.class),
    ITEM_ORE_NUGGET(ItemOreNugget.class),
    ITEM_ALLOY_NUGGET(ItemAlloyNugget.class),
    ITEM_ORE_PLATE(ItemOrePlate.class),
    ITEM_ALLOY_PLATE(ItemAlloyPlate.class);

 /*   ITEM_MATERIAL_GEARCORE(ItemGearCore.class),
    ITEM_MATERIAL_GEAR(ItemGear.class);*/

    private final Class<? extends Item> itemClass;
    private Item item;

    Items(Class<? extends Item> itemClass) {
        this.itemClass = itemClass;
    }

    public static void registerItems() {
        for (Items i : Items.values()) {
            i.registerItem();
        }
    }

    public ItemStack getStack() {
        return new ItemStack(item);
    }

    public ItemStack getStack(int size) {
        return new ItemStack(item, size);
    }

    public ItemStack getStack(int size, int damage) {
        return new ItemStack(item, size, damage);
    }

    public Item getItem() {
        return this.item;
    }

    private void registerItem() {
        item = RegistrationHelper.registerItem(itemClass);
    }
}