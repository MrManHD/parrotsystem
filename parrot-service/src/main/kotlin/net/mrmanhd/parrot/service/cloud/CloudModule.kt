package net.mrmanhd.parrot.service.cloud

import eu.thesimplecloud.api.external.ICloudModule
import eu.thesimplecloud.launcher.startup.Launcher
import net.mrmanhd.parrot.lib.Parrot
import net.mrmanhd.parrot.service.ParrotServiceCore
import net.mrmanhd.parrot.service.cloud.command.*
import net.mrmanhd.parrot.service.cloud.group.GroupHandler

/**
 * Created by MrManHD
 * Class create at 10.06.2022 21:40
 */

class CloudModule : ICloudModule {

    val groupHandler = GroupHandler()

    override fun isReloadable(): Boolean = false

    override fun onEnable() {
        ParrotServiceCore()
        Parrot.instance.hazelcastServerHandler.startConnection()
        this.groupHandler.loadGroups()
        registerEvents()
    }

    override fun onDisable() {
        ParrotServiceCore.instance.shutdown()
    }

    private fun registerEvents() {
        val commandManager = Launcher.instance.commandManager
        commandManager.registerCommand(this, ParrotCommand())
        commandManager.registerCommand(this, CreateGroupCommand())
        commandManager.registerCommand(this, ParrotReloadCommand())
    }

    companion object {
        lateinit var instance: CloudModule
            private set
    }

}