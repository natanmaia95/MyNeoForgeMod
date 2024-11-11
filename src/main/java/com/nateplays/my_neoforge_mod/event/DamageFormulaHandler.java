package com.nateplays.my_neoforge_mod.event;

import com.mojang.logging.LogUtils;
import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.attribute.ModAttributes;
import com.nateplays.my_neoforge_mod.item.weapons.HuntingWeaponItem;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;

@EventBusSubscriber(modid = MyNeoForgeMod.MODID)
public class DamageFormulaHandler {

    @SubscribeEvent
    public static void livingDamageEvent(LivingIncomingDamageEvent event) {

        DamageSource damageSource = event.getSource();
        damageFormulaRawStep(event, damageSource);
//        damageFormulaMotionValueStep(event);
        damageFormulaElementalStep(event);


        damageFormulaDefenseStep(event);
    }

    public static boolean damageFormulaRawStep(LivingIncomingDamageEvent event, DamageSource damageSource) {
        ItemStack weaponStack = damageSource.getWeaponItem();
        if (weaponStack != null && weaponStack.getItem() instanceof HuntingWeaponItem) {
            HuntingWeaponItem weaponItem = (HuntingWeaponItem) weaponStack.getItem();




            //finish by applying the "motion value", the actual strength of the swing
            float mvMultiplier = weaponItem.getAttackDamageMultiplier();
            event.setAmount(event.getAmount() * mvMultiplier);
            LogUtils.getLogger().debug("multiplier:" + String.valueOf(mvMultiplier));
        }

        return true;
    }

    public static boolean damageFormulaElementalStep(LivingIncomingDamageEvent event) {
        DamageSource damageSource = event.getSource();
        ItemStack weaponStack = damageSource.getWeaponItem();
        Entity entity = damageSource.getEntity();
        if (weaponStack != null && weaponStack.getItem() instanceof HuntingWeaponItem weaponItem) {
            double finalElementalDamage = 0.0;

            if (entity instanceof LivingEntity livingEntity) {
                double elementalDamage = livingEntity.getAttributeValue(ModAttributes.FIRE_DAMAGE);

                if (livingEntity instanceof Player player) { //half elem damage if spamming attack
                    if (player.getAttackStrengthScale(0) <= 0.8f) elementalDamage *= 0.5;
                }
                //check crits, etc
                finalElementalDamage += elementalDamage;
            }

            event.setAmount(event.getAmount() + (float) finalElementalDamage);
        }

        return true;
    }

    public static boolean damageFormulaDefenseStep(LivingIncomingDamageEvent event) {
        LivingEntity livingEntity = event.getEntity();
        float damageAmount = event.getAmount();
        if (!livingEntity.getAttributes().hasAttribute(ModAttributes.DEFENSE)) return true;
        double defense = livingEntity.getAttributeValue(ModAttributes.DEFENSE);
        if (defense == 0) return true;
        double defenseReduction = 8.0 / (8.0 + defense);
        double damageAfterDefense = damageAmount * defenseReduction;

//        event.addReductionModifier(DamageContainer.Reduction.ARMOR);

        event.setAmount((float)damageAfterDefense);
        return true;
    }


    @SubscribeEvent
    public static void criticalHitEvent(CriticalHitEvent event) {
        //cancel vanilla crit events
        if (event.isVanillaCritical()) {
            event.setDamageMultiplier(1.0f);
            event.setCriticalHit(false);
        }
        //roll affinity
//        event.setDamageMultiplier(1.25f);
//        if (event.getTarget().getRandom().nextFloat() <= 0.5) {
//            event.setCriticalHit(true);
//        }
    }
}
