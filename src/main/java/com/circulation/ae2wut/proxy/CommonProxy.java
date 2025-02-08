package com.circulation.ae2wut.proxy;

import appeng.api.AEApi;
import appeng.api.config.Upgrades;
import com.circulation.ae2wut.handler.WirelessUniversalTerminalHandler;
import com.circulation.ae2wut.item.ItemWirelessUniversalTerminal;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

@SuppressWarnings("MethodMayBeStatic")
public class CommonProxy {

    public CommonProxy() {
    }

    public void construction() {

    }

    public void preInit() {
        MinecraftForge.EVENT_BUS.register(WirelessUniversalTerminalHandler.INSTANCE);
    }

    public void init() {
        AEApi.instance().registries().wireless().registerWirelessHandler(ItemWirelessUniversalTerminal.INSTANCE);
    }

    public void postInit() {
        Upgrades.MAGNET.registerItem(new ItemStack(ItemWirelessUniversalTerminal.INSTANCE), 1);
    }
}
