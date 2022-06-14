package net.mrmanhd.parrot.lib.connection

import net.mrmanhd.parrot.api.extension.getBukkitGamePlayers
import net.mrmanhd.parrot.api.service.player.PlayerState
import net.mrmanhd.parrot.api.service.state.ServiceState
import net.mrmanhd.parrot.lib.api.service.ParrotService
import net.mrmanhd.parrot.lib.extension.sendMessage
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

/**
 * Created by MrManHD
 * Class create at 13.06.2022 13:10
 */

abstract class AbstractDefaultPlayerConnector(
    private val parrotService: ParrotService
) {

    fun handlePlayerAdd(player: Player) {
        if (this.parrotService.isPlayerOnline(player.uniqueId)) {
            sendMessage("connector.failed.player.already.connected", player.name, player.uniqueId, this.parrotService.getName())
            return
        }

        sendMessage("connector.starting", player.name, player.uniqueId, this.parrotService.getName(), this.parrotService.getGroupName())

        when (this.parrotService.getState()) {
            ServiceState.INGAME -> this.parrotService.addGamePlayer(player.uniqueId, player.name, PlayerState.SPECTATOR)
            else -> this.parrotService.addGamePlayer(player.uniqueId, player.name, PlayerState.LOBBY)
        }

        player.inventory.clear()
        player.inventory.armorContents = emptyArray()

        player.health = 20.0
        player.foodLevel = 20
    }

    fun updatePlayerVisible(player: Player) {
        val plugin = Bukkit.getPluginManager().plugins.firstOrNull()!!
        val list = this.parrotService.getBukkitGamePlayers()

        Bukkit.getOnlinePlayers().filter { it.name != player.name }.filter { !list.contains(it) }.forEach {
            player.hidePlayer(plugin, it)
            it.hidePlayer(plugin, player)
        }

        object : BukkitRunnable() {
            override fun run() {
                list.forEach {
                    player.showPlayer(plugin, it)
                    it.showPlayer(plugin, player)
                }
            }
        }.runTaskLater(plugin, 3)
    }

}