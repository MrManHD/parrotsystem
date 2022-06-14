package net.mrmanhd.parrot.service.plugin.command

import dev.triumphteam.cmd.core.BaseCommand
import dev.triumphteam.cmd.core.annotation.Command
import dev.triumphteam.cmd.core.annotation.Default
import dev.triumphteam.cmd.core.annotation.SubCommand
import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.api.extension.connectToService
import net.mrmanhd.parrot.api.utils.Variant
import net.mrmanhd.parrot.lib.extension.sendChatMessage
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

        val parrotGroup = ParrotApi.instance.getGroupHandler().getGroupByName("bedwars")!!
        parrotGroup.createService()
            .withVariant(Variant.fromString("2x5"))
            .withMaxPlayersVariant()
            .withProperty("spectator", true)
            .startService()
                .addResultListener { sender.sendMessage("server ${it.getName()} startet") }
                .addFailureListener { sender.sendMessage("failed: ${it.message}") }
    }

    @SubCommand("list")
    fun executeList(sender: CommandSender) {
        sender as Player

        sender.sendMessage("")
        ParrotApi.instance.getServiceHandler().getAllServices().forEach {
            sender.sendMessage(it.toJsonString())
            sender.sendMessage("")
        }
    }

    @SubCommand("groups")
    fun executeGroups(sender: CommandSender) {
        sender as Player

        ParrotApi.instance.getGroupHandler().getAllGroups().forEach {
            sender.sendMessage("> ${it.getName()}")
        }
    }

    @SubCommand("message")
    fun executeMessages(sender: CommandSender) {
        sender as Player
        sender.sendChatMessage("test.test", sender.name)
    }

    @SubCommand("send")
    fun executeSend(sender: CommandSender) {
        sender as Player
        sender.connectToService(ParrotApi.instance.getServiceHandler().getAllServices().random())
    }

}