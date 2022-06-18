package net.mrmanhd.parrot.service.cloud.group

import eu.thesimplecloud.api.utils.Nameable
import net.mrmanhd.parrot.api.utils.ParrotLocation
import net.mrmanhd.parrot.lib.repository.info.ParrotGroupInfo

/**
 * Created by MrManHD
 * Class create at 11.06.2022 19:32
 */

class Group(
    val groupName: String,
    val preLoadedWorlds: List<String>,
    val minimumOnlineServiceCount: Int,
    val maintenance: Boolean,
    val spawnLocation: ParrotLocation
) : Nameable {

    override fun getName(): String = this.groupName

    fun convertToParrotGroupInfo(): ParrotGroupInfo {
        return ParrotGroupInfo(
            this.groupName,
            this.preLoadedWorlds,
            this.minimumOnlineServiceCount,
            this.maintenance,
            this.spawnLocation
        )
    }

}