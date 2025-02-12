package com.circulation.ae2wut.mixin.ae2fcbaubles;

import baubles.api.BaublesApi;
import com.circulation.ae2wut.item.ItemWirelessUniversalTerminal;
import com.glodblock.github.inventory.GuiType;
import com.glodblock.github.network.CPacketUseKeybind;
import com.glodblock.github.util.Util;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mixin(value = CPacketUseKeybind.Handler.class, remap = false)
public class MixinCPacketUseKeybindHandler {

    @Inject(method="tryOpenBauble", at = @At(value= "HEAD"), cancellable = true)
    private static void tryOpenBaubleMixin(EntityPlayer player, CallbackInfo ci) {
        for (int i = 0; i < BaublesApi.getBaublesHandler(player).getSlots(); i++) {
            ItemStack stackInSlot = BaublesApi.getBaublesHandler(player).getStackInSlot(i);
            if (stackInSlot.getItem() == ItemWirelessUniversalTerminal.INSTANCE && stackInSlot.getTagCompound() != null) {
                List<Integer> list = null;
                if (stackInSlot.getTagCompound().hasKey("modes")) {
                    list = Arrays.stream(stackInSlot.getTagCompound().getIntArray("modes")).boxed().collect(Collectors.toList());
                }
                if (list != null && list.contains(4)) {
                    ItemWirelessUniversalTerminal.INSTANCE.nbtChangeB(stackInSlot);
                    ItemWirelessUniversalTerminal.INSTANCE.nbtChange(stackInSlot, 4);
                    Util.openWirelessTerminal(stackInSlot, i, true, player.world, player, GuiType.WIRELESS_FLUID_PATTERN_TERMINAL);
                    ci.cancel();
                    return;
                }
            }
        }
    }
}
