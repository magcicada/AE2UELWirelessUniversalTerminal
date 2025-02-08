package com.circulation.ae2wut.item;

import com.circulation.ae2wut.AE2UELWirelessUniversalTerminal;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class CreativeTab extends CreativeTabs {
    public static final CreativeTab INSTANCE = new CreativeTab();

    private CreativeTab() {
        super(AE2UELWirelessUniversalTerminal.MOD_ID);
    }

    @Nonnull
    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemWirelessUniversalTerminal.INSTANCE);
    }

}
