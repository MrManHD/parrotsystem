package net.mrmanhd.parrot.lib.messagechannel.dto

/**
 * Created by MrManHD
 * Class create at 12.06.2022 13:48
 */

class CloudMessageDTO(
    val message: String,
    val levelType: LevelType
) {

    enum class LevelType {
        CONSOLE,

        INFO,

        WARNING
    }

}