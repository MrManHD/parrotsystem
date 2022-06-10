package net.mrmanhd.parrot.lib.api

import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.api.group.IGroupHandler
import net.mrmanhd.parrot.api.service.IServiceHandler
import net.mrmanhd.parrot.lib.api.group.GroupHandler
import net.mrmanhd.parrot.lib.api.service.ServiceHandler

/**
 * Created by MrManHD
 * Class create at 10.06.2022 12:07
 */

class ParrotLib : ParrotApi() {

    val serviceHandler = ServiceHandler()
    val groupHandler = GroupHandler()

    init {
        instance = this
    }

    override fun getServiceHandler(): IServiceHandler = this.serviceHandler

    override fun getGroupHandler(): IGroupHandler = this.groupHandler

    companion object {
        lateinit var instance: ParrotLib
            private set
    }

}