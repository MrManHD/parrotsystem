package net.mrmanhd.parrot.service.cloud.message.config

import eu.thesimplecloud.api.utils.Nameable

/**
 * Created by MrManHD
 * Class create at 12.06.2022 20:04
 */

class ChatMessage(
    val languageName: String,
    val messages: Map<String, String>
) : Nameable {

    override fun getName(): String = this.languageName

}