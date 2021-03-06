package net.mrmanhd.parrot.lib.api.service

import eu.thesimplecloud.api.CloudAPI
import eu.thesimplecloud.api.service.ICloudService
import eu.thesimplecloud.api.servicegroup.ICloudServiceGroup
import eu.thesimplecloud.clientserverapi.lib.promise.CommunicationPromise
import eu.thesimplecloud.clientserverapi.lib.promise.ICommunicationPromise
import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.api.service.IParrotService
import net.mrmanhd.parrot.lib.Parrot
import net.mrmanhd.parrot.lib.api.group.ParrotGroup
import net.mrmanhd.parrot.lib.api.service.builder.ParrotServiceBuilder
import net.mrmanhd.parrot.lib.exception.CloudServiceNotFoundException
import net.mrmanhd.parrot.lib.extension.hasServiceLoaded
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
        val cloudService = getStartingCloudService() ?: return CommunicationPromise
            .failed(CloudServiceNotFoundException())
        return CommunicationPromise.runAsync { handleAsyncPromise(cloudService) }
    }

    private fun handleAsyncPromise(cloudService: ICloudService): ParrotService {
        val parrotService = createNewServiceInstance(cloudService.getName())
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
            this.builder.propertyMap,
            this.builder.spawnLocation,
            this.builder.variant
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

    private fun getStartingCloudService(): ICloudService? {
        this.builder.getCloudService()?.let { return it }
        val serviceGroup = getCloudServiceGroup()
        if (serviceGroup.getAllServices().none { it.hasServiceLoaded() }) {
            sendCloudMessage("service.start.failed.daemons.offline")
            return null
        }
        if (CloudAPI.instance.getGlobalPropertyHolder().hasProperty("parrot-disable-starting")) {
            sendCloudMessage("service.start.failed.disable.starting")
            return null
        }
        return getCloudServiceWithMinimumParrotServices(serviceGroup)
    }

    private fun getCloudServiceWithMinimumParrotServices(serviceGroup: ICloudServiceGroup): ICloudService {
        val services = serviceGroup.getAllServices().filter { it.hasServiceLoaded() }.shuffled()
        return services.minByOrNull { ParrotApi.instance.getServiceHandler().getAllServicesByCloudService(it).size }
            ?: getRandomCloudService(serviceGroup)
    }

    private fun getRandomCloudService(serviceGroup: ICloudServiceGroup): ICloudService {
        return serviceGroup.getAllServices().filter { it.hasServiceLoaded() }.shuffled().random()
    }

    private fun getCloudServiceGroup(): ICloudServiceGroup {
        this.builder.cloudServiceGroup?.let { return it }
        return Parrot.instance.configRepository.getConfig().getStartGroups().random()
    }

}