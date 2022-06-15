package net.mrmanhd.parrot.service.cloud.command

import eu.thesimplecloud.api.command.ICommandSender
import eu.thesimplecloud.launcher.console.command.CommandType
import eu.thesimplecloud.launcher.console.command.ICommandHandler
import eu.thesimplecloud.launcher.console.command.annotations.Command
import eu.thesimplecloud.launcher.console.command.annotations.CommandSubPath

/**
 * Created by MrManHD
 * Class create at 11.06.2022 19:49
 */

@Command("parrot", CommandType.CONSOLE)
class ParrotCommand : ICommandHandler {

    @CommandSubPath
    fun handleExecute(sender: ICommandSender) {
        sender.sendMessage(">> parrot start <GroupName>")
        sender.sendMessage(">> parrot start <GroupName> <ServiceName>")
        sender.sendMessage(">> parrot start <GroupName> <ServiceName> <isPrivate>")
        sender.sendMessage(">> parrot stop <ParrotService>")
        sender.sendMessage(">> parrot stopgroup <ParrotGroup>")
        sender.sendMessage(">> parrot list")
        sender.sendMessage(">> parrot reload config")
        sender.sendMessage(">> parrot reload groups")
        sender.sendMessage(">> parrot reload messages")
        sender.sendMessage(">> parrot creategroup <GroupName> <MinimumOnlineServiceCount>")
    }

}