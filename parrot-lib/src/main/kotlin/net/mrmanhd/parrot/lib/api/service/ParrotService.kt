package net.mrmanhd.parrot.lib.api.service

import net.mrmanhd.parrot.api.group.IParrotGroup
import net.mrmanhd.parrot.api.service.IParrotService
import net.mrmanhd.parrot.api.service.player.IGamePlayer
import net.mrmanhd.parrot.api.service.player.PlayerState
import net.mrmanhd.parrot.api.service.state.ServiceState
import net.mrmanhd.parrot.lib.Parrot
import net.mrmanhd.parrot.lib.api.group.ParrotGroup
import net.mrmanhd.parrot.lib.repository.info.ParrotServiceInfo
import java.util.*

/**
 * Created by MrManHD
 * Class create at 10.06.2022 19:29
 */

class ParrotService(
    private val uniqueId: UUID,
    private val parrotGroup: ParrotGroup,
    private val name: String,
    private val owner: UUID?,
    private val privateService: Boolean,
    private val removeWhenServiceEmpty: Boolean,
    private val cloudServiceName: String,
    private val createdAt: Long = System.currentTimeMillis()
) : IParrotService {

    override fun getUniqueId(): UUID = this.uniqueId

    override fun getGroup(): IParrotGroup {
        return this.parrotGroup
    }

    override fun getName(): String = this.name

    override fun createdAt(): Long = this.createdAt

    override fun getCloudServiceName(): String = this.cloudServiceName

    override fun getOwner(): UUID? {
        return this.owner
    }

    override fun isPrivate(): Boolean {
        return this.privateService
    }

    override fun isRemoveWhenServiceEmpty(): Boolean {
        return this.removeWhenServiceEmpty
    }

    override fun getMotd(): String {
        TODO("Not yet implemented")
    }

    override fun setMotd(motd: String) {
        TODO("Not yet implemented")
    }

    override fun getState(): ServiceState {
        TODO("Not yet implemented")
    }

    override fun setState(state: ServiceState) {
        TODO("Not yet implemented")
    }

    override fun getProperty(key: String): Any? {
        TODO("Not yet implemented")
    }

    override fun setProperty(key: String, value: Any) {
        TODO("Not yet implemented")
    }

    override fun getGamePlayers(): List<IGamePlayer> {
        TODO("Not yet implemented")
    }

    override fun updateGamePlayer(uniqueId: UUID, playerState: PlayerState): IGamePlayer {
        TODO("Not yet implemented")
    }

    override fun getPreConnectedPlayers(): List<UUID> {
        TODO("Not yet implemented")
    }

    override fun getMaxPlayers(): Int {
        TODO("Not yet implemented")
    }

    override fun setMaxPlayers(maxPlayers: Int) {
        TODO("Not yet implemented")
    }

    override fun loadWorld(slimeWorldName: String) {
        TODO("Not yet implemented")
    }

    override fun unloadWorld(slimeWorldName: String) {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }

    override fun update() {
        getInfo()?.update()
    }

    override fun toJsonString(): String {
        return getInfo()?.convertToJsonString() ?: "{}"
    }

    private fun getInfo(): ParrotServiceInfo? {
        return Parrot.instance.parrotServiceRepository.find(this.uniqueId)
    }

}