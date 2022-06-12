package net.mrmanhd.parrot.service.cloud.config

object DefaultConfig {

    fun get(): Config {
        return Config(
            "§8[§6Parrot§8]§7 ",
            arrayListOf("daemon")
        )
    }

}