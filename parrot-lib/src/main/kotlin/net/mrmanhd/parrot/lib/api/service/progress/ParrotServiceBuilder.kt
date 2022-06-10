package net.mrmanhd.parrot.lib.api.service.progress

import eu.thesimplecloud.api.CloudAPI
import eu.thesimplecloud.api.service.ICloudService
import eu.thesimplecloud.clientserverapi.lib.promise.ICommunicationPromise
import net.mrmanhd.parrot.api.group.IParrotGroup
import net.mrmanhd.parrot.api.service.IParrotService
import net.mrmanhd.parrot.api.service.process.IParrotServiceBuilder
import net.mrmanhd.parrot.lib.api.ParrotLib
import java.util.*

/**
 * Created by MrManHD
 * Class create at 10.06.2022 12:52
 */

class ParrotServiceBuilder(
    val parrotGroup: IParrotGroup
) : IParrotServiceBuilder {

    var maxPlayers = 20
    var motd = "Another Parrot Service"
    var isPrivateService = false
    var cloudServiceName: String? = null
    var propertyMap = hashMapOf<String, Any>()
    var owner: UUID? = null
    var isRemoveWhenServiceEmpty = false

    override fun maxPlayers(maxPlayers: Int): IParrotServiceBuilder {
        this.maxPlayers = maxPlayers
        return this
    }

    override fun motd(motd: String): IParrotServiceBuilder {
        this.motd = motd
        return this
    }

    override fun privateService(): IParrotServiceBuilder {
        this.isPrivateService = true
        return this
    }

    override fun cloudService(cloudService: ICloudService): IParrotServiceBuilder {
        this.cloudServiceName = cloudService.getName()
        return this
    }

    override fun property(key: String, value: Any): IParrotServiceBuilder {
        this.propertyMap[key] = value
        return this
    }

    override fun owner(playerUniqueId: UUID): IParrotServiceBuilder {
        this.owner = playerUniqueId
        return this
    }

    override fun removeWhenServiceEmpty(): IParrotServiceBuilder {
        this.isRemoveWhenServiceEmpty = true
        return this
    }

    override fun startService(): ICommunicationPromise<IParrotService> {
        return ParrotLib.instance.serviceHandler.startService(this)
    }

    fun getCloudService(): ICloudService? {
        return this.cloudServiceName?.let { CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(it) }
    }
}