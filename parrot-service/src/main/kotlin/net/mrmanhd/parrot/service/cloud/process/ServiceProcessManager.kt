package net.mrmanhd.parrot.service.cloud.process

/**
 * Created by MrManHD
 * Class create at 15.06.2022 12:49
 */

class ServiceProcessManager {

    init {
        ServiceProcessStartHandler().handle()
        ServiceProcessStopHandler().handle()
    }

}