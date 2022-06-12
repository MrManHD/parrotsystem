package net.mrmanhd.parrot.lib.messagechannel.listener

import eu.thesimplecloud.api.message.IMessageListener
import eu.thesimplecloud.api.network.component.INetworkComponent
import eu.thesimplecloud.launcher.startup.Launcher
import net.mrmanhd.parrot.lib.messagechannel.dto.CloudMessageDTO

/**
 * Created by MrManHD
 * Class create at 12.06.2022 13:49
 */

class CloudMessageListener : IMessageListener<CloudMessageDTO> {

    override fun messageReceived(msg: CloudMessageDTO, sender: INetworkComponent) {
        val logger = Launcher.instance.logger
        when (msg.levelType) {
            CloudMessageDTO.LevelType.CONSOLE -> logger.console(msg.message)
            CloudMessageDTO.LevelType.INFO -> logger.info(msg.message)
            CloudMessageDTO.LevelType.WARNING -> logger.warning(msg.message)
        }
    }

}