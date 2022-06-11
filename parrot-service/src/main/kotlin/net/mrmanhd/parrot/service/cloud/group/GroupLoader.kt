package net.mrmanhd.parrot.service.cloud.group

import eu.thesimplecloud.api.config.AbstractMultipleConfigLoader
import java.io.File

/**
 * Created by MrManHD
 * Class create at 11.06.2022 19:31
 */

class GroupLoader : AbstractMultipleConfigLoader<Group>(
    Group::class.java,
    File("modules/parrot/groups"),
    emptyList(),
    true
)