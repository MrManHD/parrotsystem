package net.mrmanhd.parrot.lib.repository.info

import eu.thesimplecloud.api.CloudAPI
import eu.thesimplecloud.api.servicegroup.ICloudServiceGroup
import java.io.Serializable

/**
 * Created by MrManHD
 * Class create at 13.06.2022 00:43
 */

class ConfigInfo(
    val prefix: String,
    val language: String,
    private val startGroupNames: List<String>
) : Serializable {

    fun getStartGroupNames(): List<ICloudServiceGroup> {
        val groupManager = CloudAPI.instance.getCloudServiceGroupManager()
        return this.startGroupNames.mapNotNull { groupManager.getServiceGroupByName(it) }
    }

}