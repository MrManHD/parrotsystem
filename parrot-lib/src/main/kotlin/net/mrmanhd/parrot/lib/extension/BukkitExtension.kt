package net.mrmanhd.parrot.lib.extension

import net.mrmanhd.parrot.api.utils.ParrotLocation
import net.mrmanhd.parrot.lib.Parrot
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * Created by MrManHD
 * Class create at 12.06.2022 20:12
 */

fun Player.sendChatMessage(key: String, vararg strings: Any) {
    val prefix = Parrot.instance.configRepository.getConfig().prefix
    val message = Parrot.instance.chatMessageService.getChatMessageByKey(key, *strings)
    this.sendMessage(prefix + message)
}

fun CommandSender.sendChatMessage(key: String, vararg strings: Any) {
    val prefix = Parrot.instance.configRepository.getConfig().prefix
    val message = Parrot.instance.chatMessageService.getChatMessageByKey(key, *strings)
    this.sendMessage(prefix + message)
}

fun ParrotLocation.asLocation(): Location {
    return Location(
        Bukkit.getWorld(this.worldName)!!,
        this.x,
        this.y,
        this.z,
        this.yaw,
        this.pitch
    )
}

fun debugMessage(key: String, vararg strings: Any) {
    Bukkit.getOnlinePlayers().filter { it.hasPermission("*") }
        .forEach { it.sendChatMessage(key, *strings) }
}