package net.mrmanhd.parrot.service.cloud.config

import net.mrmanhd.parrot.lib.repository.info.ConfigInfo
import net.mrmanhd.parrot.lib.utils.Tablist

/**
 * Created by MrManHD
 * Class create at 13.06.2022 00:40
 */

class Config(
    val prefix: String,
    val language: String,
    val startGroupNames: List<String>,
    val legacyTablist: Tablist?,
    val groupTablist: Tablist,
) {

    fun convertToConfigInfo(): ConfigInfo {
        return ConfigInfo(
            this.prefix,
            this.language,
            this.startGroupNames,
            this.legacyTablist,
            this.groupTablist
        )
    }

}