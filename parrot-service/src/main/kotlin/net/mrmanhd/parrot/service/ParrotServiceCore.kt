package net.mrmanhd.parrot.service

import net.mrmanhd.parrot.lib.Parrot

/**
 * Created by MrManHD
 * Class create at 10.06.2022 19:28
 */

class ParrotServiceCore {

    init {
        instance = this
        Parrot()
    }

    fun shutdown() {
        Parrot.instance.shutdown()
    }

    companion object {
        lateinit var instance: ParrotServiceCore
            private set
    }

}