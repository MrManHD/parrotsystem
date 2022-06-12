package net.mrmanhd.parrot.service.cloud.command

import eu.thesimplecloud.api.command.ICommandSender
import eu.thesimplecloud.launcher.console.command.CommandType
import eu.thesimplecloud.launcher.console.command.ICommandHandler
import eu.thesimplecloud.launcher.console.command.annotations.*
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
        sender.sendMessage(">> parrot creategroup <GroupName> <MinimumOnlineServiceCount> <MaxOnlineServiceCount>")
    }

    @CommandSubPath("creategroup <groupName> <minimumOnlineServiceCount> <maxOnlineServiceCount>")
    fun handleExecute(
        sender: ICommandSender,
        @CommandArgument("groupName") groupName: String,
        @CommandArgument("minimumOnlineServiceCount") minimumOnlineServiceCount: Int,
        @CommandArgument("maxOnlineServiceCount") maxOnlineServiceCount: Int
    ) {
        val group = Group(groupName, listOf(), minimumOnlineServiceCount, maxOnlineServiceCount, false)
        CloudModule.instance.groupHandler.addGroup(group)

        sender.sendMessage("ParrotGroup $groupName was created successfully!")
    }

}