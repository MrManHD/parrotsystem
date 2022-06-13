package net.mrmanhd.parrot.daemon

/**
 * Created by MrManHD
 * Class create at 13.06.2022 12:47
 */

class ParrotDaemon {

    init {
        instance = this
    }

    companion object {
        lateinit var instance: ParrotDaemon
            private set
    }

}