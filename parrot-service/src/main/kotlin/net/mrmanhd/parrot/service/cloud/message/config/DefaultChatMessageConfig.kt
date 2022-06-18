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
            Pair("connector.success", "Spieler {0}/{1} wurde mit ParrotService {2}/{3} verbinden"),
            Pair("service.start.success", "ParrotService {0}/{1} ist nun online auf {2}"),
            Pair("service.stop", "ParrotService {0}/{1} wird auf {2} heruntergefahren"),
            Pair("service.start.failed.daemons.offline", "Ein ParrotService konnte nicht gestartet werden, weil keine Services online sind!"),
            Pair("service.start.failed.disable.starting", "Ein ParrotService konnte nicht gestartet werden, weil der Start deaktiviert ist!"),
            Pair("service.daemon.ready.use", "Service {0} ist jetzt einsatzbereit"),
            Pair("service.daemon.start.new.service", "[Parrot] ParrotService {0}/{1} wurde gestartet!"),
            Pair("service.daemon.stop.service", "[Parrot] ParrotService {0}/{1} wurde gestoppt!"),
            Pair("service.world.load.failed.parrotService", "Welt {0} kann in {1} nicht geladen werden! Ist ParrotService null?"),
            Pair("command.setup.create.group.success", "ParrotGroup {0} was created successfully!"),
            Pair("command.reload.groups", "Lade alle ParrotGroups neu!"),
            Pair("command.reload.messages", "Lade alle Nachrichten neu!"),
            Pair("command.reload.config", "Lade Config neu!"),
            Pair("command.start.failed.group", "Die ParrotGroup mit dem Namen {0} existiert nicht!"),
            Pair("command.start.failed.cloudService", "Der Service mit dem Namen {0} existiert nicht!"),
            Pair("command.start.success", "Der ParrotService {0} wird nun gestartet"),
            Pair("command.stop.failed.service", "Der ParrotService mit dem Namen {0} existiert nicht!"),
            Pair("command.stop.success", "Der ParrotService {0} wird nun gestoppt"),
            Pair("command.stopgroup.failed.group", "Die ParrotGroup mit dem Namen {0} existiert nicht!"),
            Pair("command.stopgroup.success", "Alle ParrotServices von der ParrotGroup {0} werden nun gestoppt"),
            Pair("debug.gameplayer.create", "GamePlayer §6{0}§7 wird erstellt für §6{1}"),
            Pair("debug.gameplayer.delete", "GamePlayer §6{0}§7 wird von §6{1}§7 gelöscht"),
            Pair("debug.gameplayer.delete.failed", "GamePlayer §6{0}§7 konnte nicht gelöscht werden"),
            Pair("debug.gameplayer.update", "GamePlayer §6{0}§7 wurde aktualisiert"),
            Pair("debug.daemon.start.new.service", "Ein neuer ParrotService wird estellt §6{0}"),
            Pair("debug.daemon.stop.service", "Der ParrotService §6{0}§7 wird gestoppt"),
            Pair("debug.command.update", "Die Nachrichten wurden aktualisiert"),
            Pair("debug.world.template.load", "Welttemplate §6{0}§7 wird nun geladen"),
            Pair("debug.world.template.failed.import", "§cWelttemplate {0} ist bereits geladen!"),
            Pair("debug.world.load.template", "SlimeWorld wird von Template §6{0}§7 geladen für §6{1}"),
            Pair("debug.service.world.load", "Die Welt §6{0}§7 wird für §6{1}§7 geladen"),
            Pair("debug.service.world.load.success", "Die Welt §6{0}§7 wurde geladen für §6{1}§7 ({2})"),
            Pair("debug.service.world.unload", "Die Welt §6{0}§7 wird für §6{1}§7 gelöscht")
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
            Pair("connector.success", "Connect player {0}/{1} to ParrotService {2}/{3}"),
            Pair("service.start.success", "ParrotService {0}/{1} is now online on {2}"),
            Pair("service.stop", "ParrotService {0}/{1} shutting down on {2}"),
            Pair("service.start.failed.daemons.offline", "A ParrotService could not be started because no services are online!"),
            Pair("service.start.failed.disable.starting", "A ParrotService could not be started because the start is deactivated!"),
            Pair("service.daemon.ready.use", "Service {0} is now ready to use"),
            Pair("service.daemon.start.new.service", "[Parrot] ParrotService {0}/{1} is started!"),
            Pair("service.daemon.stop.service", "[Parrot] ParrotService {0}/{1} is now offline!"),
            Pair("service.world.load.failed.parrotService", "Cannot load World {0} in {1}! Is ParrotService null?"),
            Pair("command.setup.create.group.success", "ParrotGroup {0} was created successfully!"),
            Pair("command.reload.groups", "Reloading all ParrotGroups!"),
            Pair("command.reload.messages", "Reloading all messages!"),
            Pair("command.reload.config", "Reloading Config!"),
            Pair("command.start.failed.group", "The ParrotGroup with name {0} does not exist!"),
            Pair("command.start.failed.cloudService", "The service with name {0} does not exist!"),
            Pair("command.start.success", "The ParrotService {0} is now started"),
            Pair("command.stop.failed.service", "The ParrotService with name {0} does not exist!"),
            Pair("command.stop.success", "The ParrotService {0} is now offline"),
            Pair("command.stopgroup.failed.group", "The ParrotGroup with name {0} does not exist!"),
            Pair("command.stopgroup.success", "All ParrotServices of ParrotGroup {0} is now offline"),
            Pair("debug.gameplayer.create", "GamePlayer §6{0}§7 is created for §6{1}"),
            Pair("debug.gameplayer.delete", "GamePlayer §6{0}§7 is deleted from §6{1}"),
            Pair("debug.gameplayer.delete.failed", "GamePlayer §6{0}§7 could not be deleted"),
            Pair("debug.gameplayer.update", "GamePlayer §6{0}§7 was updated"),
            Pair("debug.daemon.start.new.service", "A new ParrotService is created §6{0}"),
            Pair("debug.daemon.stop.service", "The ParrotService §6{0}§7 is stopped"),
            Pair("debug.command.update", "The news has been updated"),
            Pair("debug.world.template.load", "Worldtemplate §6{0}§7 is now loaded"),
            Pair("debug.world.template.failed.import", "§cWorldtemplate {0} is already loaded!"),
            Pair("debug.world.load.template", "SlimeWorld is loaded from template §6{0}§7 for §6{1}"),
            Pair("debug.service.world.load", "The world §6{0}§7 is loaded for §6{1}§7"),
            Pair("debug.service.world.load.success", "The world §6{0}§7 was loaded for §6{1}§7 ({2})"),
            Pair("debug.service.world.unload", "The world §6{0}§7 is deleted for §6{1}")
        )
    }

}