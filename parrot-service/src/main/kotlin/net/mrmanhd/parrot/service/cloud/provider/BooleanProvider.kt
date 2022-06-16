package net.mrmanhd.parrot.service.cloud.provider

import eu.thesimplecloud.api.command.ICommandSender
import eu.thesimplecloud.launcher.console.command.provider.ICommandSuggestionProvider

/**
 * Created by MrManHD
 * Class create at 16.06.2022 21:19
 */

class BooleanProvider : ICommandSuggestionProvider {
    override fun getSuggestions(sender: ICommandSender, fullCommand: String, lastArgument: String): List<String> {
        return listOf("true", "false")
    }
}