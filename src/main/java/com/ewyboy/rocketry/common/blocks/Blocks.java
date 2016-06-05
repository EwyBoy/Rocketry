package com.ewyboy.rocketry.common.blocks;

import com.ewyboy.rocketry.common.blocks.ores.BlockAlloy;
import com.ewyboy.rocketry.common.blocks.ores.BlockAlloyBlock;
import com.ewyboy.rocketry.common.blocks.ores.BlockOre;
import com.ewyboy.rocketry.common.blocks.ores.BlockOreBlock;
import com.ewyboy.rocketry.common.items.ores.ItemAlloy;
import com.ewyboy.rocketry.common.items.ores.ItemAlloyBlock;
import com.ewyboy.rocketry.common.items.ores.ItemOre;
import com.ewyboy.rocketry.common.items.ores.ItemOreBlock;
import com.ewyboy.rocketry.common.utility.helpers.RegistrationHelper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public enum Blocks {
    BLOCK_ORE(BlockOre.class, ItemOre.class),
    BLOCK_ALLOY(BlockAlloy.class, ItemAlloy.class),
    BLOCK_ORE_BLOCK(BlockOreBlock.class, ItemOreBlock.class),
    BLOCK_ALLOY_BLOCK(BlockAlloyBlock.class, ItemAlloyBlock.class),
    ;

    private final Class<? extends BlockBase> blockClass;
    private final Class<? extends ItemBlock> itemBlockClass;
    private Block block;

    Blocks(Class<? extends BlockBase> blockClass) {
        this(blockClass, ItemBlock.class);
    }

    Blocks(Class<? extends BlockBase> blockClass, Class<? extends ItemBlock> itemBlockClass) {
        this.blockClass = blockClass;
        this.itemBlockClass = itemBlockClass;
    }

    public static void registerBlocks() {
        for (Blocks block : Blocks.values()) {
            block.registerBlock();
        }
    }

    public ItemStack getStack() {
        return new ItemStack(block);
    }

    public ItemStack getStack(int size) {
        return new ItemStack(block, size);
    }

    public ItemStack getStack(int size, int meta) {
        return new ItemStack(block, size, meta);
    }

    public Block getBlock() {
        return this.block;
    }

    private void registerBlock() {
        block = RegistrationHelper.registerBlock(blockClass, itemBlockClass);
    }
}