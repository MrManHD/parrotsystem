package net.mrmanhd.parrot.lib.repository

import net.mrmanhd.parrot.api.hazelcast.AbstractHazelcastHashMapRepository
import net.mrmanhd.parrot.lib.repository.info.ConfigInfo

/**
 * Created by MrManHD
 * Class create at 13.06.2022 00:43
 */

class ConfigRepository : AbstractHazelcastHashMapRepository<String, ConfigInfo>(
    "config"
) {

    fun getConfig(): ConfigInfo {
        return find("config")!!
    }

}