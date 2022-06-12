package net.mrmanhd.parrot.lib.messagechannel.listener

import eu.thesimplecloud.api.message.IMessageListener
import eu.thesimplecloud.api.network.component.INetworkComponent
import net.mrmanhd.parrot.lib.messagechannel.dto.PlayerConnectorDTO

/**
 * Created by MrManHD
 * Class create at 12.06.2022 21:01
 */

class PlayerConnectorListener : IMessageListener<PlayerConnectorDTO> {

    override fun messageReceived(msg: PlayerConnectorDTO, sender: INetworkComponent) {
        TODO("Not yet implemented")
    }

}