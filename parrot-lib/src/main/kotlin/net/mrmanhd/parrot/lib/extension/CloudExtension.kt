package net.mrmanhd.parrot.lib.extension

import eu.thesimplecloud.api.player.ICloudPlayer
import net.mrmanhd.parrot.lib.Parrot

/**
 * Created by MrManHD
 * Class create at 12.06.2022 20:12
 */

fun ICloudPlayer.sendChatMessage(key: String, vararg strings: String) {
    val message = Parrot.instance.chatMessageService.getChatMessageByKey(key, *strings)
    this.sendMessage(message)
}