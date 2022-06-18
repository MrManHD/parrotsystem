package net.mrmanhd.parrot.lib.messagechannel.dto

import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.api.service.IParrotService

/**
 * Created by MrManHD
 * Class create at 18.06.2022 15:33
 */

class ParrotWorldStateDTO(
    val parrotName: String,
    val slimeWorldtemplateName: String,
    val type: Type
) {

    fun getParrotService(): IParrotService? {
        return ParrotApi.instance.getServiceHandler().getServiceByName(this.parrotName)
    }

    enum class Type {
        LOAD,

        UNLOAD
    }
}