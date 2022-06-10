package net.mrmanhd.parrot.lib.api.service

import eu.thesimplecloud.clientserverapi.lib.promise.ICommunicationPromise
import net.mrmanhd.parrot.api.group.IParrotGroup
import net.mrmanhd.parrot.api.service.IParrotService
import net.mrmanhd.parrot.api.service.IServiceHandler
import net.mrmanhd.parrot.api.service.process.IParrotProgress
import net.mrmanhd.parrot.lib.api.service.progress.ParrotProgress

/**
 * Created by MrManHD
 * Class create at 10.06.2022 12:50
 */

class ServiceHandler : IServiceHandler {

    fun startService(progress: ParrotProgress): ICommunicationPromise<IParrotService> {
        TODO()
    }

    override fun createProgress(parrotGroup: IParrotGroup): IParrotProgress {
        return ParrotProgress(parrotGroup)
    }

    override fun getAllServices(): List<IParrotService> {
        TODO("Not yet implemented")
    }

}