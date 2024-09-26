package com.nateplays.my_neoforge_mod.tags;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_MACHALITE_TOOL = createTag("needs_machalite_tool");
        public static final TagKey<Block> INCORRECT_FOR_MACHALITE_TOOL = createTag("incorrect_for_machalite_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, name));
        }
    }

    public static class Enchantments {


        private static TagKey<Enchantment> createTag(String name) {
            return TagKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(MyNeoForgeMod.MODID, name));
        }
    }
}
