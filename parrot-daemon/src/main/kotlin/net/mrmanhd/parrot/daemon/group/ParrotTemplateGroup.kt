package net.mrmanhd.parrot.daemon.group

import net.mrmanhd.parrot.api.group.IParrotGroup
import java.io.File

/**
 * Created by MrManHD
 * Class create at 18.06.2022 16:06
 */

class ParrotTemplateGroup(
    val parrotGroup: IParrotGroup,
    private val directoryFile: File
) {

    fun getWorldNames(): List<String> {
        val worldsDirectory = File(this.directoryFile, "worlds")
        worldsDirectory.mkdirs()
        return worldsDirectory.listFiles()?.map { it.name } ?: listOf()
    }

}