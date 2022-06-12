package net.mrmanhd.parrot.api

import com.hazelcast.core.HazelcastInstance
import net.mrmanhd.parrot.api.group.IGroupHandler
import net.mrmanhd.parrot.api.service.ILocalServiceHandler
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

    abstract fun getLocalServiceHandler(): ILocalServiceHandler

    abstract fun getGroupHandler(): IGroupHandler

    abstract fun getHazelcast(): HazelcastInstance

    companion object {
        lateinit var instance: ParrotApi
            private set
    }

}