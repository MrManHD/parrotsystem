package net.mrmanhd.parrot.service.cloud.command

import eu.thesimplecloud.api.command.ICommandSender
import eu.thesimplecloud.launcher.console.command.CommandType
import eu.thesimplecloud.launcher.console.command.ICommandHandler
import eu.thesimplecloud.launcher.console.command.annotations.*
import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.api.utils.ParrotLocation
import net.mrmanhd.parrot.service.cloud.CloudModule
import net.mrmanhd.parrot.service.cloud.group.Group

/**
 * Created by MrManHD
 * Class create at 11.06.2022 19:49
 */

@Command("parrot", CommandType.CONSOLE)
class ListCommand : ICommandHandler {

    @CommandSubPath("list")
    fun handle(sender: ICommandSender) {
        sender.sendMessage("")
        ParrotApi.instance.getServiceHandler().getAllServices().forEach {
            sender.sendMessage(it.toJsonString())
            sender.sendMessage("")
        }
    }

}