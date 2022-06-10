package net.mrmanhd.parrot.lib.utils

import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.api.group.IParrotGroup

/**
 * Created by MrManHD
 * Class create at 10.06.2022 22:31
 */

class ServiceNumberGenerator(
    private val parrotGroup: IParrotGroup
) {

    fun getNumber(): Int {
        var number = 1
        while (isNumberInUse("${parrotGroup.getName()}-$number")) {
            number++
        }
        return number
    }

    private fun isNumberInUse(parrotName: String): Boolean {
        val serviceHandler = ParrotApi.instance.getServiceHandler()
        return serviceHandler.getServiceByName(parrotName) != null
    }

}