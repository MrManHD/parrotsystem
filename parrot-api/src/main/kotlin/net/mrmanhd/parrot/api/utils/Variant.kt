package net.mrmanhd.parrot.api.utils

import java.io.Serializable

/**
 * Created by MrManHD
 * Class create at 14.06.2022 19:09
 */

data class Variant(
    val playersPerTeam: Int = 1,
    val teams: Int = 2
) : Serializable {

    override fun toString(): String {
        return "${teams}x$playersPerTeam"
    }

    companion object {
        fun fromString(variantString: String): Variant {
            val split = variantString.split("x")
            val typedArray = split.toTypedArray()

            return Variant(typedArray[1].toInt(), typedArray[0].toInt())
        }

        fun getStringVariant(playersPerTeam: Int, teams: Int): String {
            return "${teams}x$playersPerTeam"
        }
    }
}