package net.mrmanhd.parrot.api.extension

import eu.thesimplecloud.api.player.ICloudPlayer
import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.api.service.IParrotService

/**
 * Created by MrManHD
 * Class create at 12.06.2022 18:23
 */

fun ICloudPlayer.getParrotService(): IParrotService? {
    return ParrotApi.instance.getServiceHandler().getServiceByPlayer(this.getUniqueId())
}