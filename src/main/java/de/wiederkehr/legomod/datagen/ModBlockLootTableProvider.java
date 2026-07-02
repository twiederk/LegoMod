package de.wiederkehr.legomod.datagen;

import de.wiederkehr.legomod.block.ModBlocks;
import de.wiederkehr.legomod.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    public ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.AZURITE_BLOCK.get());
        dropSelf(ModBlocks.RAW_AZURITE_BLOCK.get());

        add(ModBlocks.AZURITE_ORE.get(),
                createOreDrop(ModBlocks.AZURITE_ORE.get(), ModItems.RAW_AZURITE.get()));
        add(ModBlocks.AZURITE_DEEPSLATE_ORE.get(),
                createOreDrop(ModBlocks.AZURITE_DEEPSLATE_ORE.get(), ModItems.RAW_AZURITE.get()));
        add(ModBlocks.AZURITE_NETHER_ORE.get(),
                createMultipleOreDrops(ModBlocks.AZURITE_NETHER_ORE.get(), ModItems.RAW_AZURITE.get(), 4, 7));
        add(ModBlocks.AZURITE_END_ORE.get(),
                createMultipleOreDrops(ModBlocks.AZURITE_END_ORE.get(), ModItems.RAW_AZURITE.get(), 5, 9));

    }

    protected LootTable.Builder createMultipleOreDrops(Block block, Item item, float min_drops, float max_drops) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(block, this.applyExplosionDecay(block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min_drops, max_drops)))
                                .apply(ApplyBonusCount.addOreBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }


    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
