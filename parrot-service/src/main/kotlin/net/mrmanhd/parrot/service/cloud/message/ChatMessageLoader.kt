package net.mrmanhd.parrot.service.cloud.message

import eu.thesimplecloud.api.config.AbstractJsonLibConfigLoader
import net.mrmanhd.parrot.service.cloud.config.ChatMessageConfig
import net.mrmanhd.parrot.service.cloud.config.DefaultChatMessageConfig
import java.io.File

/**
 * Created by MrManHD
 * Class create at 12.06.2022 20:03
 */

class ChatMessageLoader : AbstractJsonLibConfigLoader<ChatMessageConfig>(
    ChatMessageConfig::class.java,
    File("modules/parrot/messages.json"),
    { DefaultChatMessageConfig.get() },
    true,
)