package net.mrmanhd.parrot.lib.messagechannel

import eu.thesimplecloud.api.CloudAPI
import net.mrmanhd.parrot.lib.messagechannel.dto.CloudMessageDTO
import net.mrmanhd.parrot.lib.messagechannel.dto.ParrotServiceStateDTO
import net.mrmanhd.parrot.lib.messagechannel.dto.PlayerConnectorDTO
import net.mrmanhd.parrot.lib.messagechannel.listener.CloudMessageListener
import net.mrmanhd.parrot.lib.messagechannel.listener.ParrotServiceStateListener
import net.mrmanhd.parrot.lib.messagechannel.listener.PlayerConnectorListener

/**
 * Created by MrManHD
 * Class create at 12.06.2022 13:47
 */

class MessageChannelRegistry {

    private val messageChannelManager = CloudAPI.instance.getMessageChannelManager()

    fun registerChannels() {
        val cloudModule = CloudAPI.instance.getThisSidesCloudModule()

        this.messageChannelManager.registerMessageChannel(
            cloudModule,
            "parrot-cloud-message",
            CloudMessageDTO::class.java
        )

        this.messageChannelManager.registerMessageChannel(
            cloudModule,
            "parrot-service-state",
            ParrotServiceStateDTO::class.java
        )

        this.messageChannelManager.registerMessageChannel(
            cloudModule,
            "parrot-player-connector",
            PlayerConnectorDTO::class.java
        )
    }

    fun registerListeners() {
        this.messageChannelManager.getMessageChannelByName<CloudMessageDTO>("parrot-cloud-message")
            ?.registerListener(CloudMessageListener())
        this.messageChannelManager.getMessageChannelByName<ParrotServiceStateDTO>("parrot-service-state")
            ?.registerListener(ParrotServiceStateListener())
        this.messageChannelManager.getMessageChannelByName<PlayerConnectorDTO>("parrot-player-connector")
            ?.registerListener(PlayerConnectorListener())
    }

    fun unregisterChannels() {
        this.messageChannelManager.unregisterMessageChannel("parrot-cloud-message")
        this.messageChannelManager.unregisterMessageChannel("parrot-service-state")
        this.messageChannelManager.unregisterMessageChannel("parrot-player-connector")
    }

}