package com.nateplays.my_neoforge_mod.item.weapons;

import net.minecraft.core.component.DataComponents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.neoforged.neoforge.common.ItemAbilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DualBladesItem extends HuntingWeaponItem {
    static final Logger LOGGER = LoggerFactory.getLogger(DualBladesItem.class);

    //TODO: change this to be stored in the weapon
    public static boolean lastSwingWithMainHand = false;



    public DualBladesItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    public static ItemAttributeModifiers createAttributes(Tier tier) {
        return HuntingWeaponItem.createAttributes(tier, -1.3f);
    }

    public static float getAttackDamageMultiplier() {
        return 1.9F;
    }


//    @Override
//    public boolean canPerformAction(ItemStack stack, net.neoforged.neoforge.common.ItemAbility itemAbility) {
//        return ItemAbilities.DEFAULT_SWORD_ACTIONS.contains(itemAbility);
//    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        if (entity instanceof Player player) {
            RandomSource randomSource = RandomSource.create();
            if (randomSource.nextFloat() > 0.5) {
                forceServerSwing(player, InteractionHand.OFF_HAND);
                return true;
            }
        }
        return super.onEntitySwing(stack, entity);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return slotChanged;
    }
}
