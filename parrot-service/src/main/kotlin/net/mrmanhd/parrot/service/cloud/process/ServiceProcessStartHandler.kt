package net.mrmanhd.parrot.service.cloud.process

import eu.thesimplecloud.api.service.ICloudService
import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.api.group.IParrotGroup
import net.mrmanhd.parrot.lib.Parrot
import net.mrmanhd.parrot.lib.extension.hasServiceLoaded
import java.util.concurrent.TimeUnit
import kotlin.random.Random

/**
 * Created by MrManHD
 * Class create at 15.06.2022 12:49
 */

class ServiceProcessStartHandler {

    fun handle() {
        Parrot.instance.scheduler.scheduleWithFixedDelay({
            if (getCloudServices().any { it.hasServiceLoaded() }) {
                ParrotApi.instance.getGroupHandler().getAllGroups().forEach { handleStartingServiceByGroup(it) }
            }
        }, 10, 5, TimeUnit.SECONDS)
    }

    private fun getCloudServices(): List<ICloudService> {
        return Parrot.instance.configRepository.getConfig().getStartGroupNames()
            .map { it.getAllServices() }
            .flatten()
    }

    private fun handleStartingServiceByGroup(parrotGroup: IParrotGroup) {
        val allServices = parrotGroup.getAllServices().filter { !it.isInPlayingState() }
        if (allServices.size < parrotGroup.getMinimumOnlineServiceCount()) {
            Parrot.instance.scheduler.schedule({
                parrotGroup.createService().startService()
            }, Random.nextLong(0,2000), TimeUnit.MILLISECONDS)
        }
    }

}