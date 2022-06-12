package net.mrmanhd.parrot.service.cloud.config

import net.mrmanhd.parrot.lib.Parrot

/**
 * Created by MrManHD
 * Class create at 13.06.2022 00:42
 */

class ConfigHandler {

    private val repository by lazy { Parrot.instance.configRepository }

    fun loadConfig() {
        val config = ConfigLoader().loadConfig()
        this.repository.insert("config", config.convertToConfigInfo())
    }

    fun updateConfig() {
        this.repository.clear()
        loadConfig()
    }

}