package com.circulation.ae2wut.mixin.ae2exttable;

import com._0xc4de.ae2exttable.client.gui.AE2ExtendedGUIs;
import com._0xc4de.ae2exttable.items.ItemRegistry;
import com.circulation.ae2wut.item.ItemWirelessUniversalTerminal;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemRegistry.class,remap = false)
public class MixinItemRegistry {
    @Inject(method = "guiByItem", at = @At(value = "HEAD"), cancellable = true)
    private static void onGuiByItem(Item item, CallbackInfoReturnable<AE2ExtendedGUIs> cir){
        if (item == ItemWirelessUniversalTerminal.INSTANCE){
            cir.setReturnValue(AE2ExtendedGUIs.WIRELESS_ULTIMATE_CRAFTING_TERMINAL);
        }
    }
}
