package ru.luk.reelytras.command
import ru.luk.reelytras.ReElytras

import net.md_5.bungee.api.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class PluginCommand : CommandExecutor, TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        if (args.isEmpty())
            return false

        when (args[0]) {
            "reload" -> {
                ReElytras.plugin.reloadConfig()
                sender.sendMessage(ChatColor.GREEN.toString() + "Plugin successfully reloaded!")
            }

            "info" -> {
                sender.sendMessage("${ChatColor.GRAY}Author ${ChatColor.DARK_GRAY}> ${ChatColor.WHITE}lluk\n${ChatColor.GRAY}Version ${ChatColor.DARK_GRAY}> ${ChatColor.WHITE}1.1")
            }
        }
        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, s: String, args: Array<String>): MutableList<String>? {
        if (sender is Player) {
            return if (args.size == 1) mutableListOf("info", "reload") else mutableListOf()
        }
        return null
    }
}