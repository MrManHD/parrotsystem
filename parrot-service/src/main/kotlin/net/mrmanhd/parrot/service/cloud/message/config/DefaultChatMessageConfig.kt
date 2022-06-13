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
            Pair("connector.failed.round.connected", "§cDu bist bereits in diesem Spiel!"),
            Pair("connector.succsess", "Spieler {0}/{1} wurde mit ParrotService {2}/{3} verbinden"),
            Pair("service.start.success", "ParrotService {0}/{1} ist nun online auf {2}"),
            Pair("service.start.failed.daemons.offline", "Ein ParrotService konnte nicht gestartet werden, weil keine Services online sind!"),
            Pair("service.start.failed.disable.starting", "Ein ParrotService konnte nicht gestartet werden, weil der Start deaktiviert ist!"),
            Pair("service.daemon.ready.use", "Service {0} ist jetzt einsatzbereit")
        )
    }

    private fun getEnglishMessages(): Map<String, String> {
        return hashMapOf(
            Pair("connector.failed.service.null", "§cThere was just a problem connecting the server! (#{0})"),
            Pair("connector.failed.round.ends", "§cThis game is already finished!"),
            Pair("connector.failed.round.full", "§cThis game is already full!"),
            Pair("connector.failed.round.connected", "§cYou are already in this game!"),
            Pair("connector.succsess", "Connect player {0}/{1} to ParrotService {2}/{3}"),
            Pair("service.start.success", "ParrotService {0}/{1} is now online on {2}"),
            Pair("service.start.failed.daemons.offline", "A ParrotService could not be started because no services are online!"),
            Pair("service.start.failed.disable.starting", "A ParrotService could not be started because the start is deactivated!"),
            Pair("service.daemon.ready.use", "Service {0} is now ready to use")
        )
    }

}