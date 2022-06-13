package net.mrmanhd.parrot.lib.repository.info

import eu.thesimplecloud.jsonlib.JsonLib
import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.api.service.state.ServiceState
import net.mrmanhd.parrot.api.utils.ParrotLocation
import net.mrmanhd.parrot.lib.Parrot
import net.mrmanhd.parrot.lib.api.ParrotLib
import net.mrmanhd.parrot.lib.api.group.ParrotGroup
import net.mrmanhd.parrot.lib.api.service.ParrotService
import net.mrmanhd.parrot.lib.api.service.player.GamePlayer
import java.io.Serializable
import java.util.UUID

/**
 * Created by MrManHD
 * Class create at 10.06.2022 22:22
 */

class ParrotServiceInfo(
    val uniqueId: UUID,
    val parrotGroup: ParrotGroup,
    val cloudServiceName: String,
    val name: String,
    val createdAt: Long,
    val owner: UUID?,
    val privateService: Boolean,
    val removeWhenServiceEmpty: Boolean,
    var motd: String,
    var maxPlayers: Int,
    val propertyMap: HashMap<String, Any>,
    val spawnLocation: ParrotLocation
) : Serializable {

    val gamePlayers = arrayListOf<GamePlayer>()
    val preConnectedPlayers = arrayListOf<UUID>()

    var state = ServiceState.STARTING

    fun convertToJsonString(): String {
        return JsonLib.fromObject(this).getAsJsonString()
    }

    fun convertToParrotService(): ParrotService {
        return ParrotService(
            this.uniqueId,
            this.parrotGroup,
            this.name,
            this.owner,
            this.privateService,
            this.removeWhenServiceEmpty,
            this.cloudServiceName,
            this.spawnLocation,
            this.createdAt
        )
    }

    fun update() {
        val localServiceHandler = ParrotLib.instance.localServiceHandler
        localServiceHandler.updateLocalService(convertToParrotService())

        Parrot.instance.parrotServiceRepository.insert(this.uniqueId, this)
    }

}