package net.mrmanhd.parrot.api.hazelcast

import com.hazelcast.core.HazelcastInstance
import com.hazelcast.core.LifecycleEvent

/**
 * Created by MrManHD
 * Class create at 10.06.2022 22:01
 */

fun HazelcastInstance.withLifecycle(state: LifecycleEvent.LifecycleState, function: (LifecycleEvent) -> Unit): HazelcastInstance {
    this.lifecycleService.addLifecycleListener {
        if (it.state == state) {
            function(it)
        }
    }
    return this
}