package net.mrmanhd.parrot.service.cloud.message.config

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
            Pair("connector.failed.service.null", "§cEs gab gerade ein Problem beim verbinden des Servers! (#{0})"),
            Pair("connector.failed.round.ends", "§cDieses Spiel ist bereits beendet!"),
            Pair("connector.failed.round.full", "§cDieses Spiel ist bereits voll!"),
            Pair("connector.failed.round.connected", "§cDu bist bereits in diesem Spiel!")
        )
    }

}