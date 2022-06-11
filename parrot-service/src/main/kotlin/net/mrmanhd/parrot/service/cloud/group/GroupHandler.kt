package net.mrmanhd.parrot.service.cloud.group

import eu.thesimplecloud.launcher.startup.Launcher
import net.mrmanhd.parrot.lib.Parrot

/**
 * Created by MrManHD
 * Class create at 11.06.2022 19:35
 */

class GroupHandler {

    private val repository by lazy { Parrot.instance.parrotGroupRepository }

    fun loadGroups() {
        val groups = GroupLoader().loadAll()
        groups.forEach { registerGroup(it) }
    }

    fun updateGroups() {
        this.repository.clear()
        loadGroups()
    }

    fun addGroup(group: Group) {
        GroupLoader().save(group)
        this.repository.insert(group.groupName, group.convertToParrotGroupInfo())
    }

    private fun registerGroup(group: Group) {
        Launcher.instance.logger.console("Register ParrotGroup ${group.groupName}")
        this.repository.insert(group.groupName, group.convertToParrotGroupInfo())
    }

}