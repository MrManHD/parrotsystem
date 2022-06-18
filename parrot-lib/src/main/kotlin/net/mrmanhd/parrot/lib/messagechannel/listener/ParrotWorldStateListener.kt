package net.mrmanhd.parrot.lib.messagechannel.listener

import eu.thesimplecloud.api.message.IMessageListener
import eu.thesimplecloud.api.network.component.INetworkComponent
import eu.thesimplecloud.plugin.extension.syncBukkit
import net.mrmanhd.parrot.api.service.IParrotService
import net.mrmanhd.parrot.lib.Parrot
import net.mrmanhd.parrot.lib.extension.debugMessage
import net.mrmanhd.parrot.lib.extension.sendCloudMessage
import net.mrmanhd.parrot.lib.messagechannel.dto.ParrotWorldStateDTO
import org.bukkit.Bukkit

/**
 * Created by MrManHD
 * Class create at 18.06.2022 15:33
 */

class ParrotWorldStateListener : IMessageListener<ParrotWorldStateDTO> {

    override fun messageReceived(msg: ParrotWorldStateDTO, sender: INetworkComponent) {
        val parrotService = msg.getParrotService()
        if (msg.type == ParrotWorldStateDTO.Type.LOAD) {
            loadSlimeWorld(parrotService, msg.slimeWorldtemplateName, sender.getName())
        } else {
            unloadSlimeWorld(msg.parrotName, msg.slimeWorldtemplateName)
        }
    }

    private fun loadSlimeWorld(parrotService: IParrotService?, slimeWorldTemplateName: String, senderName: String) {
        if (parrotService == null) {
            sendCloudMessage("service.world.load.failed.parrotService", slimeWorldTemplateName, senderName)
            return
        }

        val currentTimeMillis = System.currentTimeMillis()
        debugMessage("debug.service.world.load", slimeWorldTemplateName, parrotService.getName())

        syncBukkit {
            Parrot.instance.worldHandler.loadSlimeWorld(parrotService, slimeWorldTemplateName)
            val millis = System.currentTimeMillis() - currentTimeMillis
            debugMessage("debug.service.world.load.success", slimeWorldTemplateName, parrotService.getName(), millis)
        }

    }

    private fun unloadSlimeWorld(parrotName: String, slimeWorldTemplateName: String) {
        debugMessage("debug.service.world.unload", slimeWorldTemplateName, parrotName)
        Bukkit.unloadWorld(slimeWorldTemplateName, false)
    }

}