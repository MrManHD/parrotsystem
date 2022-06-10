package net.mrmanhd.parrot.api.hazelcast

import com.hazelcast.core.EntryEvent
import com.hazelcast.map.listener.*
import net.mrmanhd.parrot.api.ParrotApi

/**
 * Created by MrManHD
 * Class create at 10.06.2022 22:01
 */

abstract class AbstractHazelcastHashMapRepository<K : Any, V : Any>(
    name: String
) {

    private val hashMap by lazy {
        ParrotApi.instance.getHazelcast().getMap<K, V>(name)
    }

    fun findAll(): List<V> {
        return this.hashMap.values.toList()
    }

    fun findAllKeys(): List<K> {
        return this.hashMap.keys.toList()
    }

    fun getMap(): Map<K, V> = this.hashMap

    fun find(key: K): V? {
        return this.hashMap[key]
    }

    fun remove(key: K) {
        this.hashMap.remove(key)
    }

    fun insert(key: K, value: V) {
        this.hashMap.put(key, value)
    }

    fun clear() {
        this.hashMap.clear()
    }

    fun withAddedListener(function: (V) -> Unit): AbstractHazelcastHashMapRepository<K, V> {
        this.hashMap.addEntryListener(object : EntryAddedListener<K, V> {
            override fun entryAdded(event: EntryEvent<K, V>) {
                function(event.value)
            }
        }, true)
        return this
    }

    fun withRemovedListener(function: (V) -> Unit): AbstractHazelcastHashMapRepository<K, V> {
        this.hashMap.addEntryListener(object : EntryRemovedListener<K, V> {
            override fun entryRemoved(event: EntryEvent<K, V>) {
                function(event.oldValue)
            }
        }, true)
        return this
    }

    fun withUpdatedListener(function: (V, V) -> Unit): AbstractHazelcastHashMapRepository<K, V> {
        this.hashMap.addEntryListener(object : EntryUpdatedListener<K, V> {
            override fun entryUpdated(event: EntryEvent<K, V>) {
                function(event.value, event.oldValue)
            }
        }, true)
        return this
    }

}