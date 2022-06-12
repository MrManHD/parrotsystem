package net.mrmanhd.parrot.lib.api.connector

import eu.thesimplecloud.api.CloudAPI
import eu.thesimplecloud.api.player.ICloudPlayer
import net.mrmanhd.parrot.api.connector.IPlayerConnectorService
import net.mrmanhd.parrot.api.service.IParrotService
import net.mrmanhd.parrot.lib.Parrot
import net.mrmanhd.parrot.lib.api.service.ParrotService
import net.mrmanhd.parrot.lib.extension.sendChatMessage
import net.mrmanhd.parrot.lib.extension.sendCloudMessage
import net.mrmanhd.parrot.lib.extension.writeMessage
import net.mrmanhd.parrot.lib.messagechannel.dto.PlayerConnectorDTO
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by MrManHD
 * Class create at 12.06.2022 19:41
 */

class PlayerConnectorService : IPlayerConnectorService {

    override fun connectPlayerToService(uniqueId: UUID, parrotService: IParrotService) {
        CloudAPI.instance.getCloudPlayerManager().getCloudPlayer(uniqueId)
            .addResultListener { handleCloudPlayerConnection(it, parrotService as ParrotService) }
            .addFailureListener { writeMessage("[Parrot] Cannot connect player $uniqueId to ParrotService ${parrotService.getName()}") }
    }

    private fun handleCloudPlayerConnection(cloudPlayer: ICloudPlayer, parrotService: ParrotService) {
        if (parrotService.isEnding()) {
            cloudPlayer.sendChatMessage("connector.failed.round.ends")
            return
        }

        if (parrotService.isFull()) {
            cloudPlayer.sendChatMessage("connector.failed.round.full")
            return
        }

        if (parrotService.isPlayerOnline(cloudPlayer.getUniqueId())) {
            cloudPlayer.sendChatMessage("connector.failed.round.connected")
            return
        }

        if (parrotService.isPlayerPreConnected(cloudPlayer.getUniqueId())) {
            return
        }

        parrotService.addPreConnectedPlayer(cloudPlayer.getUniqueId())
        Parrot.instance.scheduler.schedule({
            parrotService.removePreConnectedPlayer(cloudPlayer.getUniqueId())
        }, 3, TimeUnit.SECONDS)

        sendMessageChannelToService(cloudPlayer, parrotService)
        sendCloudMessage("Connect player ${cloudPlayer.getName()}/${cloudPlayer.getUniqueId()} to ParrotService ${parrotService.getName()}/${parrotService.getGroupName()}")
    }

    private fun sendMessageChannelToService(cloudPlayer: ICloudPlayer, parrotService: IParrotService) {
        val isPlayerAlreadyOnSameService = cloudPlayer.getConnectedServerName() == parrotService.getCloudServiceName()
        val dto = PlayerConnectorDTO(cloudPlayer.getUniqueId(), isPlayerAlreadyOnSameService, parrotService.getUniqueId())

        val cloudService = parrotService.getCloudService()
        if (cloudService == null) {
            cloudPlayer.sendChatMessage("connector.failed.service.null","PCS-64")
            return
        }

        CloudAPI.instance.getMessageChannelManager()
            .getMessageChannelByName<PlayerConnectorDTO>("parrot-player-connector")?.sendMessage(dto, cloudService)
    }

}