package net.mrmanhd.parrot.lib.repository

import net.mrmanhd.parrot.api.hazelcast.AbstractHazelcastHashMapRepository

/**
 * Created by MrManHD
 * Class create at 12.06.2022 20:07
 */

class ChatMessageRepository : AbstractHazelcastHashMapRepository<String, String>(
    "chatMessages"
)