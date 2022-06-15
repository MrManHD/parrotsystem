package net.mrmanhd.parrot.service.cloud.process

import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.api.group.IParrotGroup
import net.mrmanhd.parrot.lib.Parrot
import java.util.concurrent.TimeUnit

/**
 * Created by MrManHD
 * Class create at 15.06.2022 12:49
 */

class ServiceProcessStopHandler {

    fun handle() {
        Parrot.instance.scheduler.scheduleWithFixedDelay({
            ParrotApi.instance.getGroupHandler().getAllGroups().forEach { handleStoppingServiceByGroup(it) }
        }, 10, 60 * 3, TimeUnit.SECONDS)
    }

    private fun handleStoppingServiceByGroup(parrotGroup: IParrotGroup) {
        val allServices = parrotGroup.getAllServices()
            .filter { !it.isInPlayingState() }
            .filter { it.getGamePlayers().isEmpty() }
        val emptyServiceSize = allServices.size - parrotGroup.getMinimumOnlineServiceCount()
        if (emptyServiceSize > 0) {
            allServices.take(emptyServiceSize).forEach { it.stop() }
        }
    }

}