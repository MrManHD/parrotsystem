package net.mrmanhd.parrot.service.cloud

import eu.thesimplecloud.api.CloudAPI
import eu.thesimplecloud.api.external.ICloudModule
import eu.thesimplecloud.launcher.startup.Launcher
import net.mrmanhd.parrot.lib.Parrot
import net.mrmanhd.parrot.service.ParrotServiceCore
import net.mrmanhd.parrot.service.cloud.command.*
import net.mrmanhd.parrot.service.cloud.config.ConfigHandler
import net.mrmanhd.parrot.service.cloud.group.GroupHandler
import net.mrmanhd.parrot.service.cloud.handler.TablistHandler
import net.mrmanhd.parrot.service.cloud.listener.CloudPlayerLoginListener
import net.mrmanhd.parrot.service.cloud.message.ChatMessageHandler
import net.mrmanhd.parrot.service.cloud.process.ServiceProcessManager

/**
 * Created by MrManHD
 * Class create at 10.06.2022 21:40
 */

class CloudModule : ICloudModule {

    val groupHandler = GroupHandler()
    val chatMessageHandler = ChatMessageHandler()
    val configHandler = ConfigHandler()
    val tablistHandler = TablistHandler()

    override fun isReloadable(): Boolean = false

    override fun onEnable() {
        instance = this
        ParrotServiceCore()
        ServiceProcessManager()

        Parrot.instance.hazelcastServerHandler.startConnection()

        this.tablistHandler.schedule()
        this.groupHandler.loadGroups()
        this.configHandler.loadConfig()
        this.chatMessageHandler.loadChatMessages()

        registerEvents()
    }

    override fun onDisable() {
        ParrotServiceCore.instance.shutdown()
    }

    private fun registerEvents() {
        val eventManager = CloudAPI.instance.getEventManager()
        eventManager.registerListener(this, CloudPlayerLoginListener())

        val commandManager = Launcher.instance.commandManager
        commandManager.registerCommand(this, ParrotCommand())
        commandManager.registerCommand(this, CreateGroupCommand())
        commandManager.registerCommand(this, ParrotReloadCommand())
        commandManager.registerCommand(this, ListCommand())
        commandManager.registerCommand(this, CreateGameCommand())
        commandManager.registerCommand(this, StopCommand())
        commandManager.registerCommand(this, RestartCommand())
    }

    companion object {
        lateinit var instance: CloudModule
            private set
    }

}