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
class StartCommand : ICommandHandler {

    @CommandSubPath("start")
    fun handle(sender: ICommandSender) {
        sender.sendMessage(">> parrot start <GroupName>")
        sender.sendMessage(">> parrot start <GroupName> <ServiceName>")
        sender.sendMessage(">> parrot start <GroupName> <ServiceName> <isPrivate>")
    }

    @CommandSubPath("start <groupName>")
    fun handleExecute(sender: ICommandSender, @CommandArgument("groupName") groupName: String) {
        val parrotGroup = ParrotApi.instance.getGroupHandler().getGroupByName(groupName)
        if (parrotGroup == null) {
            sender.sendChatMessage("command.start.failed.group", groupName)
            return
        }

        startService(sender, ParrotApi.instance.getServiceHandler().createService(parrotGroup))
    }

    @CommandSubPath("start <groupName> <serviceName>")
    fun handleExecute(
        sender: ICommandSender,
        @CommandArgument("groupName") groupName: String,
        @CommandArgument("serviceName") serviceName: String
    ) {
        val parrotGroup = ParrotApi.instance.getGroupHandler().getGroupByName(groupName)
        if (parrotGroup == null) {
            sender.sendChatMessage("command.start.failed.group", groupName)
            return
        }

        val cloudService = CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(serviceName)
        if (cloudService == null) {
            sender.sendChatMessage("command.start.failed.cloudService", serviceName)
            return
        }

        val serviceBuilder = ParrotApi.instance.getServiceHandler().createService(parrotGroup)
            .withCloudService(cloudService)
        startService(sender, serviceBuilder)
    }

    @CommandSubPath("start <groupName> <serviceName> <isPrivate>")
    fun handleExecute(
        sender: ICommandSender,
        @CommandArgument("groupName") groupName: String,
        @CommandArgument("serviceName") serviceName: String,
        @CommandArgument("isPrivate") isPrivate: Boolean
    ) {
        val parrotGroup = ParrotApi.instance.getGroupHandler().getGroupByName(groupName)
        if (parrotGroup == null) {
            sender.sendChatMessage("command.start.failed.group", groupName)
            return
        }

        val cloudService = CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(serviceName)
        if (cloudService == null) {
            sender.sendChatMessage("command.start.failed.cloudService", serviceName)
            return
        }

        val serviceBuilder = ParrotApi.instance.getServiceHandler().createService(parrotGroup)
            .withCloudService(cloudService)

        if (isPrivate) {
            serviceBuilder.withPrivateService()
        }

        startService(sender, serviceBuilder)
    }

    private fun startService(sender: ICommandSender, serviceBuilder: IParrotServiceBuilder) {
        serviceBuilder.startService()
            .addResultListener { sender.sendChatMessage("command.start.success", it.getName()) }
            .addFailureListener { it.printStackTrace() }
    }

}