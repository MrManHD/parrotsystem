package net.mrmanhd.parrot.api.service.process

import eu.thesimplecloud.api.service.ICloudService
import eu.thesimplecloud.clientserverapi.lib.promise.ICommunicationPromise
import net.mrmanhd.parrot.api.service.IParrotService
import java.util.UUID

/**
 * Created by MrManHD
 * Class create at 10.06.2022 11:34
 */

interface IParrotServiceBuilder {

    fun maxPlayers(maxPlayers: Int): IParrotServiceBuilder

    fun motd(motd: String): IParrotServiceBuilder

    fun privateService(): IParrotServiceBuilder

    fun cloudService(cloudService: ICloudService): IParrotServiceBuilder

    fun property(key: String, value: Any): IParrotServiceBuilder

    fun owner(playerUniqueId: UUID): IParrotServiceBuilder

    fun removeWhenServiceEmpty(): IParrotServiceBuilder

    fun startService(): ICommunicationPromise<IParrotService>

}