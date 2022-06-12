package net.mrmanhd.parrot.lib.api.service

import eu.thesimplecloud.api.CloudAPI
import eu.thesimplecloud.api.service.ICloudService
import eu.thesimplecloud.clientserverapi.lib.promise.CommunicationPromise
import eu.thesimplecloud.clientserverapi.lib.promise.ICommunicationPromise
import net.mrmanhd.parrot.api.service.IParrotService
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
 * Class create at 12.06.2022 17:48
 */

class ParrotServiceCreator(
    private val builder: ParrotServiceBuilder
) {

    fun start(): ICommunicationPromise<IParrotService> {
        val cloudService = this.builder.getCloudService() ?: return CommunicationPromise<IParrotService>()
            .setFailure(CloudServiceNotFoundException())
        return CommunicationPromise.runAsync { handleAsyncPromise(cloudService) }
    }

    private fun handleAsyncPromise(cloudService: ICloudService): ParrotService {
        val parrotService = createNewServiceInstance(cloudService.getName())
        sendCloudMessage("ParrotService ${parrotService.getName()}/${parrotService.getGroupName()} is now online on ${cloudService.getName()}")
        sendStateMessageChannel(parrotService, cloudService)
        return parrotService
    }

    private fun createNewServiceInstance(cloudServiceName: String): ParrotService {
        val parrotGroup = builder.parrotGroup as ParrotGroup
        val number = ServiceNumberGenerator(parrotGroup).getNumber()

        val parrotServiceInfo = ParrotServiceInfo(
            UUID.randomUUID(),
            parrotGroup,
            cloudServiceName,
            "${parrotGroup.getName()}-$number",
            System.currentTimeMillis(),
            this.builder.owner,
            this.builder.isPrivateService,
            this.builder.isRemoveWhenServiceEmpty,
            this.builder.motd,
            this.builder.maxPlayers,
            this.builder.propertyMap
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