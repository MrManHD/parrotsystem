package net.mrmanhd.parrot.service.plugin.command

import dev.triumphteam.cmd.bukkit.annotation.Permission
import dev.triumphteam.cmd.core.BaseCommand
import dev.triumphteam.cmd.core.annotation.Command
import dev.triumphteam.cmd.core.annotation.Default
import dev.triumphteam.cmd.core.annotation.SubCommand
import dev.triumphteam.cmd.core.annotation.Suggestion
import eu.thesimplecloud.api.CloudAPI
import eu.thesimplecloud.api.player.text.CloudText
import eu.thesimplecloud.plugin.extension.getCloudPlayer
import net.kyori.adventure.text.Component
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.api.extension.connectToService
import net.mrmanhd.parrot.api.service.IParrotService
import net.mrmanhd.parrot.lib.Parrot
import net.mrmanhd.parrot.lib.extension.hasServiceLoaded
import net.mrmanhd.parrot.lib.extension.sendChatMessage
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

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
        sender.sendMessage("§8-§7 /parrot join <ParrotService>")
        sender.sendMessage("§8-§7 /parrot send <ParrotService> <Playername>")
        sender.sendMessage("§8-§7 /parrot list")
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

        CloudAPI.instance.getCloudPlayerManager().getCloudPlayer(playerName)
            .addResultListener {
                it.sendChatMessage("ingame.command.parrot.send.from.player", sender.name, parrotName)
                sender.sendChatMessage("ingame.command.parrot.send.success", it.getName(), parrotName)
                it.connectToService(parrotService)
            }
            .addFailureListener { sender.sendChatMessage("ingame.command.parrot.send.failed.playerName") }
    }

    @SubCommand("join")
    @Permission("parrot.command.join")
    fun executeJoin(sender: CommandSender, @Suggestion("parrotServices") parrotName: String) {
        sender as Player

        val parrotService = ParrotApi.instance.getServiceHandler().getServiceByName(parrotName)
        if (parrotService == null) {
            sender.sendChatMessage("ingame.command.parrot.join.failed.parrotService")
            return
        }

        sender.sendChatMessage("ingame.command.parrot.join.success", parrotName)
        sender.connectToService(parrotService)
    }

    @SubCommand("list")
    @Permission("parrot.command.list")
    fun executeList(sender: CommandSender) {
        sender as Player

        val services = Parrot.instance.configRepository.getConfig()
            .getStartGroups().map { it.getAllServices() }.flatten().filter { it.hasServiceLoaded() }
        if (services.isEmpty()) {
            sender.sendChatMessage("ingame.command.list.failed.daemons")
            return
        }

        services.forEach { service ->
            val list = ParrotApi.instance.getServiceHandler().getAllServicesByCloudService(service)
            sender.sendMessage("")
            sender.sendMessage(Component.text("§8›§7 ${service.getName()}§8: (§a${service.getUsedMemory()}MB §8┃§c ${list.size} Parrots §8┃§f ${service.getOnlineCount()} Spieler§8)"))

            val cloudPlayer = sender.getCloudPlayer()
            list.sortedBy { it.getName() }.forEach {
                val cloudText = CloudText("§8●§f §8(§e${it.getOnlineCount()}§8/§e${it.getMaxPlayers()} §8┃§a ${it.getState()}§8)")
                cloudText.addHover(getHoverListMessage(it))
                cloudPlayer.sendMessage(cloudText)
            }
        }
    }

    private fun getHoverListMessage(parrotService: IParrotService): String {
        val stringBuilder = StringBuilder(
            "§7Private Runde §8» ${
                parrotService.isPrivate().toString()
                    .replace("true", "§aJa").replace("false", "§cNein")
            }"
        )
            .append("\n§7Gruppe §8»§a ${parrotService.getGroupName()}")

        parrotService.getOwner()?.let {
            val name = CloudAPI.instance.getCloudPlayerManager().getOfflineCloudPlayer(it).get().getName()
            stringBuilder.append("\n§7Owner §8»§c $name")
        }

        stringBuilder.append("\nMotd §8»§e ${parrotService.getMotd()}")

        parrotService.getVariant()?.let { stringBuilder.append("\n§7Variant §8»§b $it") }

        val simpleDateFormat = SimpleDateFormat("dd.MM.yy HH:mm:ss")
        stringBuilder.append("\n\n§7Erstellt am §f${simpleDateFormat.format(Date(parrotService.createdAt()))}")

        return stringBuilder.toString()
    }

}