package net.mrmanhd.parrot.api.group

import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.api.service.IParrotService
import net.mrmanhd.parrot.api.service.builder.IParrotServiceBuilder
import net.mrmanhd.parrot.api.utils.ParrotLocation

/**
 * Created by MrManHD
 * Class create at 10.06.2022 11:36
 */

interface IParrotGroup {

    fun getName(): String

    fun getPreLoadedWorlds(): List<String>

    fun getMinimumOnlineServiceCount(): Int

    fun getStartingGroupNames(): List<String>

    fun isInMaintenance(): Boolean

    fun getSpawnLocation(): ParrotLocation

    fun getAllServices(): List<IParrotService> {
        return ParrotApi.instance.getServiceHandler().getAllServicesByGroup(this)
    }

    fun getOnlinePlayersCount(): Int {
        return getAllServices().sumOf { it.getOnlineCount() }
    }

    fun createService(): IParrotServiceBuilder {
        return ParrotApi.instance.getServiceHandler().createService(this)
    }

}