package net.mrmanhd.parrot.service.service

import net.mrmanhd.parrot.service.ParrotServiceCore
import org.bukkit.plugin.java.JavaPlugin

/**
 * Created by MrManHD
 * Class create at 10.06.2022 21:40
 */

class SpigotPlugin : JavaPlugin() {

    override fun onEnable() {
        val parrotServiceCore = ParrotServiceCore()
        parrotServiceCore.hazelcastClientHandler.startConnection()
    }

    override fun onDisable() {
        ParrotServiceCore.instance.shutdown()
    }

}