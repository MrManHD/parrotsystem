package net.mrmanhd.parrot.lib.api.service.progress

import eu.thesimplecloud.api.service.ICloudService
import eu.thesimplecloud.clientserverapi.lib.promise.ICommunicationPromise
import net.mrmanhd.parrot.api.group.IParrotGroup
import net.mrmanhd.parrot.api.service.IParrotService
import net.mrmanhd.parrot.api.service.process.IParrotProgress
import net.mrmanhd.parrot.lib.api.ParrotLib
import java.util.*

/**
 * Created by MrManHD
 * Class create at 10.06.2022 12:52
 */

class ParrotProgress(
    val parrotGroup: IParrotGroup
) : IParrotProgress {

    var maxPlayers = 20
    var motd = "Another Parrot Service"
    var isPrivateService = false
    var cloudServiceName: String? = null
    var propertyMap = hashMapOf<String, Any>()
    var owner: UUID? = null
    var isRemoveWhenServiceEmpty = false

    override fun maxPlayers(maxPlayers: Int): IParrotProgress {
        this.maxPlayers = maxPlayers
        return this
    }

    override fun motd(motd: String): IParrotProgress {
        this.motd = motd
        return this
    }

    override fun privateService(): IParrotProgress {
        this.isPrivateService = true
        return this
    }

    override fun cloudService(cloudService: ICloudService): IParrotProgress {
        this.cloudServiceName = cloudService.getName()
        return this
    }

    override fun property(key: String, value: Any): IParrotProgress {
        this.propertyMap[key] = value
        return this
    }

    override fun owner(playerUniqueId: UUID): IParrotProgress {
        this.owner = playerUniqueId
        return this
    }

    override fun removeWhenServiceEmpty(): IParrotProgress {
        this.isRemoveWhenServiceEmpty = true
        return this
    }

    override fun startService(): ICommunicationPromise<IParrotService> {
        return ParrotLib.instance.serviceHandler.startService(this)
    }
}