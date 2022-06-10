package net.mrmanhd.parrot.service.service

import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.*
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.proxy.ProxyServer
import net.mrmanhd.parrot.service.ParrotService
import java.util.logging.Logger

/**
 * Created by MrManHD
 * Class create at 10.06.2022 21:40
 */

@Plugin(id = "parrotservice", name = "parrotservice", version = "1.0.1", authors = ["MrManHD"])
class VelocityPlugin @Inject constructor(
    val server: ProxyServer,
    val logger: Logger,
) {

    @Subscribe
    fun handleProxyInitialization(event: ProxyInitializeEvent) {
        val parrotService = ParrotService()
        parrotService.hazelcastClientHandler.startConnection()
    }

    @Subscribe
    fun handleProxyShutdown(event: ProxyShutdownEvent) {
        ParrotService.instance.shutdown()
    }

}