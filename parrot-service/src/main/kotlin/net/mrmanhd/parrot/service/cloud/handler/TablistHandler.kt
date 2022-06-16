package net.mrmanhd.parrot.service.cloud.handler

import eu.thesimplecloud.api.CloudAPI
import eu.thesimplecloud.api.player.ICloudPlayer
import net.mrmanhd.parrot.lib.Parrot
import java.util.concurrent.TimeUnit

/**
 * Created by MrManHD
 * Class create at 16.06.2022 13:35
 */

class TablistHandler {

    private val config by lazy { Parrot.instance.configRepository.getConfig() }

    fun schedule() {
        Parrot.instance.scheduler.scheduleWithFixedDelay({
            val allCachedObjects = CloudAPI.instance.getCloudPlayerManager().getAllCachedObjects()
            allCachedObjects.forEach { sendTablistToCloudPlayer(it) }
        },1,1, TimeUnit.SECONDS)
    }

    fun sendTablistToCloudPlayer(cloudPlayer: ICloudPlayer) {
        if (isCloudPlayerInService(cloudPlayer)) {
            this.config.groupTablist.sendTablist(cloudPlayer)
        } else {
            this.config.legacyTablist?.sendTablist(cloudPlayer)
        }
    }

    private fun isCloudPlayerInService(cloudPlayer: ICloudPlayer): Boolean {
        val cloudServiceGroup = cloudPlayer.getConnectedServer()?.getServiceGroup() ?: return false
        return this.config.getStartGroupNames().contains(cloudServiceGroup)
    }

}