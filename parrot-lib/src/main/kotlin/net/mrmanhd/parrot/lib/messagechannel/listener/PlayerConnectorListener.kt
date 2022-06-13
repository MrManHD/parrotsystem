package net.mrmanhd.parrot.lib.messagechannel.listener

import eu.thesimplecloud.api.message.IMessageListener
import eu.thesimplecloud.api.network.component.INetworkComponent
import eu.thesimplecloud.api.player.ICloudPlayer
import net.mrmanhd.parrot.api.service.IParrotService
import net.mrmanhd.parrot.lib.api.service.ParrotService
import net.mrmanhd.parrot.lib.connection.PlayerServerConnector
import net.mrmanhd.parrot.lib.connection.PlayerWorldConnector
import net.mrmanhd.parrot.lib.extension.sendChatMessage
import net.mrmanhd.parrot.lib.messagechannel.dto.PlayerConnectorDTO

/**
 * Created by MrManHD
 * Class create at 12.06.2022 21:01
 */

class PlayerConnectorListener : IMessageListener<PlayerConnectorDTO> {

    override fun messageReceived(msg: PlayerConnectorDTO, sender: INetworkComponent) {
        val parrotService = msg.getParrotService() as ParrotService?
        val connectedParrotService = msg.getConnectedParrotServiceByPlayer() as ParrotService?
        msg.getCloudPlayer()
            .addResultListener {
                handleCloudPlayerConnection(
                    it,
                    parrotService,
                    connectedParrotService,
                    msg.playerAlreadyOnSameService
                )
            }
    }

    private fun handleCloudPlayerConnection(
        cloudPlayer: ICloudPlayer,
        parrotService: ParrotService?,
        connectedParrotService: ParrotService?,
        playerAlreadyOnSameDaemon: Boolean
    ) {
        if (parrotService == null) {
            cloudPlayer.sendToLobby()
            cloudPlayer.sendChatMessage("connector.failed.service.null", "PCL-42")
            return
        }

        if (playerAlreadyOnSameDaemon) {
            PlayerWorldConnector(cloudPlayer, parrotService, connectedParrotService).handle()
        } else {
            PlayerServerConnector(cloudPlayer, parrotService).handle()
        }
    }

}