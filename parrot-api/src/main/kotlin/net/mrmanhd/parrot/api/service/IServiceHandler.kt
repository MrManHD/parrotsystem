package net.mrmanhd.parrot.api.service

import eu.thesimplecloud.api.service.ICloudService
import net.mrmanhd.parrot.api.group.IParrotGroup
import net.mrmanhd.parrot.api.service.builder.IParrotServiceBuilder
import java.util.UUID

/**
 * Created by MrManHD
 * Class create at 10.06.2022 11:53
 */

interface IServiceHandler {

    fun createService(parrotGroup: IParrotGroup): IParrotServiceBuilder

    fun getServiceByPlayer(playerUniqueId: UUID): IParrotService? {
        return getAllServices().firstOrNull { it.isPlayerOnline(playerUniqueId) }
    }

    fun getServiceByUniqueId(serviceUniqueId: UUID): IParrotService? {
        return getAllServices().firstOrNull { it.getUniqueId() == serviceUniqueId }
    }

    fun getServiceByName(parrotName: String): IParrotService? {
        return getAllServices().firstOrNull { it.getName() == parrotName }
    }

    fun getAllServicesByOwner(owner: UUID): List<IParrotService> {
        return getAllServices().filter { it.getOwner() == owner }
    }

    fun getAllServicesByGroup(parrotGroup: IParrotGroup): List<IParrotService> {
        return getAllServices().filter { it.getGroup().getName() == parrotGroup.getName() }
    }

    fun getAllServicesByCloudService(cloudService: ICloudService): List<IParrotService> {
        return getAllServices().filter { it.getCloudServiceName() == cloudService.getName() }
    }

    fun getAllServices(): List<IParrotService>

}