package ru.luk.reelytras.listener
import ru.luk.reelytras.ReElytras

import org.bukkit.Material
import org.bukkit.event.Listener
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.inventory.PrepareAnvilEvent

import kotlin.math.ceil

class ElytraAnvilListener : Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    fun onElytraAnvil(event: PrepareAnvilEvent) {
        val firstItem = event.inventory.firstItem ?: return
        val secondItem = event.inventory.secondItem ?: return

        if (firstItem.type != Material.ELYTRA) {
            return
        }

        if (!ReElytras.allowEnchantments && secondItem.type == Material.ENCHANTED_BOOK
            || !ReElytras.allowRepairWithPhantomMembrane && firstItem.isRepairableBy(secondItem)) {
            event.result = null
        }

        if (firstItem.isRepairableBy(secondItem) && firstItem.durability > 0) {
            val repairedElytra = firstItem.clone()
            val membraneCost = firstItem.type.maxDurability * ReElytras.repairWithPhantomMembranePercent / 100
            var currentRepairCostAmount = ceil(repairedElytra.durability.toDouble() / membraneCost).toInt()

            if (currentRepairCostAmount > secondItem.amount) {
                currentRepairCostAmount = secondItem.amount
            }

            event.inventory.repairCostAmount = currentRepairCostAmount
            repairedElytra.durability = (repairedElytra.durability - membraneCost * currentRepairCostAmount).toShort()
            event.result = repairedElytra
        }
    }
}