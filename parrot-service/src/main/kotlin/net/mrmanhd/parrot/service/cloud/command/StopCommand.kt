package net.mrmanhd.parrot.service.cloud.command

import eu.thesimplecloud.api.CloudAPI
import eu.thesimplecloud.api.command.ICommandSender
import eu.thesimplecloud.launcher.console.command.CommandType
import eu.thesimplecloud.launcher.console.command.ICommandHandler
import eu.thesimplecloud.launcher.console.command.annotations.*
import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.api.service.builder.IParrotServiceBuilder
import net.mrmanhd.parrot.api.utils.ParrotLocation
import net.mrmanhd.parrot.lib.extension.sendChatMessage
import net.mrmanhd.parrot.service.cloud.CloudModule
import net.mrmanhd.parrot.service.cloud.group.Group

/**
 * Created by MrManHD
 * Class create at 11.06.2022 19:49
 */

@Command("parrot", CommandType.CONSOLE)
class StopCommand : ICommandHandler {

    @CommandSubPath("stop")
    fun handle(sender: ICommandSender) {
        sender.sendMessage(">> parrot stop <ParrotService>")
        sender.sendMessage(">> parrot stopgroup <ParrotGroup>")
    }

    @CommandSubPath("stop <parrotService>")
    fun handleExecute(sender: ICommandSender, @CommandArgument("parrotService") parrotService: String) {
        val service = ParrotApi.instance.getServiceHandler().getServiceByName(parrotService)
        if (service == null) {
            sender.sendChatMessage("command.stop.failed.service", parrotService)
            return
        }
        service.stop()
        sender.sendChatMessage("command.stop.success", parrotService)
    }

    @CommandSubPath("stopgroup <parrotGroup>")
    fun handleExecuteStopGroup(sender: ICommandSender, @CommandArgument("parrotGroup") parrotGroup: String) {
        val group = ParrotApi.instance.getGroupHandler().getGroupByName(parrotGroup)
        if (group == null) {
            sender.sendChatMessage("command.stop.failed.service", parrotGroup)
            return
        }
        group.getAllServices().forEach { it.stop() }
        sender.sendChatMessage("command.stopgroup.success", parrotGroup)
    }

}