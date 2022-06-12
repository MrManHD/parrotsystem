package net.mrmanhd.parrot.lib.api.service

import net.mrmanhd.parrot.api.group.IParrotGroup
import net.mrmanhd.parrot.api.service.IParrotService
import net.mrmanhd.parrot.api.service.IServiceHandler
import net.mrmanhd.parrot.api.service.builder.IParrotServiceBuilder
import net.mrmanhd.parrot.lib.Parrot
import net.mrmanhd.parrot.lib.api.service.builder.ParrotServiceBuilder

/**
 * Created by MrManHD
 * Class create at 10.06.2022 12:50
 */

class ServiceHandler : IServiceHandler {

    override fun createService(parrotGroup: IParrotGroup): IParrotServiceBuilder {
        return ParrotServiceBuilder(parrotGroup)
    }

    override fun getAllServices(): List<IParrotService> {
        return Parrot.instance.parrotServiceRepository.getAllParrotServices()
    }

}