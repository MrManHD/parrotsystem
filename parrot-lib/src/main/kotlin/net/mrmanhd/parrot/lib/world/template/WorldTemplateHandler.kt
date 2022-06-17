package net.mrmanhd.parrot.lib.world.template

import net.mrmanhd.parrot.api.group.IParrotGroup

/**
 * Created by MrManHD
 * Class create at 16.06.2022 23:58
 */

class WorldTemplateHandler {

    private val templates = arrayListOf<WorldTemplate>()

    fun loadTemplate(parotGroup: IParrotGroup, slimeWorldTemplateName: String): WorldTemplate {
        val worldTemplate = WorldTemplate(parotGroup, slimeWorldTemplateName)
        this.templates.add(worldTemplate)
        return worldTemplate
    }

    fun getTemplateFromName(parrotGroup: IParrotGroup, slimeWorldTemplateName: String): WorldTemplate? {
        return getTemplatesFromParrotGroup(parrotGroup).firstOrNull { it.slimeWorldTemplateName == slimeWorldTemplateName }
    }

    fun getTemplatesFromParrotGroup(parrotGroup: IParrotGroup): List<WorldTemplate> {
        return this.templates.filter { it.parrotGroup == parrotGroup }
    }

}