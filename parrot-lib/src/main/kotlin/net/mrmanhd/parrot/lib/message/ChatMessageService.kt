package net.mrmanhd.parrot.lib.message

import net.mrmanhd.parrot.lib.Parrot
import java.text.MessageFormat

/**
 * Created by MrManHD
 * Class create at 12.06.2022 20:11
 */

class ChatMessageService {

    fun getChatMessageByKey(key: String, vararg strings: String): String {
        val message = Parrot.instance.chatMessageRepository.find(key)
            ?: "Die Nachricht für den Key $key existiert nicht!"
        return MessageFormat.format(message, *strings)
    }

}