package ru.luk.reelytras.listener
import ru.luk.reelytras.ReElytras

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.PrepareAnvilEvent
import org.bukkit.event.inventory.PrepareItemCraftEvent
import org.bukkit.event.inventory.PrepareGrindstoneEvent

class ElytraUnificationListener : Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    fun onElytraAnvilUnification(event: PrepareAnvilEvent) {
        if (!ReElytras.allowUnificationRepairInAnvil
            && (event.inventory.firstItem ?: return).type == Material.ELYTRA
            && (event.inventory.secondItem ?: return).type == Material.ELYTRA) {
            event.result = null
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    fun onElytraCraftUnification(event: PrepareItemCraftEvent) {
        if (!ReElytras.allowUnificationRepairInCraft && (event.inventory.result ?: return).type == Material.ELYTRA) {
            event.inventory.result = null
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    fun onElytraGrindstoneUnification(event: PrepareGrindstoneEvent) {
        if (!ReElytras.allowUnificationRepairInGrindstone
            && (event.inventory.upperItem ?: return).type == Material.ELYTRA
            && (event.inventory.lowerItem ?: return).type == Material.ELYTRA) {
            event.result = null
        }
    }
}