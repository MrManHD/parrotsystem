package net.mrmanhd.parrot.service.plugin

import dev.triumphteam.cmd.bukkit.BukkitCommandManager
import net.mrmanhd.parrot.lib.Parrot
import net.mrmanhd.parrot.service.ParrotServiceCore
import net.mrmanhd.parrot.service.plugin.command.TestCommand
import org.bukkit.plugin.java.JavaPlugin

/**
 * Created by MrManHD
 * Class create at 10.06.2022 21:40
 */

class SpigotPlugin : JavaPlugin() {

    override fun onEnable() {
        ParrotServiceCore()
        Parrot.instance.hazelcastClientHandler.startConnection()

        registerEvents()
    }

    override fun onDisable() {
        ParrotServiceCore.instance.shutdown()
    }

    private fun registerEvents() {
        val commandManager = BukkitCommandManager.create(this)
        commandManager.registerCommand(TestCommand())
    }

}