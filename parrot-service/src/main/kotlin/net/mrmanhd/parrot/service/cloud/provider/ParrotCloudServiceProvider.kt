package net.mrmanhd.parrot.service.cloud.provider

import eu.thesimplecloud.api.command.ICommandSender
import eu.thesimplecloud.launcher.console.command.provider.ICommandSuggestionProvider
import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.lib.Parrot

/**
 * Created by MrManHD
 * Class create at 16.06.2022 21:15
 */

class ParrotCloudServiceProvider : ICommandSuggestionProvider {
    override fun getSuggestions(sender: ICommandSender, fullCommand: String, lastArgument: String): List<String> {
        return Parrot.instance.configRepository.getConfig().getStartGroupNames()
            .map { it.getAllServices() }
            .flatten()
            .map { it.getName() }
    }
}