package net.mrmanhd.parrot.lib.messagechannel.dto

import eu.thesimplecloud.api.CloudAPI
import eu.thesimplecloud.api.player.ICloudPlayer
import eu.thesimplecloud.clientserverapi.lib.promise.ICommunicationPromise
import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.api.service.IParrotService
import java.util.UUID

/**
 * Created by MrManHD
 * Class create at 12.06.2022 20:59
 */

class PlayerConnectorDTO(
    private val uniqueId: UUID,
    val playerAlreadyOnSameService: Boolean,
    private val parrotServiceUniqueId: UUID
) {

    fun getParrotService(): IParrotService? {
        return ParrotApi.instance.getServiceHandler().getServiceByUniqueId(this.parrotServiceUniqueId)
    }

    fun getConnectedParrotServiceByPlayer(): IParrotService? {
        return ParrotApi.instance.getServiceHandler().getServiceByPlayer(this.uniqueId)
    }

    fun getCloudPlayer(): ICommunicationPromise<ICloudPlayer> {
        return CloudAPI.instance.getCloudPlayerManager().getCloudPlayer(this.uniqueId)
    }

}