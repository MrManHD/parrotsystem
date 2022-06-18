package net.mrmanhd.parrot.service.plugin.command

import dev.triumphteam.cmd.bukkit.annotation.Permission
import dev.triumphteam.cmd.core.BaseCommand
import dev.triumphteam.cmd.core.annotation.Command
import dev.triumphteam.cmd.core.annotation.Default
import dev.triumphteam.cmd.core.annotation.SubCommand
import dev.triumphteam.cmd.core.annotation.Suggestion
import eu.thesimplecloud.api.CloudAPI
import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.api.extension.connectToService
import net.mrmanhd.parrot.lib.extension.sendChatMessage
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * Created by MrManHD
 * Class create at 18.06.2022 19:35
 */

@Command("parrot")
class ParrotCommand : BaseCommand() {

    @Default
    @Permission("parrot.command.default")
    fun execute(sender: CommandSender) {
        sender as Player
        sender.sendChatMessage("ingame.command.parrot.info")
        sender.sendMessage("§8-§7 /parrot send <ParrotService> <Playername>")
    }

    @SubCommand("send")
    @Permission("parrot.command.send")
    fun executeSend(
        sender: CommandSender,
        @Suggestion("parrotServices") parrotName: String,
        @Suggestion("playerNames") playerName: String
    ) {
        sender as Player

        val parrotService = ParrotApi.instance.getServiceHandler().getServiceByName(parrotName)
        if (parrotService == null) {
            sender.sendChatMessage("ingame.command.parrot.send.failed.parrotService")
            return
        }

        if (sender.name.equals(playerName, true)) {
            sender.sendChatMessage("ingame.command.parrot.send.success.self", parrotName)
            sender.connectToService(parrotService)
            return
        }

        CloudAPI.instance.getCloudPlayerManager().getCloudPlayer(playerName)
            .addResultListener {
                it.sendChatMessage("ingame.command.parrot.send.from.player", sender.name, parrotName)
                sender.sendChatMessage("ingame.command.parrot.send.success", it.getName(), parrotName)
                it.connectToService(parrotService)
            }
            .addFailureListener { sender.sendChatMessage("ingame.command.parrot.send.failed.playerName") }
1
    }

}