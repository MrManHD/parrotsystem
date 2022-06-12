package net.mrmanhd.parrot.lib.messagechannel.dto

import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.api.service.IParrotService

/**
 * Created by MrManHD
 * Class create at 12.06.2022 17:15
 */

class ParrotServiceStateDTO(
    val parrotName: String,
    val type: Type
) {

    fun getParrotService(): IParrotService? {
        return ParrotApi.instance.getServiceHandler().getServiceByName(this.parrotName)
    }

    enum class Type {
        STARTING,

        STOPPING
    }
}