package net.mrmanhd.parrot.daemon

import net.mrmanhd.parrot.daemon.group.ParrotTemplateGroupHandler
import org.bukkit.plugin.java.JavaPlugin

/**
 * Created by MrManHD
 * Class create at 13.06.2022 12:47
 */

class ParrotDaemon(
    val javaPlugin: JavaPlugin
) {

    val parrotTemplateGroupHandler = ParrotTemplateGroupHandler()

    init {
        instance = this
        parrotTemplateGroupHandler.loadTemplateGroups()
    }

    companion object {
        lateinit var instance: ParrotDaemon
            private set
    }

}