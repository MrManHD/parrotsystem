package net.mrmanhd.parrot.daemon.command

import dev.triumphteam.cmd.bukkit.annotation.Permission
import dev.triumphteam.cmd.core.BaseCommand
import dev.triumphteam.cmd.core.annotation.ArgName
import dev.triumphteam.cmd.core.annotation.Command
import dev.triumphteam.cmd.core.annotation.Default
import net.mrmanhd.parrot.api.extension.getParrotService
import net.mrmanhd.parrot.daemon.ParrotDaemon
import net.mrmanhd.parrot.lib.extension.sendChatMessage
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

/**
 * Created by MrManHD
 * Class create at 18.06.2022 16:12
 */

@Command("gotoworld")
class GotoWorldCommand : BaseCommand() {

    @Default
    @Permission("parrot.command.gotoworld")
    fun execute(sender: CommandSender, worldName: String) {
        sender as Player

        val parrotService = sender.getParrotService()
        if (parrotService == null) {
            sender.sendChatMessage("ingame.command.gotoworld.failed.parrotService")
            return
        }

        parrotService.loadWorld(worldName)

        object : BukkitRunnable() {
            override fun run() {
                sender.teleport(Bukkit.getWorld("${parrotService.getUniqueId()}-$worldName")!!.spawnLocation)
            }
        }.runTaskLater(ParrotDaemon.instance.javaPlugin, 100)
    }

}