package net.mrmanhd.parrot.api.extension

import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.api.service.IParrotService
import org.bukkit.Bukkit
import org.bukkit.entity.Player

/**
 * Created by MrManHD
 * Class create at 13.06.2022 14:09
 */
 
fun Player.getParrotService(): IParrotService? {
    return ParrotApi.instance.getServiceHandler().getServiceByPlayer(this.uniqueId)
}

fun Player.connectToService(parrotService: IParrotService) {
    parrotService.connectPlayer(this.uniqueId)
}

fun IParrotService.getBukkitGamePlayers(): List<Player> {
    return this.getGamePlayers().mapNotNull { Bukkit.getPlayer(it.getUniqueId()) }
}