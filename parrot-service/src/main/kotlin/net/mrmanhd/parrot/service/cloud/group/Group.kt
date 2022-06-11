package net.mrmanhd.parrot.service.cloud.group

import eu.thesimplecloud.api.utils.Nameable
import net.mrmanhd.parrot.lib.repository.info.ParrotGroupInfo

/**
 * Created by MrManHD
 * Class create at 11.06.2022 19:32
 */

class Group(
    val groupName: String,
    val preLoadedWorlds: List<String>,
    val minimumOnlineServiceCount: Int,
    val maxOnlineServiceCount: Int,
    val maintenance: Boolean
) : Nameable {

    override fun getName(): String = this.groupName

    fun convertToParrotGroupInfo(): ParrotGroupInfo {
        return ParrotGroupInfo(
            this.groupName,
            this.preLoadedWorlds,
            this.minimumOnlineServiceCount,
            this.maxOnlineServiceCount,
            this.maintenance
        )
    }

}