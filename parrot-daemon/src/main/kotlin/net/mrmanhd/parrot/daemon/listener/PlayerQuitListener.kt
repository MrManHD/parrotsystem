package net.mrmanhd.parrot.daemon.listener

import net.mrmanhd.parrot.api.extension.getParrotService
import net.mrmanhd.parrot.lib.api.service.ParrotService
import net.mrmanhd.parrot.lib.extension.debugMessage
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

/**
 * Created by MrManHD
 * Class create at 13.06.2022 19:17
 */

class PlayerQuitListener : Listener {

    @EventHandler
    fun handleQuit(event: PlayerQuitEvent) {
        val player = event.player
        val parrotService = player.getParrotService() as ParrotService? ?: return

        parrotService.removePreConnectedPlayer(player.uniqueId)
        parrotService.removeGamePlayer(player.uniqueId)

        debugMessage("debug.gameplayer.delete", player.name, parrotService.getName())

        event.quitMessage(null)
    }

}