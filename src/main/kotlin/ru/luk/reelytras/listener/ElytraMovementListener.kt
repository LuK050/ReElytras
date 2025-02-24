package ru.luk.reelytras.listener
import ru.luk.reelytras.ReElytras

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityToggleGlideEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerRiptideEvent

class ElytraFireworkListener : Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    fun onElytraToggleGlide(event: EntityToggleGlideEvent) {
        if (event.isGliding && (!ReElytras.allowElytras
                    || !ReElytras.allowElytrasWhenRaining && event.entity.isInRain)) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onElytraMovement(event: PlayerMoveEvent) {
        if (!ReElytras.allowElytrasWhenRaining && event.player.isInRain && event.player.isGliding) {
            event.player.isGliding = false
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    fun onElytraFireworkBoost(event: PlayerInteractEvent) {
        if (!ReElytras.allowFireworkBoost && event.player.isGliding && event.action.isRightClick
            && event.item?.type == Material.FIREWORK_ROCKET) {
            event.isCancelled = true
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onElytraRiptideBoost(event: PlayerRiptideEvent) {
        val player = event.player
        if (!ReElytras.allowRiptideBoost &&
            (player.isGliding
                    || (player.location.add(0.0, -1.0, 0.0).block.type == Material.AIR
                    && player.inventory.chestplate?.type == Material.ELYTRA))) {
            event.player.teleport(event.player)
        }
    }
}