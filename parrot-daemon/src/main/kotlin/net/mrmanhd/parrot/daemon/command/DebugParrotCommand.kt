package net.mrmanhd.parrot.daemon.command

import dev.triumphteam.cmd.bukkit.annotation.Permission
import dev.triumphteam.cmd.core.BaseCommand
import dev.triumphteam.cmd.core.annotation.Command
import dev.triumphteam.cmd.core.annotation.Default
import eu.thesimplecloud.api.CloudAPI
import net.mrmanhd.parrot.lib.extension.sendChatMessage
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * Created by MrManHD
 * Class create at 15.06.2022 12:22
 */

@Command("debugparrot")
class DebugParrotCommand : BaseCommand() {

    @Default
    @Permission("parrot.command.debugparrot")
    fun execute(sender: CommandSender) {

        val propertyHolder = CloudAPI.instance.getGlobalPropertyHolder()
        if (propertyHolder.hasProperty("parrot-debugmessages")) {
            propertyHolder.removeProperty("parrot-debugmessages")
        } else {
            propertyHolder.setProperty("parrot-debugmessages", true)
        }

        sender.sendChatMessage("debug.command.update")
    }

}