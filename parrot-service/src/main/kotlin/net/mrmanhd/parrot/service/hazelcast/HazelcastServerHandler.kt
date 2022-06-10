package net.mrmanhd.parrot.service.hazelcast

import com.hazelcast.config.Config
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import net.mrmanhd.parrot.api.ParrotApi

/**
 * Created by MrManHD
 * Class create at 10.06.2022 22:03
 */

class HazelcastServerHandler {

    private var hazelcastInstance: HazelcastInstance? = null

    fun startConnection() {
        val config = Config()
        config.classLoader = ParrotApi::class.java.classLoader
        config.setProperty("hazelcast.logging.type", "none")
        this.hazelcastInstance = Hazelcast.newHazelcastInstance(config)
    }

    fun stopConnection() {
        this.hazelcastInstance?.shutdown()
    }

    fun getHazelcastInstance(): HazelcastInstance? = this.hazelcastInstance

}