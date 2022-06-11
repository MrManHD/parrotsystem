package net.mrmanhd.parrot.lib.repository

import net.mrmanhd.parrot.api.group.IParrotGroup
import net.mrmanhd.parrot.api.hazelcast.AbstractHazelcastHashMapRepository
import net.mrmanhd.parrot.lib.repository.info.ParrotGroupInfo

/**
 * Created by MrManHD
 * Class create at 10.06.2022 22:21
 */

class ParrotGroupRepository : AbstractHazelcastHashMapRepository<String, ParrotGroupInfo>(
    "parrotGroups"
) {

    fun getAllParrotGroups(): List<IParrotGroup> {
        return findAll().map { it.convertToParrotGroup() }
    }

}