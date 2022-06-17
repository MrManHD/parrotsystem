package net.mrmanhd.parrot.lib

import net.mrmanhd.parrot.lib.api.ParrotLib
import net.mrmanhd.parrot.lib.hazelcast.HazelcastClientHandler
import net.mrmanhd.parrot.lib.hazelcast.HazelcastServerHandler
import net.mrmanhd.parrot.lib.message.ChatMessageService
import net.mrmanhd.parrot.lib.messagechannel.MessageChannelRegistry
import net.mrmanhd.parrot.lib.repository.ChatMessageRepository
import net.mrmanhd.parrot.lib.repository.ConfigRepository
import net.mrmanhd.parrot.lib.repository.ParrotGroupRepository
import net.mrmanhd.parrot.lib.repository.ParrotServiceRepository
import net.mrmanhd.parrot.lib.world.WorldHandler
import net.mrmanhd.parrot.lib.world.template.WorldTemplateHandler
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

/**
 * Created by MrManHD
 * Class create at 10.06.2022 12:06
 */

class Parrot {

    val scheduler = Executors.newScheduledThreadPool(1)

    val chatMessageService = ChatMessageService()

    val hazelcastClientHandler = HazelcastClientHandler()
    val hazelcastServerHandler = HazelcastServerHandler()

    val worldHandler = WorldHandler()
    val worldTemplateHandler = WorldTemplateHandler()

    val parrotServiceRepository = ParrotServiceRepository()
    val parrotGroupRepository = ParrotGroupRepository()
    val chatMessageRepository = ChatMessageRepository()
    val configRepository = ConfigRepository()

    val messageChannelRegistry = MessageChannelRegistry()

    init {
        instance = this
        ParrotLib()

        messageChannelRegistry.registerChannels()
        messageChannelRegistry.registerListeners()
    }

    fun shutdown() {
        this.messageChannelRegistry.unregisterChannels()
        this.hazelcastServerHandler.stopConnection()
        this.hazelcastClientHandler.stopConnection()
    }

    companion object {
        lateinit var instance: Parrot
            private set
    }

}