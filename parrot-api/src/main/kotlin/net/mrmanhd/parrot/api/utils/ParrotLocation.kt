package net.mrmanhd.parrot.api.utils

import java.io.Serializable

/**
 * Created by MrManHD
 * Class create at 13.06.2022 13:24
 */

class ParrotLocation(
    val worldName: String,
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Float,
    val pitch: Float,
) : Serializable