package net.mrmanhd.parrot.daemon.group

import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.api.group.IParrotGroup
import net.mrmanhd.parrot.lib.Parrot
import net.mrmanhd.parrot.lib.extension.writeMessage
import java.io.File

/**
 * Created by MrManHD
 * Class create at 18.06.2022 15:59
 */

class ParrotTemplateGroupHandler {

    fun loadTemplateGroups() {
        val allGroups = ParrotApi.instance.getGroupHandler().getAllGroups()
        allGroups.forEach { registerTemplateGroup(it) }
    }

    private fun registerTemplateGroup(parrotGroup: IParrotGroup) {
        val templateGroup = ParrotTemplateGroup(parrotGroup, File("parrot/${parrotGroup.getName()}"))
        templateGroup.getWorldNames().forEach { Parrot.instance.worldTemplateHandler.loadTemplate(parrotGroup, it) }
        parrotGroup.getPreLoadedWorlds().forEach { loadPreLoadingWorld(parrotGroup, it) }
    }

    private fun loadPreLoadingWorld(parrotGroup: IParrotGroup, slimeWorldTemplateName: String) {
        writeMessage("[Parrot] Loading globalWorld $slimeWorldTemplateName for ${parrotGroup.getName()}")
        Parrot.instance.worldHandler.loadGlobalSlimeWorld(
            parrotGroup,
            slimeWorldTemplateName,
            "${parrotGroup.getName()}_$slimeWorldTemplateName"
        )
    }
}