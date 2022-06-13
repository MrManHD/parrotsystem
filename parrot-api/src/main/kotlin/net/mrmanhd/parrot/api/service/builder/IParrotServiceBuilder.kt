package net.mrmanhd.parrot.api.service.builder

import eu.thesimplecloud.api.service.ICloudService
import eu.thesimplecloud.clientserverapi.lib.promise.ICommunicationPromise
import net.mrmanhd.parrot.api.service.IParrotService
import net.mrmanhd.parrot.api.utils.ParrotLocation
import java.util.UUID

/**
 * Created by MrManHD
 * Class create at 10.06.2022 11:34
 */

interface IParrotServiceBuilder {

    fun withMaxPlayers(maxPlayers: Int): IParrotServiceBuilder

    fun withSpawnLocation(spawnLocation: ParrotLocation): IParrotServiceBuilder

    fun withMotd(motd: String): IParrotServiceBuilder

    fun withPrivateService(): IParrotServiceBuilder

    fun withCloudService(cloudService: ICloudService): IParrotServiceBuilder

    fun withProperty(key: String, value: Any): IParrotServiceBuilder

    fun withOwner(playerUniqueId: UUID): IParrotServiceBuilder

    fun withRemoveWhenServiceEmpty(): IParrotServiceBuilder

    fun startService(): ICommunicationPromise<IParrotService>

}