package net.mrmanhd.parrot.lib.api.service.player

import net.mrmanhd.parrot.api.service.player.IGamePlayer
import net.mrmanhd.parrot.api.service.player.PlayerState
import java.util.*

/**
 * Created by MrManHD
 * Class create at 12.06.2022 12:53
 */

class GamePlayer(
    private val uniqueId: UUID,
    private val name: String,
    private val playerState: PlayerState,
    private val createdAt: Long = System.currentTimeMillis()
) : IGamePlayer {

    override fun getUniqueId(): UUID = this.uniqueId

    override fun getName(): String = this.name

    override fun getState(): PlayerState = this.playerState

    override fun createdAt(): Long = this.createdAt

}