package net.mrmanhd.parrot.lib.messagechannel.listener

import eu.thesimplecloud.api.message.IMessageListener
import eu.thesimplecloud.api.network.component.INetworkComponent
import net.mrmanhd.parrot.lib.messagechannel.dto.ParrotServiceStateDTO

/**
 * Created by MrManHD
 * Class create at 12.06.2022 17:16
 */

class ParrotServiceStateListener : IMessageListener<ParrotServiceStateDTO> {

    override fun messageReceived(msg: ParrotServiceStateDTO, sender: INetworkComponent) {
        if (msg.type == ParrotServiceStateDTO.Type.STARTING) {
            println("starte ${msg.parrotName}")
        }
    }

}