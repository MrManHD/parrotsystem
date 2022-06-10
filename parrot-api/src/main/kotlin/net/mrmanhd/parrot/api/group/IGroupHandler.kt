package net.mrmanhd.parrot.api.group

/**
 * Created by MrManHD
 * Class create at 10.06.2022 19:14
 */

interface IGroupHandler {

    fun getGroupByName(groupName: String): IParrotGroup? {
        return getAllGroups().firstOrNull { it.getName() == groupName }
    }

    fun getAllGroups(): List<IParrotGroup>

}