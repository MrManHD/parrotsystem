package net.mrmanhd.parrot.lib.api.service.builder

import eu.thesimplecloud.api.CloudAPI
import eu.thesimplecloud.api.service.ICloudService
import eu.thesimplecloud.clientserverapi.lib.promise.ICommunicationPromise
import net.mrmanhd.parrot.api.group.IParrotGroup
import net.mrmanhd.parrot.api.service.IParrotService
import net.mrmanhd.parrot.api.service.builder.IParrotServiceBuilder
import net.mrmanhd.parrot.api.utils.ParrotLocation
import net.mrmanhd.parrot.api.utils.Variant
import net.mrmanhd.parrot.lib.api.ParrotLib
import net.mrmanhd.parrot.lib.api.service.ParrotServiceCreator
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
    var spawnLocation = parrotGroup.getSpawnLocation()
    var variant: Variant? = null

    override fun withMaxPlayers(maxPlayers: Int): IParrotServiceBuilder {
        this.maxPlayers = maxPlayers
        return this
    }

    override fun withSpawnLocation(spawnLocation: ParrotLocation): IParrotServiceBuilder {
        this.spawnLocation = spawnLocation
        return this
    }

    override fun withMotd(motd: String): IParrotServiceBuilder {
        this.motd = motd
        return this
    }

    override fun withVariant(variant: Variant): IParrotServiceBuilder {
        this.variant = variant
        return this
    }

    override fun withPrivateService(): IParrotServiceBuilder {
        this.isPrivateService = true
        return this
    }

    override fun withCloudService(cloudService: ICloudService): IParrotServiceBuilder {
        this.cloudServiceName = cloudService.getName()
        return this
    }

    override fun withProperty(key: String, value: Any): IParrotServiceBuilder {
        this.propertyMap[key] = value
        return this
    }

    override fun withOwner(playerUniqueId: UUID): IParrotServiceBuilder {
        this.owner = playerUniqueId
        return this
    }

    override fun withRemoveWhenServiceEmpty(): IParrotServiceBuilder {
        this.isRemoveWhenServiceEmpty = true
        return this
    }

    override fun startService(): ICommunicationPromise<IParrotService> {
        return ParrotServiceCreator(this).start()
    }

    fun getCloudService(): ICloudService? {
        return this.cloudServiceName?.let { CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(it) }
    }
}