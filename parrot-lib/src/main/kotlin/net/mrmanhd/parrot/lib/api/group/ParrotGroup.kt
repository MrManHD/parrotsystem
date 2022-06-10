package net.mrmanhd.parrot.lib.api.group

import net.mrmanhd.parrot.api.group.IParrotGroup

/**
 * Created by MrManHD
 * Class create at 10.06.2022 18:45
 */

class ParrotGroup(
    private val name: String,
    private val preLoadedWorlds: List<String>,
    private val minimumOnlineServiceCount: Int,
    private val maxOnlineServiceCount: Int,
    private val maintenance: Boolean
) : IParrotGroup {

    override fun getName(): String = this.name

    override fun getPreLoadedWorlds(): List<String> = this.preLoadedWorlds

    override fun getMinimumOnlineServiceCount(): Int = this.minimumOnlineServiceCount

    override fun getMaxOnlineServiceCount(): Int = this.maxOnlineServiceCount

    override fun isInMaintenance(): Boolean = this.maintenance

}