package net.mrmanhd.parrot.service.cloud.config

/**
 * Created by MrManHD
 * Class create at 12.06.2022 20:06
 */

object DefaultChatMessageConfig {

    fun get(): ChatMessageConfig {
        return ChatMessageConfig(
            getMessages()
        )
    }

    private fun getMessages(): Map<String, String> {
        return hashMapOf(
            Pair("test.test", "Hallo {0}")
        )
    }

}