package ru.luk.reelytras
import ru.luk.reelytras.command.PluginCommand
import ru.luk.reelytras.listener.ElytraAnvilListener
import ru.luk.reelytras.listener.ElytraFireworkListener
import ru.luk.reelytras.listener.ElytraDamageListener
import ru.luk.reelytras.listener.ElytraUnificationListener

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

import java.io.File
import kotlin.properties.Delegates

class ReElytras : JavaPlugin() {
    companion object {
        lateinit var plugin: ReElytras
        var allowFireworkBoost by Delegates.notNull<Boolean>()
        var allowRiptideBoost by Delegates.notNull<Boolean>()
        var allowEnchantments by Delegates.notNull<Boolean>()
        var allowRepairWithPhantomMembrane by Delegates.notNull<Boolean>()
        var allowUnificationRepairInAnvil by Delegates.notNull<Boolean>()
        var allowUnificationRepairInCraft by Delegates.notNull<Boolean>()
        var allowUnificationRepairInGrindstone by Delegates.notNull<Boolean>()
        var playDestroySound by Delegates.notNull<Boolean>()
        var permanentDestroy by Delegates.notNull<Boolean>()
        var repairWithPhantomMembranePercent by Delegates.notNull<Int>()
        var damagePerSecond by Delegates.notNull<Int>()
    }

    override fun onEnable() {
        plugin = this

        File(dataFolder.toString() + File.separator + "config.yml").apply {
            if (!exists()) {
                config.options().copyDefaults(true)
                saveDefaultConfig()
            }
        }

        allowFireworkBoost = config.getBoolean("allowFireworkBoost")
        allowRiptideBoost = config.getBoolean("allowRiptideBoost")
        allowEnchantments = config.getBoolean("allowEnchantments")
        allowRepairWithPhantomMembrane = config.getBoolean("allowRepairWithPhantomMembrane")
        allowUnificationRepairInAnvil = config.getBoolean("allowUnificationRepairInAnvil")
        allowUnificationRepairInCraft = config.getBoolean("allowUnificationRepairInCraft")
        allowUnificationRepairInGrindstone = config.getBoolean("allowUnificationRepairInGrindstone")
        playDestroySound = config.getBoolean("playDestroySound")
        permanentDestroy = config.getBoolean("permanentDestroy")
        repairWithPhantomMembranePercent = config.getInt("repairWithPhantomMembranePercent")
        damagePerSecond = config.getInt("damagePerSecond")

        Bukkit.getPluginManager().registerEvents(ElytraAnvilListener(), this)
        Bukkit.getPluginManager().registerEvents(ElytraFireworkListener(), this)
        Bukkit.getPluginManager().registerEvents(ElytraDamageListener(), this)
        Bukkit.getPluginManager().registerEvents(ElytraUnificationListener(), this)

        getCommand("reelytras")?.setExecutor(PluginCommand())
    }
}