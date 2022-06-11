package net.mrmanhd.parrot.lib.repository.info

import eu.thesimplecloud.jsonlib.JsonLib
import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.lib.Parrot
import net.mrmanhd.parrot.lib.api.group.ParrotGroup
import net.mrmanhd.parrot.lib.api.service.ParrotService
import java.io.Serializable
import java.util.UUID

/**
 * Created by MrManHD
 * Class create at 10.06.2022 22:22
 */

class ParrotServiceInfo(
    val uniqueId: UUID,
    val parrotGroup: ParrotGroupInfo,
    val cloudServiceName: String,
    val name: String,
    val createdAt: Long,
    val owner: UUID?,
    val privateService: Boolean,
    val removeWhenServiceEmpty: Boolean
) : Serializable {

    fun convertToJsonString(): String {
        return JsonLib.fromObject(this).getAsJsonString()
    }

    fun convertToParrotService(): ParrotService {
        return ParrotService(
            this.uniqueId,
            this.parrotGroup.convertToParrotGroup(),
            this.name,
            this.owner,
            this.privateService,
            this.removeWhenServiceEmpty,
            this.cloudServiceName,
            this.createdAt
        )
    }

    fun update() {
        Parrot.instance.parrotServiceRepository.insert(this.uniqueId, this)
    }

}