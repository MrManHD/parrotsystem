package net.mrmanhd.parrot.lib

import net.mrmanhd.parrot.lib.hazelcast.HazelcastClientHandler
import net.mrmanhd.parrot.lib.hazelcast.HazelcastServerHandler
import net.mrmanhd.parrot.lib.repository.ParrotServiceRepository

/**
 * Created by MrManHD
 * Class create at 10.06.2022 12:06
 */

class Parrot {

    val hazelcastClientHandler = HazelcastClientHandler()
    val hazelcastServerHandler = HazelcastServerHandler()

    val parrotServiceRepository = ParrotServiceRepository()

    init {
        instance = this
    }

    companion object {
        lateinit var instance: Parrot
            private set
    }

}