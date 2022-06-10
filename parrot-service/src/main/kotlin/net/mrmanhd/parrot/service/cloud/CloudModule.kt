package net.mrmanhd.parrot.service.cloud

import eu.thesimplecloud.api.external.ICloudModule
import net.mrmanhd.parrot.service.ParrotServiceCore

/**
 * Created by MrManHD
 * Class create at 10.06.2022 21:40
 */

class CloudModule : ICloudModule {

    override fun onEnable() {
        val parrotServiceCore = ParrotServiceCore()
        parrotServiceCore.hazelcastServerHandler.startConnection()
    }

    override fun onDisable() {
        ParrotServiceCore.instance.shutdown()
    }

}