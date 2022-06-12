package net.mrmanhd.parrot.lib.messagechannel.listener

import eu.thesimplecloud.api.message.IMessageListener
import eu.thesimplecloud.api.network.component.INetworkComponent
import eu.thesimplecloud.api.player.ICloudPlayer
import net.mrmanhd.parrot.api.service.IParrotService
import net.mrmanhd.parrot.lib.extension.sendChatMessage
import net.mrmanhd.parrot.lib.messagechannel.dto.PlayerConnectorDTO

/**
 * Created by MrManHD
 * Class create at 12.06.2022 21:01
 */

class PlayerConnectorListener : IMessageListener<PlayerConnectorDTO> {

    override fun messageReceived(msg: PlayerConnectorDTO, sender: INetworkComponent) {
        val parrotService = msg.getParrotService()
        msg.getCloudPlayer().addResultListener { handleCloudPlayerConnection(it, parrotService, msg.playerAlreadyOnSameService) }
    }

    private fun handleCloudPlayerConnection(
        cloudPlayer: ICloudPlayer,
        parrotService: IParrotService?,
        playerAlreadyOnSameDaemon: Boolean
    ) {
        if (parrotService == null) {
            cloudPlayer.sendToLobby()
            cloudPlayer.sendChatMessage("connector.failed.service.null", "PCL-25")
        }
    }

}