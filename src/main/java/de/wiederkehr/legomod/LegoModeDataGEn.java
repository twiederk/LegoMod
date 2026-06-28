package de.wiederkehr.legomod;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = LegoMod.MOD_ID)
public class LegoModeDataGEn {

    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {

    }
}
