package net.mrmanhd.parrot.daemon.plugin

import dev.triumphteam.cmd.bukkit.BukkitCommandManager
import eu.thesimplecloud.plugin.startup.CloudPlugin
import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.daemon.ParrotDaemon
import net.mrmanhd.parrot.daemon.command.DebugParrotCommand
import net.mrmanhd.parrot.daemon.command.GotoWorldCommand
import net.mrmanhd.parrot.daemon.listener.PlayerQuitListener
import net.mrmanhd.parrot.lib.Parrot
import net.mrmanhd.parrot.lib.api.service.ParrotService
import net.mrmanhd.parrot.lib.extension.sendCloudMessage
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

/**
 * Created by MrManHD
 * Class create at 13.06.2022 12:48
 */

class SpigotPlugin : JavaPlugin() {

    override fun onEnable() {
        ParrotDaemon(this)
        registerEvents()

        object : BukkitRunnable() {
            override fun run() {
                val thisService = CloudPlugin.instance.thisService()
                thisService.setProperty("service-finish", true)
                sendCloudMessage("service.daemon.ready.use", thisService.getName())
            }
        }.runTaskLater(this, 40)
    }

    override fun onDisable() {
        val serviceHandler = ParrotApi.instance.getServiceHandler()
        serviceHandler.getAllServicesByCloudService(CloudPlugin.instance.thisService())
            .map { it as ParrotService }.forEach { it.shutdown() }
    }

    private fun registerEvents() {
        val pluginManager = Bukkit.getPluginManager()
        pluginManager.registerEvents(PlayerQuitListener(), this)

        val commandManager = BukkitCommandManager.create(this)
        commandManager.registerCommand(DebugParrotCommand())
        commandManager.registerCommand(GotoWorldCommand())
    }

}