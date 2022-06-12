package net.mrmanhd.parrot.lib.api.service

import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.api.group.IParrotGroup
import net.mrmanhd.parrot.api.service.ILocalServiceHandler
import net.mrmanhd.parrot.api.service.IParrotService
import net.mrmanhd.parrot.api.service.builder.IParrotServiceBuilder
import java.util.UUID

/**
 * Created by MrManHD
 * Class create at 12.06.2022 18:11
 */

class LocalServiceHandler : ILocalServiceHandler {

    private val localServices = arrayListOf<IParrotService>()

    override fun createService(parrotGroup: IParrotGroup): IParrotServiceBuilder {
        return ParrotApi.instance.getServiceHandler().createService(parrotGroup)
    }

    override fun getAllServices(): List<IParrotService> = this.localServices

    fun updateLocalService(parrotService: IParrotService) {
        if (existLocalService(parrotService.getUniqueId())) {
            addLocalService(parrotService)
        }
    }

    fun addLocalService(parrotService: IParrotService) {
        removeLocalService(parrotService)
        this.localServices.add(parrotService)
    }

    fun removeLocalService(parrotService: IParrotService) {
        this.localServices.removeIf { it.getUniqueId() == parrotService.getUniqueId() }
    }

    private fun existLocalService(uniqueId: UUID): Boolean {
        return getAllServices().map { it.getUniqueId() }.contains(uniqueId)
    }

}