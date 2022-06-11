package net.mrmanhd.parrot.lib.api.group

import net.mrmanhd.parrot.api.group.IGroupHandler
import net.mrmanhd.parrot.api.group.IParrotGroup
import net.mrmanhd.parrot.lib.Parrot

/**
 * Created by MrManHD
 * Class create at 10.06.2022 19:16
 */

class GroupHandler : IGroupHandler {

    override fun getAllGroups(): List<IParrotGroup> {
        return Parrot.instance.parrotGroupRepository.getAllParrotGroups()
    }

}