package net.mrmanhd.parrot.lib.messagechannel

import eu.thesimplecloud.api.CloudAPI
import net.mrmanhd.parrot.lib.messagechannel.dto.CloudMessageDTO
import net.mrmanhd.parrot.lib.messagechannel.listener.CloudMessageListener

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
    }

    fun registerListeners() {
        this.messageChannelManager.getMessageChannelByName<CloudMessageDTO>("parrot-cloud-message")
            ?.registerListener(CloudMessageListener())
    }

    fun unregisterChannels() {
        this.messageChannelManager.unregisterMessageChannel("parrot-cloud-message")
    }

}