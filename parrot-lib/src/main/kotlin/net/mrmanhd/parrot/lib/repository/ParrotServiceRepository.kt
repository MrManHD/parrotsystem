package net.mrmanhd.parrot.lib.repository

import net.mrmanhd.parrot.api.hazelcast.AbstractHazelcastHashMapRepository
import net.mrmanhd.parrot.api.service.IParrotService
import net.mrmanhd.parrot.lib.repository.info.ParrotServiceInfo
import java.util.UUID

/**
 * Created by MrManHD
 * Class create at 10.06.2022 22:21
 */

class ParrotServiceRepository : AbstractHazelcastHashMapRepository<UUID, ParrotServiceInfo>(
    "parrotServices"
) {

    fun getAllParrotServices(): List<IParrotService> {
        return findAll().map { it.convertToParrotService() }
    }

}