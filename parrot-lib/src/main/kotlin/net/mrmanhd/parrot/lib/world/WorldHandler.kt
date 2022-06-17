package net.mrmanhd.parrot.lib.world

import com.grinderwolf.swm.api.SlimePlugin
import net.mrmanhd.parrot.api.group.IParrotGroup
import net.mrmanhd.parrot.api.service.IParrotService
import net.mrmanhd.parrot.lib.Parrot
import net.mrmanhd.parrot.lib.exception.SlimeWorldTemplateNotFoundException
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

    fun loadGlobalSlimeWorld(parrotGroup: IParrotGroup, slimeWorldTemplateName: String, newSlimeWorldName: String) {
        val worldTemplate = getWorldTemplateFromName(parrotGroup, slimeWorldTemplateName)
        loadSlimeWorldFromTemplate(worldTemplate, newSlimeWorldName)
    }

    fun loadSlimeWorld(parrotService: IParrotService, slimeWorldTemplateName: String) {
        val worldTemplate = getWorldTemplateFromName(parrotService.getGroup(), slimeWorldTemplateName)
        loadSlimeWorldFromTemplate(worldTemplate, "${parrotService.getUniqueId()}-$slimeWorldTemplateName")
    }

    private fun loadSlimeWorldFromTemplate(worldTemplate: WorldTemplate, newSlimeWorldName: String) {
        debugMessage(
            "debug.world.load.template",
            worldTemplate.slimeWorldTemplateName,
            worldTemplate.parrotGroup.getName()
        )
        val slimeWorld = worldTemplate.getSlimeWorldTemplate().clone(newSlimeWorldName)
        this.slimePlugin.generateWorld(slimeWorld)
    }

    private fun getWorldTemplateFromName(parrotGroup: IParrotGroup, slimeWorldTemplateName: String): WorldTemplate {
        val worldTemplateHandler = Parrot.instance.worldTemplateHandler
        return worldTemplateHandler.getTemplateFromName(parrotGroup, slimeWorldTemplateName)
            ?: throw SlimeWorldTemplateNotFoundException(slimeWorldTemplateName)
    }

}