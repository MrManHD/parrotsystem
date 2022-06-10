package net.mrmanhd.parrot.service

import net.mrmanhd.parrot.lib.Parrot
import net.mrmanhd.parrot.service.hazelcast.HazelcastClientHandler
import net.mrmanhd.parrot.service.hazelcast.HazelcastServerHandler

/**
 * Created by MrManHD
 * Class create at 10.06.2022 19:28
 */

class ParrotServiceCore {

    val hazelcastClientHandler = HazelcastClientHandler()
    val hazelcastServerHandler = HazelcastServerHandler()

    init {
        instance = this
        Parrot()
    }

    fun shutdown() {
        this.hazelcastServerHandler.stopConnection()
        this.hazelcastClientHandler.stopConnection()
    }

    companion object {
        lateinit var instance: ParrotServiceCore
            private set
    }

}