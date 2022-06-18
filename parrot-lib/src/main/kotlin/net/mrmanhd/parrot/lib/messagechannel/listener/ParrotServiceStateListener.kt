package net.mrmanhd.parrot.lib.messagechannel.listener

import eu.thesimplecloud.api.message.IMessageListener
import eu.thesimplecloud.api.network.component.INetworkComponent
import eu.thesimplecloud.plugin.extension.syncBukkit
import net.kyori.adventure.text.Component
import net.mrmanhd.parrot.api.extension.getBukkitGamePlayers
import net.mrmanhd.parrot.api.extension.getBukkitWorlds
import net.mrmanhd.parrot.api.service.IParrotService
import net.mrmanhd.parrot.lib.Parrot
import net.mrmanhd.parrot.lib.api.ParrotLib
import net.mrmanhd.parrot.lib.extension.debugMessage
import net.mrmanhd.parrot.lib.extension.sendCloudMessage
import net.mrmanhd.parrot.lib.extension.sendMessage
import net.mrmanhd.parrot.lib.messagechannel.dto.ParrotServiceStateDTO
import net.mrmanhd.parrot.lib.messagechannel.dto.ParrotServiceStateDTO.*
import org.bukkit.scheduler.BukkitRunnable
import java.util.concurrent.TimeUnit

/**
 * Created by MrManHD
 * Class create at 12.06.2022 17:16
 */

class ParrotServiceStateListener : IMessageListener<ParrotServiceStateDTO> {

    override fun messageReceived(msg: ParrotServiceStateDTO, sender: INetworkComponent) {
        val parrotService = msg.getParrotService() ?: return
        if (msg.type == Type.STARTING) {
            handleStarting(parrotService)
        } else {
            handleStopping(parrotService)
        }
    }

    private fun handleStarting(parrotService: IParrotService) {
        sendMessage("service.daemon.start.new.service", parrotService.getName(), parrotService.getGroupName())
        sendCloudMessage(
            "service.start.success",
            parrotService.getName(),
            parrotService.getGroupName(),
            parrotService.getCloudService()?.getName() ?: "null"
        )
        debugMessage("debug.daemon.start.new.service", parrotService.getName())

        val localServiceHandler = ParrotLib.instance.localServiceHandler
        localServiceHandler.addLocalService(parrotService)
    }

    private fun handleStopping(parrotService: IParrotService) {
        sendMessage("service.daemon.stop.service", parrotService.getName(), parrotService.getGroupName())
        sendCloudMessage(
            "service.stop",
            parrotService.getName(),
            parrotService.getGroupName(),
            parrotService.getCloudService()?.getName() ?: "null"
        )
        debugMessage("debug.daemon.stop.service", parrotService.getName())

        val localServiceHandler = ParrotLib.instance.localServiceHandler
        localServiceHandler.removeLocalService(parrotService)

        syncBukkit {
            parrotService.getBukkitGamePlayers().forEach {
                it.kick(Component.text("§cDas Spiel wird nun neugestartet!"))
            }

            parrotService.getBukkitWorlds().forEach { parrotService.unloadWorld(it.name) }

            Parrot.instance.scheduler.schedule({
                Parrot.instance.parrotServiceRepository.remove(parrotService.getUniqueId())
            }, 1, TimeUnit.SECONDS)
        }
    }

}