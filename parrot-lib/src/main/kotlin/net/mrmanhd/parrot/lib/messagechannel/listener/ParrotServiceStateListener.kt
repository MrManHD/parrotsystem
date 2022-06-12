package net.mrmanhd.parrot.lib.messagechannel.listener

import eu.thesimplecloud.api.message.IMessageListener
import eu.thesimplecloud.api.network.component.INetworkComponent
import net.mrmanhd.parrot.api.service.IParrotService
import net.mrmanhd.parrot.lib.api.ParrotLib
import net.mrmanhd.parrot.lib.messagechannel.dto.ParrotServiceStateDTO

/**
 * Created by MrManHD
 * Class create at 12.06.2022 17:16
 */

class ParrotServiceStateListener : IMessageListener<ParrotServiceStateDTO> {

    override fun messageReceived(msg: ParrotServiceStateDTO, sender: INetworkComponent) {
        val parrotService = msg.getParrotService() ?: return
        if (msg.type == ParrotServiceStateDTO.Type.STARTING) {
            handleStarting(parrotService)
        } else {
            handleStopping(parrotService)
        }
    }

    private fun handleStarting(parrotService: IParrotService) {
        val localServiceHandler = ParrotLib.instance.localServiceHandler
        localServiceHandler.addLocalService(parrotService)
    }

    private fun handleStopping(parrotService: IParrotService) {
        val localServiceHandler = ParrotLib.instance.localServiceHandler
        localServiceHandler.removeLocalService(parrotService)
    }

}