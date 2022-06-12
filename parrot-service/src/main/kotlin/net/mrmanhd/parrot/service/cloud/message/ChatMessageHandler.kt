package net.mrmanhd.parrot.service.cloud.message

import net.mrmanhd.parrot.lib.Parrot

/**
 * Created by MrManHD
 * Class create at 12.06.2022 20:02
 */

class ChatMessageHandler {

    private val repository by lazy { Parrot.instance.chatMessageRepository }

    fun loadChatMessages() {
        val config = ChatMessageLoader().loadConfig()
        config.messages.forEach { (key, value) -> this.repository.insert(key, value) }
    }

    fun updateChatMessages() {
        this.repository.clear()
        loadChatMessages()
    }

}