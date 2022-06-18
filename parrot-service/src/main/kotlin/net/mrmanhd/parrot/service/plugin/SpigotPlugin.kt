package net.mrmanhd.parrot.service.plugin

import dev.triumphteam.cmd.bukkit.BukkitCommandManager
import dev.triumphteam.cmd.core.message.MessageKey
import dev.triumphteam.cmd.core.suggestion.SuggestionKey
import dev.triumphteam.cmd.core.suggestion.SuggestionResolver
import eu.thesimplecloud.api.CloudAPI
import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.lib.Parrot
import net.mrmanhd.parrot.service.ParrotServiceCore
import net.mrmanhd.parrot.service.plugin.command.ParrotCommand
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

        commandManager.registerSuggestion(SuggestionKey.of("parrotServices")) { _, _ ->
            ParrotApi.instance.getServiceHandler().getAllServices().map { it.getName() }
        }
        commandManager.registerSuggestion(SuggestionKey.of("playerNames")) { _, _ ->
            CloudAPI.instance.getCloudPlayerManager().getAllOnlinePlayers().get().map { it.getName() }
        }

        commandManager.registerCommand(TestCommand())
        commandManager.registerCommand(ParrotCommand())

        commandManager.registerMessage(MessageKey.NOT_ENOUGH_ARGUMENTS) { sender, context ->
            when (context.subCommand) {
                "send" -> sender.sendMessage("§8-§7 /parrot send <ParrotService> <Playername>")
            }
        }
    }

}