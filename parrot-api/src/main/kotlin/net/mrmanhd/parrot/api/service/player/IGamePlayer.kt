package net.mrmanhd.parrot.api.service.player

import java.util.*

/**
 * Created by MrManHD
 * Class create at 10.06.2022 12:34
 */

interface IGamePlayer {

    fun getUniqueId(): UUID

    fun getName(): String

    fun getState(): PlayerState

    fun createdAt(): Long

}