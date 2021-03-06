package net.mrmanhd.parrot.service.cloud.command

import eu.thesimplecloud.api.command.ICommandSender
import eu.thesimplecloud.launcher.console.command.CommandType
import eu.thesimplecloud.launcher.console.command.ICommandHandler
import eu.thesimplecloud.launcher.console.command.annotations.Command
import eu.thesimplecloud.launcher.console.command.annotations.CommandSubPath
import net.mrmanhd.parrot.lib.extension.sendChatMessage
import net.mrmanhd.parrot.service.cloud.CloudModule

/**
 * Created by MrManHD
 * Class create at 11.06.2022 19:49
 */

@Command("parrot", CommandType.CONSOLE)
class ParrotReloadCommand : ICommandHandler {

    @CommandSubPath("reload")
    fun handle(sender: ICommandSender) {
        sender.sendMessage(">> parrot reload config")
        sender.sendMessage(">> parrot reload groups")
        sender.sendMessage(">> parrot reload messages")
    }

    @CommandSubPath("reload groups")
    fun handleExecuteGroups(sender: ICommandSender) {
        sender.sendChatMessage("command.reload.group")
        CloudModule.instance.groupHandler.updateGroups()
    }

    @CommandSubPath("reload messages")
    fun handleExecuteMessages(sender: ICommandSender) {
        sender.sendChatMessage("command.reload.messages")
        CloudModule.instance.chatMessageHandler.updateChatMessages()
    }

    @CommandSubPath("reload config")
    fun handleExecuteConfig(sender: ICommandSender) {
        sender.sendChatMessage("command.reload.config")
        CloudModule.instance.configHandler.updateConfig()
    }

}