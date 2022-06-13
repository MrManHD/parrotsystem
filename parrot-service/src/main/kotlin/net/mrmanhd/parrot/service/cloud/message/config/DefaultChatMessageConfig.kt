package net.mrmanhd.parrot.service.cloud.message.config

/**
 * Created by MrManHD
 * Class create at 12.06.2022 20:06
 */

object DefaultChatMessageConfig {

    fun getGermanMessage(): ChatMessage {
        return ChatMessage(
            "de",
            getGermanMessages()
        )
    }

    fun getEnglishMessage(): ChatMessage {
        return ChatMessage(
            "en",
            getEnglishMessages()
        )
    }

    private fun getGermanMessages(): Map<String, String> {
        return hashMapOf(
            Pair("connector.failed.service.null", "§cEs gab gerade ein Problem beim verbinden des Servers! (#{0})"),
            Pair("connector.failed.round.ends", "§cDieses Spiel ist bereits beendet!"),
            Pair("connector.failed.round.full", "§cDieses Spiel ist bereits voll!"),
            Pair("connector.failed.round.connected", "§cDu bist bereits in diesem Spiel!")
        )
    }

    private fun getEnglishMessages(): Map<String, String> {
        return hashMapOf(
            Pair("connector.failed.service.null", "§cThere was just a problem connecting the server! (#{0})"),
            Pair("connector.failed.round.ends", "§cThis game is already finished!"),
            Pair("connector.failed.round.full", "§cThis game is already full!"),
            Pair("connector.failed.round.connected", "§cYou are already in this game!")
        )
    }

}