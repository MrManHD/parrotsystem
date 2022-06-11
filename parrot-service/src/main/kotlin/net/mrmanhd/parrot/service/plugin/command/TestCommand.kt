package net.mrmanhd.parrot.service.plugin.command

import dev.triumphteam.cmd.core.BaseCommand
import dev.triumphteam.cmd.core.annotation.Command
import dev.triumphteam.cmd.core.annotation.Default
import dev.triumphteam.cmd.core.annotation.SubCommand
import eu.thesimplecloud.plugin.extension.getCloudPlayer
import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.lib.api.group.ParrotGroup
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * Created by MrManHD
 * Class create at 11.06.2022 15:53
 */

@Command("test")
class TestCommand : BaseCommand() {

    @Default
    fun execute(sender: CommandSender) {
        sender as Player

        val parrotGroup = ParrotGroup("bedwars", listOf(), 5, 10, false)
        ParrotApi.instance.getServiceHandler().createService(parrotGroup)
            .withMaxPlayers(200)
            .withCloudService(sender.getCloudPlayer().getConnectedServer()!!)
            .startService()
                .addResultListener { sender.sendMessage("server ${it.getName()} startet") }
                .addFailureListener { sender.sendMessage("failed: ${it.message}") }
    }

    @SubCommand("list")
    fun executeList(sender: CommandSender) {
        sender as Player

        ParrotApi.instance.getServiceHandler().getAllServices().forEach {
            sender.sendMessage("> ${it.getName()} / ${it.getGroup()} / ${it.getCloudServiceName()}")
        }
    }

}