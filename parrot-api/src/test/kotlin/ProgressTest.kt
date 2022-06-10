import net.mrmanhd.parrot.api.ParrotApi
import net.mrmanhd.parrot.api.group.IParrotGroup

class ProgressTest {

    init {
        ParrotApi.instance.getServiceHandler().createService(ParrotGroupDummy())
            .withMaxPlayers(200)
            .withMotd("Hallo ich bin ein Text")
            .withProperty("test", "123")
            .startService()
                .addResultListener { println("Starting") }
                .addFailureListener { println(it.message) }
    }

}

class ParrotGroupDummy : IParrotGroup {
    override fun getName(): String {
        TODO("Not yet implemented")
    }

    override fun getPreLoadedWorlds(): List<String> {
        TODO("Not yet implemented")
    }

    override fun getMinimumOnlineServiceCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getMaxOnlineServiceCount(): Int {
        TODO("Not yet implemented")
    }

    override fun isInMaintenance(): Boolean {
        TODO("Not yet implemented")
    }
}