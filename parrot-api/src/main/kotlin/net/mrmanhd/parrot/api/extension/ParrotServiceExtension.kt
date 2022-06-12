package net.mrmanhd.parrot.api.extension

import net.mrmanhd.parrot.api.service.IParrotService
import org.bukkit.Bukkit
import org.bukkit.entity.Player

/**
 * Created by MrManHD
 * Class create at 12.06.2022 18:23
 */

fun IParrotService.getBukkitGamePlayers(): List<Player> {
    return this.getGamePlayers().mapNotNull { Bukkit.getPlayer(it.getUniqueId()) }
}