package net.mrmanhd.parrot.lib.world

import com.grinderwolf.swm.api.SlimePlugin
import net.mrmanhd.parrot.lib.extension.debugMessage
import net.mrmanhd.parrot.lib.world.template.WorldTemplate
import org.bukkit.Bukkit

/**
 * Created by MrManHD
 * Class create at 16.06.2022 22:56
 */

class WorldHandler {

    private val slimePlugin by lazy {
        Bukkit.getPluginManager().getPlugin("SlimeWorldManager") as SlimePlugin
    }

    fun loadSlimeWorldFromTemplate(worldTemplate: WorldTemplate, newSlimeWorldName: String) {
        debugMessage("debug.world.load.template", worldTemplate.slimeWorldTemplateName, worldTemplate.parrotGroup.getName())
        val slimeWorld = worldTemplate.getSlimeWorldTemplate().clone(newSlimeWorldName)
        this.slimePlugin.generateWorld(slimeWorld)
    }

}