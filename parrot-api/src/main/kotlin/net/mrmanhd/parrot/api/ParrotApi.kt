package net.mrmanhd.parrot.api

import net.mrmanhd.parrot.api.group.IGroupHandler
import net.mrmanhd.parrot.api.service.IServiceHandler

/**
 * Created by MrManHD
 * Class create at 10.06.2022 10:58
 */

abstract class ParrotApi {

    init {
        instance = this
    }

    abstract fun getServiceHandler(): IServiceHandler

    abstract fun getGroupHandler(): IGroupHandler

    companion object {
        lateinit var instance: ParrotApi
            private set
    }

}