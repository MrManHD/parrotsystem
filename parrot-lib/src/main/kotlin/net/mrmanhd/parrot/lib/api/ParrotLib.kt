package net.mrmanhd.parrot.lib.api

import com.hazelcast.core.HazelcastInstance
import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.api.group.IGroupHandler
import net.mrmanhd.parrot.api.service.ILocalServiceHandler
import net.mrmanhd.parrot.api.service.IServiceHandler
import net.mrmanhd.parrot.lib.Parrot
import net.mrmanhd.parrot.lib.api.group.GroupHandler
import net.mrmanhd.parrot.lib.api.service.LocalServiceHandler
import net.mrmanhd.parrot.lib.api.service.ServiceHandler

/**
 * Created by MrManHD
 * Class create at 10.06.2022 12:07
 */

class ParrotLib : ParrotApi() {

    val serviceHandler = ServiceHandler()
    val groupHandler = GroupHandler()
    val localServiceHandler = LocalServiceHandler()

    init {
        instance = this
    }

    override fun getServiceHandler(): IServiceHandler = this.serviceHandler

    override fun getLocalServiceHandler(): ILocalServiceHandler = this.localServiceHandler

    override fun getGroupHandler(): IGroupHandler = this.groupHandler

    override fun getHazelcast(): HazelcastInstance {
        return Parrot.instance.hazelcastServerHandler.getHazelcastInstance()
            ?: Parrot.instance.hazelcastClientHandler.getHazelcastInstance()!!
    }

    companion object {
        lateinit var instance: ParrotLib
            private set
    }

}