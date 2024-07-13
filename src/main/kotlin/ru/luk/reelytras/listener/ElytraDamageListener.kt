package ru.luk.reelytras.listener
import ru.luk.reelytras.ReElytras

import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemDamageEvent

class ElytraDamageListener : Listener {
    @EventHandler
    fun onElytraDamage(event: PlayerItemDamageEvent) {
        val chestplateItem = event.player.inventory.chestplate ?: return

        if (chestplateItem.type == Material.ELYTRA) {
            event.damage = ReElytras.damagePerSecond

            if (chestplateItem.type.maxDurability.toInt() - chestplateItem.durability <= 2) {
                if (ReElytras.permanentDestroy) {
                    event.player.inventory.chestplate = null
                }

                if (ReElytras.playDestroySound) {
                    event.player.world.playSound(event.player.location, Sound.ENTITY_ITEM_BREAK, 1f, 1f)
                }
            }
        }
    }
}