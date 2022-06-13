package net.mrmanhd.parrot.lib.connection

import eu.thesimplecloud.api.player.ICloudPlayer
import net.mrmanhd.parrot.lib.api.service.ParrotService
import net.mrmanhd.parrot.lib.extension.asLocation
import net.mrmanhd.parrot.lib.extension.sendChatMessage
import net.mrmanhd.parrot.lib.listener.advancedListen
import org.bukkit.entity.Player
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerJoinEvent
import org.spigotmc.event.player.PlayerSpawnLocationEvent

/**
 * Created by MrManHD
 * Class create at 13.06.2022 13:41
 */

class PlayerServerConnector(
    private val cloudPlayer: ICloudPlayer,
    private val parrotService: ParrotService
) : AbstractDefaultPlayerConnector(
    parrotService
) {

    fun handle() {
        val cloudService = this.parrotService.getCloudService()
        if (cloudService == null) {
            this.cloudPlayer.sendToLobby()
            this.cloudPlayer.sendChatMessage("connector.failed.service.null", "PSC-23")
            return
        }
        this.cloudPlayer.connect(cloudService)
        handlePlayerConnectSuccess()
    }

    private fun handlePlayerConnectSuccess() {
        advancedListen<PlayerJoinEvent>(EventPriority.LOWEST)
            .addCondition { it.player.uniqueId == this.cloudPlayer.getUniqueId() }
            .addAction { it.joinMessage(null) }
            .addAction { handleJoinEvent(it.player) }
            .unregisterAfterCall()

        advancedListen<PlayerSpawnLocationEvent>(EventPriority.LOWEST)
            .addCondition { it.player.uniqueId == this.cloudPlayer.getUniqueId() }
            .addAction { it.spawnLocation = this.parrotService.getSpawnLocation().asLocation() }
            .unregisterAfterCall()
    }

    private fun handleJoinEvent(player: Player) {
        handlePlayerAdd(player)
        updatePlayerVisible(player)

        this.parrotService.removePreConnectedPlayer(this.cloudPlayer.getUniqueId())
    }

}