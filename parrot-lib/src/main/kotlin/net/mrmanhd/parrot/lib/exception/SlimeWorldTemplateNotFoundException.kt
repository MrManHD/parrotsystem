package net.mrmanhd.parrot.lib.exception

import java.lang.Exception

/**
 * Created by MrManHD
 * Class create at 17.06.2022 19:29
 */

class SlimeWorldTemplateNotFoundException(
    slimeWorldTemplateName: String
) : Exception(
    "SlimeWorldTemplate $slimeWorldTemplateName does not exist"
)