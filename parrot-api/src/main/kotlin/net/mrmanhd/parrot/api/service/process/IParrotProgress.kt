package net.mrmanhd.parrot.api.service.process

import eu.thesimplecloud.api.service.ICloudService
import eu.thesimplecloud.clientserverapi.lib.promise.ICommunicationPromise
import net.mrmanhd.parrot.api.service.IParrotService
import java.util.UUID

/**
 * Created by MrManHD
 * Class create at 10.06.2022 11:34
 */

interface IParrotProgress {

    fun maxPlayers(maxPlayers: Int): IParrotProgress

    fun motd(motd: String): IParrotProgress

    fun privateService(): IParrotProgress

    fun cloudService(cloudService: ICloudService): IParrotProgress

    fun property(key: String, value: Any): IParrotProgress

    fun owner(playerUniqueId: UUID): IParrotProgress

    fun removeWhenServiceEmpty(): IParrotProgress

    fun startService(): ICommunicationPromise<IParrotService>

}