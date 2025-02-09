package com.circulation.ae2wut.recipes;

import appeng.api.AEApi;
import appeng.api.definitions.IDefinitions;
import appeng.api.definitions.IItems;
import appeng.util.Platform;
import com.circulation.ae2wut.AE2UELWirelessUniversalTerminal;
import com.circulation.ae2wut.item.ItemWirelessUniversalTerminal;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import com._0xc4de.ae2exttable.items.ItemRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.*;

import static com.circulation.ae2wut.item.ItemWirelessUniversalTerminal.*;
import static com.glodblock.github.loader.FCItems.WIRELESS_FLUID_PATTERN_TERMINAL;
import static com.mekeng.github.common.ItemAndBlocks.WIRELESS_GAS_TERMINAL;

public class AllWUTRecipe {

    static IDefinitions appEngApi = AEApi.instance().definitions();
    static IItems AEItems = appEngApi.items();
    static ItemStack ItemWireless = new ItemStack(ItemWirelessUniversalTerminal.INSTANCE);

    private static NBTTagCompound getNBT() {
        NBTTagCompound nbt = Platform.openNbtData(ItemWireless);
        nbt.setIntArray("modes", getAllMode());
        return nbt;
    }

    public static final Map<Integer,ItemStack> itemList = getIngredient();

    private static Map<Integer,ItemStack> getIngredient() {
        Map<Integer,ItemStack> map = new HashMap<>();
        map.put(1,AEItems.wirelessCraftingTerminal().maybeStack(1).get());
        map.put(2,AEItems.wirelessFluidTerminal().maybeStack(1).get());
        map.put(3,AEItems.wirelessPatternTerminal().maybeStack(1).get());

        if (Loader.isModLoaded("ae2fc")) {
            map.put(4,new ItemStack(WIRELESS_FLUID_PATTERN_TERMINAL));
        }

        if (Loader.isModLoaded("mekeng")) {
            map.put(5,new ItemStack(WIRELESS_GAS_TERMINAL));
        }

        if (Loader.isModLoaded("ae2exttable")) {
            map.put(6, new ItemStack(ItemRegistry.WIRELESS_BASIC_TERMINAL));
            map.put(7, new ItemStack(ItemRegistry.WIRELESS_ADVANCED_TERMINAL));
            map.put(8, new ItemStack(ItemRegistry.WIRELESS_ELITE_TERMINAL));
            map.put(9, new ItemStack(ItemRegistry.WIRELESS_ULTIMATE_TERMINAL));
        }

        return map;
    }

    public static void reciperRegister(){
        List<Ingredient> inputs = new ArrayList<>();
        for (ItemStack item : itemList.values()){
            inputs.add(Ingredient.fromStacks(item));
        }
        GameRegistry.addShapedRecipe(
                new ResourceLocation(AE2UELWirelessUniversalTerminal.MOD_ID, NAME),
                null,
                ItemWireless,
                "ABC",
                'A', appEngApi.materials().wirelessReceiver().maybeStack(1).get(),
                'B', appEngApi.parts().terminal().maybeStack(1).get(),
                'C', appEngApi.blocks().energyCellDense().maybeStack(1).get()
        );
        GameRegistry.addShapelessRecipe(
                new ResourceLocation(AE2UELWirelessUniversalTerminal.MOD_ID, NAME + 1),
                null,
                ItemWireless,
                Ingredient.fromItem(AEItems.wirelessTerminal().maybeItem().get())
        );
        ItemStack ItemWirelessALL = ItemWireless.copy();
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setIntArray("modes",getAllMode());
        ItemWirelessALL.setTagCompound(nbt);
        GameRegistry.addShapelessRecipe(
                new ResourceLocation(AE2UELWirelessUniversalTerminal.MOD_ID, NAME + "all"),
                null,
                ItemWirelessALL,
                inputs.toArray(new Ingredient[0])
        );
    }
}
