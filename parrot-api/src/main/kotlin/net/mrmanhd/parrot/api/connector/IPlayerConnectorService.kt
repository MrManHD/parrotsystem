package net.mrmanhd.parrot.api.connector

import net.mrmanhd.parrot.api.service.IParrotService
import java.util.UUID

/**
 * Created by MrManHD
 * Class create at 12.06.2022 18:31
 */

interface IPlayerConnectorService {

    fun connectPlayerToService(uniqueId: UUID, parrotService: IParrotService)

}