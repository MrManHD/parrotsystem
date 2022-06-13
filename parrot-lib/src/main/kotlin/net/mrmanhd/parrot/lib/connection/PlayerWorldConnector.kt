package net.mrmanhd.parrot.lib.connection

import eu.thesimplecloud.api.player.ICloudPlayer
import eu.thesimplecloud.plugin.extension.syncBukkit
import net.mrmanhd.parrot.lib.api.service.ParrotService
import net.mrmanhd.parrot.lib.extension.asLocation
import net.mrmanhd.parrot.lib.extension.sendChatMessage
import org.bukkit.Bukkit

/**
 * Created by MrManHD
 * Class create at 13.06.2022 13:51
 */

class PlayerWorldConnector(
    private val cloudPlayer: ICloudPlayer,
    private val parrotService: ParrotService,
    private val connectedParrotService: ParrotService?
) : AbstractDefaultPlayerConnector(
    parrotService
) {

    fun handle() {
        removeOldConnection()

        val player = Bukkit.getPlayer(this.cloudPlayer.getUniqueId())
        if (player == null) {
            cloudPlayer.sendToLobby()
            cloudPlayer.sendChatMessage("connector.failed.service.null", "PWC-27")
            return
        }

        if (this.connectedParrotService != null) {
            syncBukkit { player.teleport(this.parrotService.getSpawnLocation().asLocation()) }
        }

        handlePlayerAdd(player)
        syncBukkit { updatePlayerVisible(player) }
    }

    private fun removeOldConnection() {
        if (this.connectedParrotService != null) {
            this.connectedParrotService.removeGamePlayer(this.cloudPlayer.getUniqueId())
        }
    }

}