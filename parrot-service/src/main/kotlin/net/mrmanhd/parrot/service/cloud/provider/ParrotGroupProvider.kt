package net.mrmanhd.parrot.service.cloud.provider

import eu.thesimplecloud.api.command.ICommandSender
import eu.thesimplecloud.launcher.console.command.provider.ICommandSuggestionProvider
import net.mrmanhd.parrot.api.ParrotApi

/**
 * Created by MrManHD
 * Class create at 16.06.2022 21:15
 */

class ParrotGroupProvider : ICommandSuggestionProvider {
    override fun getSuggestions(sender: ICommandSender, fullCommand: String, lastArgument: String): List<String> {
        return ParrotApi.instance.getGroupHandler().getAllGroups().map { it.getName() }
    }
}