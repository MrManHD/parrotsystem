package net.mrmanhd.parrot.service.cloud.config

import eu.thesimplecloud.api.config.AbstractJsonLibConfigLoader
import java.io.File

/**
 * Created by MrManHD
 * Class create at 13.06.2022 00:41
 */

class ConfigLoader : AbstractJsonLibConfigLoader<Config>(
    Config::class.java,
    File("modules/parrot/config.json"),
    { DefaultConfig.get() },
    true,
)