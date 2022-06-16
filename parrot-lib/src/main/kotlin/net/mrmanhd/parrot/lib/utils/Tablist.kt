package net.mrmanhd.parrot.lib.utils

import eu.thesimplecloud.api.CloudAPI
import eu.thesimplecloud.api.player.ICloudPlayer
import net.mrmanhd.parrot.api.extension.getParrotService
import java.io.Serializable

/**
 * Created by MrManHD
 * Class create at 15.06.2022 23:09
 */

class Tablist(
    private val header: List<String>,
    private val footer: List<String>
) : Serializable {

    fun sendTablist(cloudPlayer: ICloudPlayer) {
        val headers = this.header.map { replaceStringToPlaceholder(cloudPlayer, it) }.toTypedArray()
        val footers = this.footer.map { replaceStringToPlaceholder(cloudPlayer, it) }.toTypedArray()
        cloudPlayer.sendTablist(headers, footers)
    }

    private fun replaceStringToPlaceholder(cloudPlayer: ICloudPlayer, string: String): String {
        val parrotService = cloudPlayer.getParrotService()
        val connectedProxy = cloudPlayer.getConnectedProxy()
        val connectedServer = cloudPlayer.getConnectedServer()
        return string
            .replace("%ONLINE_PLAYERS%", connectedServer?.getOnlineCount().toString())
            .replace("%MAX_PLAYERS%", connectedProxy?.getMaxPlayers().toString())
            .replace("%PARROT_SERVICE%", parrotService?.getName() ?: "?")
            .replace("%SERVER%", connectedServer?.getName() ?: "?")
            .replace("%PARROT_GROUP%", parrotService?.getGroupName() ?: "?")
    }

}