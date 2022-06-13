package net.mrmanhd.parrot.daemon.plugin

import eu.thesimplecloud.plugin.startup.CloudPlugin
import net.mrmanhd.parrot.daemon.ParrotDaemon
import net.mrmanhd.parrot.lib.extension.sendCloudMessage
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

/**
 * Created by MrManHD
 * Class create at 13.06.2022 12:48
 */

class SpigotPlugin : JavaPlugin() {

    override fun onEnable() {
        ParrotDaemon()

        object : BukkitRunnable() {
            override fun run() {
                val thisService = CloudPlugin.instance.thisService()
                thisService.setProperty("service-finish", true)
                sendCloudMessage("service.daemon.ready.use", thisService.getName())
            }
        }.runTaskLater(this, 40)
    }

    override fun onDisable() {

    }

}