package net.mrmanhd.parrot.api.service

import eu.thesimplecloud.api.CloudAPI
import eu.thesimplecloud.api.service.ICloudService
import net.mrmanhd.parrot.api.group.IParrotGroup
import net.mrmanhd.parrot.api.service.player.IGamePlayer
import net.mrmanhd.parrot.api.service.player.PlayerState
import net.mrmanhd.parrot.api.service.state.ServiceState
import net.mrmanhd.parrot.api.utils.ParrotLocation
import org.bukkit.Bukkit.getMaxPlayers
import java.util.UUID

/**
 * Created by MrManHD
 * Class create at 10.06.2022 11:29
 */

interface IParrotService {

    fun getUniqueId(): UUID

    fun getGroup(): IParrotGroup

    fun getGroupName(): String {
        return getGroup().getName()
    }

    fun getName(): String

    fun createdAt(): Long

    fun getSpawnLocation(): ParrotLocation


    fun getCloudServiceName(): String

    fun getCloudService(): ICloudService? {
        return CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(getCloudServiceName())
    }

    fun getOwner(): UUID?

    fun isPrivate(): Boolean

    fun isRemoveWhenServiceEmpty(): Boolean


    fun getMotd(): String

    fun setMotd(motd: String)


    fun getState(): ServiceState

    fun setState(state: ServiceState)


    fun isReadyToUse(): Boolean {
        return getState() != ServiceState.STARTING
    }

    fun isInPlayingState(): Boolean {
        return getState() == ServiceState.INGAME || getState() == ServiceState.ENDING
    }


    fun getProperties(): Map<String, Any>

    fun getProperty(key: String): Any? {
        return getProperties()[key]
    }

    fun hasProperty(key: String): Boolean {
        return getProperty(key) != null
    }

    fun setProperty(key: String, value: Any)

    fun removeProperty(key: String)


    fun getGamePlayers(): List<IGamePlayer>

    fun getGamePlayer(uniqueId: UUID): IGamePlayer? {
        return getGamePlayers().firstOrNull { it.getUniqueId() == uniqueId }
    }

    fun updateGamePlayer(uniqueId: UUID, playerState: PlayerState): IGamePlayer?

    fun isPlayerOnline(uniqueId: UUID): Boolean {
        return getGamePlayer(uniqueId) != null
    }


    fun getPreConnectedPlayers(): List<UUID>

    fun getPreConnectedPlayer(uniqueId: UUID): UUID? {
        return getPreConnectedPlayers().firstOrNull { it == uniqueId }
    }

    fun isPlayerPreConnected(uniqueId: UUID): Boolean {
        return getPreConnectedPlayer(uniqueId) != null
    }


    fun getMaxPlayers(): Int

    fun setMaxPlayers(maxPlayers: Int)

    fun getOnlineCount(): Int = getGamePlayers().size


    fun isFull(): Boolean {
        return getOnlineCount() >= getMaxPlayers()
    }

    fun isEnding(): Boolean {
        return getState() == ServiceState.ENDING
    }


    fun loadWorld(slimeWorldName: String)

    fun unloadWorld(slimeWorldName: String)


    fun stop()

    fun update()

    fun toJsonString(): String

}