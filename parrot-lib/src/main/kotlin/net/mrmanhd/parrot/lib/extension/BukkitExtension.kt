package net.mrmanhd.parrot.lib.extension

import net.mrmanhd.parrot.lib.Parrot
import org.bukkit.entity.Player

/**
 * Created by MrManHD
 * Class create at 12.06.2022 20:12
 */

fun Player.sendChatMessage(key: String, vararg strings: String) {
    val message = Parrot.instance.chatMessageService.getChatMessageByKey(key, *strings)
    this.sendMessage(message)
}