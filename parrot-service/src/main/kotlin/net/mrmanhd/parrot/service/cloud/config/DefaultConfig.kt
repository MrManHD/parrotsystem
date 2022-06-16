package net.mrmanhd.parrot.service.cloud.config

import net.mrmanhd.parrot.lib.utils.Tablist

object DefaultConfig {

    fun get(): Config {
        return Config(
            "§8[§6Parrot§8]§7 ",
            "en",
            arrayListOf("daemon"),
            Tablist(
                listOf(
                    "",
                    "§6ParrotSystem §8-§7§o Erstelle mehrere Spiele auf einem Server §8┃§b 1.18",
                    "§7Du bist gerade auf §8» §a%SERVER%",
                    ""
                ),
                listOf(
                    "",
                    "§7Es spielen gerade §f%ONLINE_PLAYERS%§8/§f%MAX_PLAYERS%§7 Spieler",
                    "§7GitHub §8»§3 https://github.com/MrManHD",
                    ""
                )
            ),
            Tablist(
                listOf(
                    "",
                    "§6ParrotSystem §8-§7§o Erstelle mehrere Spiele auf einem Server §8┃§b 1.18",
                    "§7Du bist gerade auf §8» §a%SERVER%",
                    ""
                ),
                listOf(
                    "",
                    "§7Es spielen gerade §f%ONLINE_PLAYERS%§8/§f%MAX_PLAYERS%§7 Spieler",
                    "§7GitHub §8»§3 https://github.com/MrManHD",
                    ""
                )
            )
        )
    }

}