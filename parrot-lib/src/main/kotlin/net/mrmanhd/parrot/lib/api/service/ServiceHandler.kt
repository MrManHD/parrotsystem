package net.mrmanhd.parrot.lib.api.service

import eu.thesimplecloud.api.CloudAPI
import eu.thesimplecloud.api.service.ICloudService
import eu.thesimplecloud.clientserverapi.lib.promise.CommunicationPromise
import eu.thesimplecloud.clientserverapi.lib.promise.ICommunicationPromise
import net.mrmanhd.parrot.api.group.IParrotGroup
import net.mrmanhd.parrot.api.service.IParrotService
import net.mrmanhd.parrot.api.service.IServiceHandler
import net.mrmanhd.parrot.api.service.builder.IParrotServiceBuilder
import net.mrmanhd.parrot.lib.Parrot
import net.mrmanhd.parrot.lib.api.group.ParrotGroup
import net.mrmanhd.parrot.lib.api.service.builder.ParrotServiceBuilder
import net.mrmanhd.parrot.lib.exception.CloudServiceNotFoundException
import net.mrmanhd.parrot.lib.extension.sendCloudMessage
import net.mrmanhd.parrot.lib.messagechannel.dto.ParrotServiceStateDTO
import net.mrmanhd.parrot.lib.repository.info.ParrotServiceInfo
import net.mrmanhd.parrot.lib.utils.ServiceNumberGenerator
import java.util.*

/**
 * Created by MrManHD
 * Class create at 10.06.2022 12:50
 */

class ServiceHandler : IServiceHandler {

    fun startService(progress: ParrotServiceBuilder): ICommunicationPromise<IParrotService> {
        val cloudService = progress.getCloudService() ?: return CommunicationPromise<IParrotService>()
            .setFailure(CloudServiceNotFoundException())
        return CommunicationPromise.runAsync { handleAsyncPromise(progress, cloudService) }
    }

    override fun createService(parrotGroup: IParrotGroup): IParrotServiceBuilder {
        return ParrotServiceBuilder(parrotGroup)
    }

    override fun getAllServices(): List<IParrotService> {
        return Parrot.instance.parrotServiceRepository.getAllParrotServices()
    }

    private fun handleAsyncPromise(progress: ParrotServiceBuilder, cloudService: ICloudService): ParrotService {
        val parrotService = createNewServiceInstance(progress, cloudService.getName())
        sendCloudMessage("ParrotService ${parrotService.getName()}/${parrotService.getGroupName()} is now online on ${cloudService.getName()}")
        sendStateMessageChannel(parrotService, cloudService)
        return parrotService
    }

    private fun createNewServiceInstance(progress: ParrotServiceBuilder, cloudServiceName: String): ParrotService {
        val parrotGroup = progress.parrotGroup as ParrotGroup
        val number = ServiceNumberGenerator(parrotGroup).getNumber()

        val parrotServiceInfo = ParrotServiceInfo(
            UUID.randomUUID(),
            parrotGroup,
            cloudServiceName,
            "${parrotGroup.getName()}-$number",
            System.currentTimeMillis(),
            progress.owner,
            progress.isPrivateService,
            progress.isRemoveWhenServiceEmpty,
            progress.motd,
            progress.maxPlayers,
            progress.propertyMap
        )
        parrotServiceInfo.update()

        return parrotServiceInfo.convertToParrotService()
    }

    private fun sendStateMessageChannel(parrotService: ParrotService, cloudService: ICloudService) {
        val messageChannel = CloudAPI.instance.getMessageChannelManager()
            .getMessageChannelByName<ParrotServiceStateDTO>("parrot-service-state") ?: return
        val serviceStateDTO = ParrotServiceStateDTO(parrotService.getName(), ParrotServiceStateDTO.Type.STARTING)
        messageChannel.sendMessage(serviceStateDTO, cloudService)
    }

}