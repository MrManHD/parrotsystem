package net.mrmanhd.parrot.lib.hazelcast

import com.hazelcast.client.HazelcastClient
import com.hazelcast.client.config.ClientConfig
import com.hazelcast.client.config.ClientNetworkConfig
import com.hazelcast.config.Config
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import eu.thesimplecloud.api.CloudAPI
import net.mrmanhd.parrot.api.ParrotApi

/**
 * Created by MrManHD
 * Class create at 10.06.2022 22:03
 */

class HazelcastClientHandler {

    private var hazelcastInstance: HazelcastInstance? = null

    fun startConnection() {
        val wrapperHosts = CloudAPI.instance.getWrapperManager().getAllCachedObjects().map { it.getHost() }
        println("Hazelcast Client connected to hosts: ${wrapperHosts.joinToString(", ")}")

        this.hazelcastInstance = HazelcastClient.newHazelcastClient(getClientConfig(wrapperHosts))
    }

    fun stopConnection() {
        this.hazelcastInstance?.shutdown()
    }

    fun getHazelcastInstance(): HazelcastInstance? = this.hazelcastInstance

    private fun getClientConfig(wrapperHosts: List<String>): ClientConfig {
        val config = ClientConfig()
        config.classLoader = ParrotApi::class.java.classLoader
        config.networkConfig = ClientNetworkConfig().addAddress(*wrapperHosts.toTypedArray())
        config.connectionStrategyConfig.isAsyncStart = true
        return config
    }

}