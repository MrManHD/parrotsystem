package net.mrmanhd.parrot.service.cloud.message

import eu.thesimplecloud.api.config.AbstractMultipleConfigLoader
import net.mrmanhd.parrot.service.cloud.group.Group
import net.mrmanhd.parrot.service.cloud.message.config.ChatMessage
import net.mrmanhd.parrot.service.cloud.message.config.DefaultChatMessageConfig
import java.io.File

/**
 * Created by MrManHD
 * Class create at 12.06.2022 20:03
 */

class ChatMessageLoader : AbstractMultipleConfigLoader<ChatMessage>(
    ChatMessage::class.java,
    File("modules/parrot/languages"),
    listOf(DefaultChatMessageConfig.getGermanMessage(), DefaultChatMessageConfig.getEnglishMessage()),
    true
)