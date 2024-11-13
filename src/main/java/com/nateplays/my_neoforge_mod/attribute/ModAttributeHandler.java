package com.nateplays.my_neoforge_mod.attribute;

import com.nateplays.my_neoforge_mod.MyNeoForgeMod;
import com.nateplays.my_neoforge_mod.entity.ModEntities;
import com.nateplays.my_neoforge_mod.entity.custom.MosswineEntity;
import com.nateplays.my_neoforge_mod.entity.pets.Felyne;
import com.nateplays.my_neoforge_mod.entity.pets.PalicoEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MyNeoForgeMod.MODID)
public class ModAttributeHandler {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.MOSSWINE.get(), MosswineEntity.createAttributes().build());
//        event.put(ModEntities.PALICO.get(), MosswineEntity.createAttributes().build());
        event.put(ModEntities.FELYNE.get(), PalicoEntity.createAttributes().build());
    }

    public static AttributeSupplier.Builder createPlayerHuntAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(ModAttributes.EATING_SPEED, ModAttributes.EATING_SPEED.get().getDefaultValue())
                .add(ModAttributes.DEFENSE, ModAttributes.DEFENSE.get().getDefaultValue());

    }

    @SubscribeEvent
    public static void entityAttributeModificationEvent(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, ModAttributes.DEFENSE);
//        BuiltInRegistries.ENTITY_TYPE.forEach((entityType) -> {
//            event.add((EntityType<? extends LivingEntity>) entityType, ModAttributes.DEFENSE);
//        });

        event.add(EntityType.PLAYER, ModAttributes.FIRE_DAMAGE);
//        event.add(EntityType., ModAttributes.DEFENSE);

        event.add(EntityType.PLAYER, ModAttributes.EATING_SPEED);
    }
}
