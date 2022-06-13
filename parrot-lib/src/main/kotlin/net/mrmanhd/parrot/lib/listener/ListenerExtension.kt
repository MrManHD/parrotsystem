package net.mrmanhd.parrot.lib.listener

import org.bukkit.Bukkit
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

/**
 * Created by MrManHD
 * Class create at 12.05.2021 16:54
 */

/**
 * Registers a listener and returns it.
 */
inline fun <reified T : Event> listen(eventPriority: EventPriority = EventPriority.NORMAL, noinline predicate: (T) -> Unit): Listener {
    return listen(T::class.java, eventPriority, predicate)
}

fun <T : Event> listen(typeClass: Class<T>, eventPriority: EventPriority = EventPriority.NORMAL, predicate: (T) -> Unit): Listener {
    val listenerObj = object : Listener {}
    Bukkit.getServer().pluginManager.registerEvent(
        typeClass,
        listenerObj,
        eventPriority,
        { _, event ->
            val event: T = try {
                event as T
            } catch (e: Exception) {
                return@registerEvent
            }
            predicate(event)
        },
        Bukkit.getPluginManager().plugins.firstOrNull()!!,
        false
    )
    return listenerObj
}

/**
 * Unregisters a listener
 */
fun unregisterListener(listener: Listener) {
    HandlerList.unregisterAll(listener)
}

/**
 * Returns a new [AdvancedListener]
 */
inline fun <reified T : Event> advancedListen(priority: EventPriority = EventPriority.NORMAL): AdvancedListener<T> = AdvancedListener(T::class.java, priority)
