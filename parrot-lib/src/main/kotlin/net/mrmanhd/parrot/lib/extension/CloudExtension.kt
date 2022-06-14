package net.mrmanhd.parrot.lib.extension

import eu.thesimplecloud.api.command.ICommandSender
import eu.thesimplecloud.api.player.ICloudPlayer
import eu.thesimplecloud.api.service.ICloudService
import net.mrmanhd.parrot.lib.Parrot

/**
 * Created by MrManHD
 * Class create at 12.06.2022 20:12
 */

fun ICloudPlayer.sendChatMessage(key: String, vararg strings: String) {
    val prefix = Parrot.instance.configRepository.getConfig().prefix
    val message = Parrot.instance.chatMessageService.getChatMessageByKey(key, *strings)
    this.sendMessage(prefix + message)
}

fun ICommandSender.sendChatMessage(key: String, vararg strings: String) {
    val message = Parrot.instance.chatMessageService.getChatMessageByKey(key, *strings)
    this.sendMessage(message)
}

fun ICloudService.hasServiceLoaded(): Boolean {
    return this.getProperty<Boolean>("service-finish")?.getValue() ?: false
}