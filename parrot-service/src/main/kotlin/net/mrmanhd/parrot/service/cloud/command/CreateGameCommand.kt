package net.mrmanhd.parrot.service.cloud.command

import eu.thesimplecloud.api.CloudAPI
import eu.thesimplecloud.api.command.ICommandSender
import eu.thesimplecloud.launcher.console.command.CommandType
import eu.thesimplecloud.launcher.console.command.ICommandHandler
import eu.thesimplecloud.launcher.console.command.annotations.*
import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.api.service.builder.IParrotServiceBuilder
import net.mrmanhd.parrot.api.utils.Variant
import net.mrmanhd.parrot.lib.Parrot
import net.mrmanhd.parrot.lib.extension.sendChatMessage
import net.mrmanhd.parrot.service.cloud.provider.BooleanProvider
import net.mrmanhd.parrot.service.cloud.provider.ParrotCloudServiceProvider
import net.mrmanhd.parrot.service.cloud.provider.ParrotGroupProvider

/**
 * Created by MrManHD
 * Class create at 11.06.2022 19:49
 */

@Command("parrot", CommandType.CONSOLE)
class CreateGameCommand : ICommandHandler {

    @CommandSubPath("creategame")
    fun handle(sender: ICommandSender) {
        sender.sendMessage(">> parrot creategame <GroupName>")
        sender.sendMessage(">> parrot creategame <GroupName> <ServiceName>")
        sender.sendMessage(">> parrot creategame <GroupName> <ServiceName> <isPrivate>")
        sender.sendMessage(">> parrot creategame <GroupName> <ServiceName> <isPrivate> <WorldName>")
    }

    @CommandSubPath("creategame <groupName>")
    fun handleExecute(sender: ICommandSender, @CommandArgument("groupName", ParrotGroupProvider::class) groupName: String) {
        val parrotGroup = ParrotApi.instance.getGroupHandler().getGroupByName(groupName)
        if (parrotGroup == null) {
            sender.sendChatMessage("command.start.failed.group", groupName)
            return
        }

        startService(sender, ParrotApi.instance.getServiceHandler().createService(parrotGroup))
    }

    @CommandSubPath("creategame <groupName> <serviceName>")
    fun handleExecute(
        sender: ICommandSender,
        @CommandArgument("groupName", ParrotGroupProvider::class) groupName: String,
        @CommandArgument("serviceName", ParrotCloudServiceProvider::class) serviceName: String
    ) {
        val parrotGroup = ParrotApi.instance.getGroupHandler().getGroupByName(groupName)
        if (parrotGroup == null) {
            sender.sendChatMessage("command.start.failed.group", groupName)
            return
        }

        val cloudService = CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(serviceName)

        val config = Parrot.instance.configRepository.getConfig()
        val serviceGroup = config.getStartGroupNames().firstOrNull { cloudService?.getGroupName() == it.getName() }

        if (serviceGroup == null) {
            sender.sendChatMessage("command.start.failed.cloudService", serviceName)
            return
        }

        val serviceBuilder = ParrotApi.instance.getServiceHandler().createService(parrotGroup)
            .withCloudService(cloudService!!)
        startService(sender, serviceBuilder)
    }

    @CommandSubPath("creategame <groupName> <serviceName> <isPrivate>")
    fun handleExecute(
        sender: ICommandSender,
        @CommandArgument("groupName", ParrotGroupProvider::class) groupName: String,
        @CommandArgument("serviceName", ParrotCloudServiceProvider::class) serviceName: String,
        @CommandArgument("isPrivate", BooleanProvider::class) isPrivate: Boolean
    ) {
        val parrotGroup = ParrotApi.instance.getGroupHandler().getGroupByName(groupName)
        if (parrotGroup == null) {
            sender.sendChatMessage("command.start.failed.group", groupName)
            return
        }

        val cloudService = CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(serviceName)

        val config = Parrot.instance.configRepository.getConfig()
        val serviceGroup = config.getStartGroupNames().firstOrNull { cloudService?.getGroupName() == it.getName() }

        if (serviceGroup == null) {
            sender.sendChatMessage("command.start.failed.cloudService", serviceName)
            return
        }

        val serviceBuilder = ParrotApi.instance.getServiceHandler().createService(parrotGroup)
            .withCloudService(cloudService!!)

        if (isPrivate) {
            serviceBuilder.withPrivateService()
        }

        startService(sender, serviceBuilder)
    }

    @CommandSubPath("creategame <groupName> <serviceName> <isPrivate> <worldName>")
    fun handleExecute(
        sender: ICommandSender,
        @CommandArgument("groupName", ParrotGroupProvider::class) groupName: String,
        @CommandArgument("serviceName", ParrotCloudServiceProvider::class) serviceName: String,
        @CommandArgument("isPrivate", BooleanProvider::class) isPrivate: Boolean,
        @CommandArgument("worldName") worldName: String
    ) {
        val parrotGroup = ParrotApi.instance.getGroupHandler().getGroupByName(groupName)
        if (parrotGroup == null) {
            sender.sendChatMessage("command.start.failed.group", groupName)
            return
        }

        val cloudService = CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(serviceName)

        val config = Parrot.instance.configRepository.getConfig()
        val serviceGroup = config.getStartGroupNames().firstOrNull { cloudService?.getGroupName() == it.getName() }

        if (serviceGroup == null) {
            sender.sendChatMessage("command.start.failed.cloudService", serviceName)
            return
        }

        val split = worldName.split("_")
        val variant = Variant.fromString(split[0])

        val serviceBuilder = ParrotApi.instance.getServiceHandler().createService(parrotGroup)
            .withCloudService(cloudService!!)
            .withVariant(variant)
            .withMaxPlayersVariant()
            .withMotd(split[1])

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