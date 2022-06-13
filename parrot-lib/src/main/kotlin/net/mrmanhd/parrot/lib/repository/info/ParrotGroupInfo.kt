package net.mrmanhd.parrot.lib.repository.info

import net.mrmanhd.parrot.api.utils.ParrotLocation
import net.mrmanhd.parrot.lib.Parrot
import net.mrmanhd.parrot.lib.api.group.ParrotGroup
import java.io.Serializable

/**
 * Created by MrManHD
 * Class create at 11.06.2022 19:22
 */

class ParrotGroupInfo(
    val name: String,
    val preLoadedWorlds: List<String>,
    val minimumOnlineServiceCount: Int,
    val maxOnlineServiceCount: Int,
    val maintenance: Boolean,
    val spawnLocation: ParrotLocation
) : Serializable {

    fun convertToParrotGroup(): ParrotGroup {
        return ParrotGroup(
            this.name,
            this.preLoadedWorlds,
            this.minimumOnlineServiceCount,
            this.maxOnlineServiceCount,
            this.maintenance,
            this.spawnLocation
        )
    }

    fun update() {
        Parrot.instance.parrotGroupRepository.insert(this.name, this)
    }

}