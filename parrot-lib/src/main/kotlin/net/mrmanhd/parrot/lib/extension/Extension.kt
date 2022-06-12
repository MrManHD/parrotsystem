package net.mrmanhd.parrot.lib.extension

import eu.thesimplecloud.api.CloudAPI
import eu.thesimplecloud.api.network.component.INetworkComponent
import eu.thesimplecloud.launcher.startup.Launcher
import net.mrmanhd.parrot.lib.messagechannel.dto.CloudMessageDTO

/**
 * Created by MrManHD
 * Class create at 12.06.2022 13:46
 */

fun sendCloudMessage(message: String, levelType: CloudMessageDTO.LevelType = CloudMessageDTO.LevelType.CONSOLE) {
    val messageChannel = CloudAPI.instance.getMessageChannelManager()
        .getMessageChannelByName<CloudMessageDTO>("parrot-cloud-message") ?: return
    messageChannel.sendMessage(CloudMessageDTO(message, levelType), INetworkComponent.MANAGER_COMPONENT)
}

fun writeMessage(message: String) {
    if (CloudAPI.instance.isManager()) {
        Launcher.instance.logger.console(message)
    } else {
        println(message)
    }
}