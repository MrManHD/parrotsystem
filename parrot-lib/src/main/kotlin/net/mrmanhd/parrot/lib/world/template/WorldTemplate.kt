package net.mrmanhd.parrot.lib.world.template

import com.grinderwolf.swm.api.SlimePlugin
import com.grinderwolf.swm.api.loaders.SlimeLoader
import com.grinderwolf.swm.api.world.SlimeWorld
import com.grinderwolf.swm.api.world.properties.SlimePropertyMap
import net.mrmanhd.parrot.api.group.IParrotGroup
import net.mrmanhd.parrot.lib.extension.debugMessage
import net.mrmanhd.parrot.lib.extension.writeMessage
import org.bukkit.Bukkit
import java.io.File
import java.lang.Exception

/**
 * Created by MrManHD
 * Class create at 16.06.2022 23:59
 */

class WorldTemplate(
    val parrotGroup: IParrotGroup,
    val slimeWorldTemplateName: String
) {

    private val slimeWorld: SlimeWorld

    init {
        val slimePlugin = Bukkit.getPluginManager().getPlugin("SlimeWorldManager") as SlimePlugin
        val slimeLoader = slimePlugin.getLoader("file")

        writeMessage("[Parrot] Load Worldtemplate $slimeWorldTemplateName")
        debugMessage("debug.world.template.load", slimeWorldTemplateName)

        importSlimeWorld(slimePlugin, slimeLoader)
        slimeWorld = slimePlugin.loadWorld(slimeLoader, "${parrotGroup.getName()}-$slimeWorldTemplateName", true, SlimePropertyMap())
    }

    fun getSlimeWorldTemplate(): SlimeWorld = this.slimeWorld

    private fun importSlimeWorld(slimePlugin: SlimePlugin, loader: SlimeLoader) {
        try {
            val file = File("parrot/${parrotGroup.getName()}/worlds/$slimeWorldTemplateName")
            slimePlugin.importWorld(file, "${parrotGroup.getName()}-$slimeWorldTemplateName", loader)
        } catch (exception: Exception) {
            debugMessage("debug.world.template.failed.import", this.slimeWorldTemplateName)
        }
    }

}