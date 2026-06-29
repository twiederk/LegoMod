package de.wiederkehr.legomod.creativemodtab;

import de.wiederkehr.legomod.LegoMod;
import de.wiederkehr.legomod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LegoMod.MOD_ID);

    public static final Supplier<CreativeModeTab> AZURITE_ITEMS_TAB = CREATIVE_MODE_TABS.register("azurite_items_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.AZURITE.get()))
                    .title(Component.translatable("creativetab.legomod.azurite_items"))
                    .withTabsBefore(CreativeModeTabs.INGREDIENTS)
                    .withTabsAfter(Identifier.fromNamespaceAndPath(LegoMod.MOD_ID, "azurite_blocks_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.AZURITE.get());
                        output.accept(ModItems.RAW_AZURITE.get());
                    })
                    .build());

    public static final Supplier<CreativeModeTab> AZURITE_BLOCKS_TAB = CREATIVE_MODE_TABS.register("azurite_blocks_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.RAW_AZURITE.get()))
                    .title(Component.translatable("creativetab.legomod.azurite_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.AZURITE.get());
                        output.accept(ModItems.RAW_AZURITE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
