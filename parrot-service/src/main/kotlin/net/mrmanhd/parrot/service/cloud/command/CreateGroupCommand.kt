package net.mrmanhd.parrot.service.cloud.command

import eu.thesimplecloud.api.command.ICommandSender
import eu.thesimplecloud.launcher.console.command.CommandType
import eu.thesimplecloud.launcher.console.command.ICommandHandler
import eu.thesimplecloud.launcher.console.command.annotations.*
import net.mrmanhd.parrot.api.utils.ParrotLocation
import net.mrmanhd.parrot.lib.extension.sendChatMessage
import net.mrmanhd.parrot.service.cloud.CloudModule
import net.mrmanhd.parrot.service.cloud.group.Group

/**
 * Created by MrManHD
 * Class create at 11.06.2022 19:49
 */

@Command("parrot", CommandType.CONSOLE)
class CreateGroupCommand : ICommandHandler {

    @CommandSubPath("creategroup")
    fun handle(sender: ICommandSender) {
        sender.sendMessage(">> parrot creategroup <GroupName> <MinimumOnlineServiceCount>")
    }

    @CommandSubPath("creategroup <groupName> <minimumOnlineServiceCount>")
    fun handleExecute(
        sender: ICommandSender,
        @CommandArgument("groupName") groupName: String,
        @CommandArgument("minimumOnlineServiceCount") minimumOnlineServiceCount: Int
    ) {
        val spawnLocation = ParrotLocation("world", 0.0, 100.0, 0.0, 0F, 0F)
        val group = Group(groupName, listOf(), minimumOnlineServiceCount, listOf(), false, spawnLocation)
        CloudModule.instance.groupHandler.addGroup(group)

        sender.sendChatMessage("command.setup.create.group.success", groupName)
    }

}