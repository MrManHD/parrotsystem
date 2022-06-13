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
            Pair("connector.failed.player.doesnt.exist", "[Parrot] Verbindung zwischen Spieler {0} und ParrotService {1} ist nicht möglich!"),
            Pair("connector.failed.player.already.connected", "[Parrot] Spieler {0}/{1} ist bereits verbunden mit dem ParrotService {2}"),
            Pair("connector.starting", "[Parrot] Verbinde Spieler {0}/{1} mit ParrotService {2}/{3}"),
            Pair("connector.succsess", "Spieler {0}/{1} wurde mit ParrotService {2}/{3} verbinden"),
            Pair("service.start.success", "ParrotService {0}/{1} ist nun online auf {2}"),
            Pair("service.start.failed.daemons.offline", "Ein ParrotService konnte nicht gestartet werden, weil keine Services online sind!"),
            Pair("service.start.failed.disable.starting", "Ein ParrotService konnte nicht gestartet werden, weil der Start deaktiviert ist!"),
            Pair("service.daemon.ready.use", "Service {0} ist jetzt einsatzbereit"),
            Pair("command.setup.create.group.succsess", "ParrotGroup {0} was created successfully!"),
            Pair("command.reload.groups", "Lade alle ParrotGroups neu!"),
            Pair("command.reload.messages", "Lade alle Nachrichten neu!"),
            Pair("command.reload.config", "Lade Config neu!"),
        )
    }

    private fun getEnglishMessages(): Map<String, String> {
        return hashMapOf(
            Pair("connector.failed.service.null", "§cThere was just a problem connecting the server! (#{0})"),
            Pair("connector.failed.round.ends", "§cThis game is already finished!"),
            Pair("connector.failed.round.full", "§cThis game is already full!"),
            Pair("connector.failed.round.connected", "§cYou are already in this game!"),
            Pair("connector.failed.player.doesnt.exist", "[Parrot] Cannot connect player {0} to ParrotService {1}"),
            Pair("connector.failed.player.already.connected", "[Parrot] Player {0}/{1} is already in ParrotService {2}"),
            Pair("connector.starting", "[Parrot] Connecting player {0}/{1} to ParrotService {2}/{3}"),
            Pair("connector.succsess", "Connect player {0}/{1} to ParrotService {2}/{3}"),
            Pair("service.start.success", "ParrotService {0}/{1} is now online on {2}"),
            Pair("service.start.failed.daemons.offline", "A ParrotService could not be started because no services are online!"),
            Pair("service.start.failed.disable.starting", "A ParrotService could not be started because the start is deactivated!"),
            Pair("service.daemon.ready.use", "Service {0} is now ready to use"),
            Pair("command.setup.create.group.succsess", "ParrotGroup {0} was created successfully!"),
            Pair("command.reload.groups", "Reloading all ParrotGroups!"),
            Pair("command.reload.messages", "Reloading all messages!"),
            Pair("command.reload.config", "Reloading Config!")
        )
    }

}