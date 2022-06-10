package net.mrmanhd.parrot.lib

/**
 * Created by MrManHD
 * Class create at 10.06.2022 12:06
 */

class Parrot {

    init {
        instance = this
    }

    companion object {
        lateinit var instance: Parrot
            private set
    }

}