package com.leclowndu93150.potiondescriptions;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = PotionDescriptionsMain.MODID, bus = EventBusSubscriber.Bus.GAME,value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onRenderTooltip(ItemTooltipEvent event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        ItemStack stack = event.getItemStack();
        if(stack.getItem() instanceof PotionItem) {
            if(Screen.hasShiftDown()){
                PotionContents potionContents = stack.get(DataComponents.POTION_CONTENTS);
                ResourceLocation potion = ResourceLocation.parse(potionContents.potion().get().getRegisteredName());
                String potionEffect = potion.getPath();
                String potionNameSpace = potion.getNamespace();
                if(potionEffect.contains("long_")) {
                    potionEffect = potionEffect.replace("long_", "");
                }
                event.getToolTip().add(Component.translatable("effect"+"."+potionNameSpace+"."+potionEffect+"."+"description").withStyle(ChatFormatting.GRAY));
            }else {
                event.getToolTip().add(Component.literal("Press ").withStyle(ChatFormatting.GRAY).append(Component.literal("Shift").withStyle(ChatFormatting.YELLOW)).append(Component.literal(" for potion description").withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.ITALIC));
            }
        }
    }
}
