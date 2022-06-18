package net.mrmanhd.parrot.service.cloud.command

import eu.thesimplecloud.api.CloudAPI
import eu.thesimplecloud.api.command.ICommandSender
import eu.thesimplecloud.launcher.console.command.CommandType
import eu.thesimplecloud.launcher.console.command.ICommandHandler
import eu.thesimplecloud.launcher.console.command.annotations.Command
import eu.thesimplecloud.launcher.console.command.annotations.CommandSubPath
import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.api.service.state.ServiceState
import net.mrmanhd.parrot.lib.Parrot
import net.mrmanhd.parrot.lib.extension.sendChatMessage
import java.util.concurrent.TimeUnit

/**
 * Created by MrManHD
 * Class create at 18.06.2022 21:01
 */

@Command("parrot", CommandType.CONSOLE)
class RestartCommand : ICommandHandler {

    @CommandSubPath("restart")
    fun handle(sender: ICommandSender) {
        val propertyHolder = CloudAPI.instance.getGlobalPropertyHolder()
        if (propertyHolder.hasProperty("parrot-disable-starting")) {
            sender.sendChatMessage("command.restart.failed")
            return
        }

        propertyHolder.setProperty("parrot-disable-starting", true)

        val serviceHandler = ParrotApi.instance.getServiceHandler()
        serviceHandler.getAllServices().filter { it.getState() != ServiceState.INGAME }.forEach { it.stop() }

        startScheduler(sender)
    }

    private fun startScheduler(sender: ICommandSender) {
        Parrot.instance.scheduler.schedule({ scheduleShutdown(sender) },1, TimeUnit.SECONDS)
    }

    private fun scheduleShutdown(sender: ICommandSender) {
        val serviceHandler = ParrotApi.instance.getServiceHandler()
        if (serviceHandler.getAllServices().isNotEmpty()) {
            startScheduler(sender)
            return
        }

        Parrot.instance.configRepository.getConfig()
            .getStartGroups().forEach { it.shutdownAllServices() }

        val propertyHolder = CloudAPI.instance.getGlobalPropertyHolder()
        ParrotApi.instance.getGroupHandler().getAllGroups()
            .map { it.getStartingCloudServiceGroups() }
            .flatten()
            .forEach { it.shutdownAllServices() }

        sender.sendChatMessage("command.restart.success")
        propertyHolder.removeProperty("parrot-disable-starting")
    }

}