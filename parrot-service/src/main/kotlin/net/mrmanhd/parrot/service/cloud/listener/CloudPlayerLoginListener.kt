package net.mrmanhd.parrot.service.cloud.listener

import eu.thesimplecloud.api.event.player.CloudPlayerLoginEvent
import eu.thesimplecloud.api.eventapi.CloudEventHandler
import eu.thesimplecloud.api.eventapi.IListener
import net.mrmanhd.parrot.service.cloud.CloudModule

/**
 * Created by MrManHD
 * Class create at 16.06.2022 13:39
 */

class CloudPlayerLoginListener : IListener {

    @CloudEventHandler
    fun handleLogin(event: CloudPlayerLoginEvent) {
        event.getCloudPlayer()
            .addResultListener { CloudModule.instance.tablistHandler.sendTablistToCloudPlayer(it) }
    }

}