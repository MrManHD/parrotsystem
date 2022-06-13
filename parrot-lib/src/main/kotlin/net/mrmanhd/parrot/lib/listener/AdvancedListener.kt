package net.mrmanhd.parrot.lib.listener

import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener

/**
 * Created by MrManHD
 * Class create at 12.05.2021 16:55
 */

class AdvancedListener<T : Event>(listenerClass: Class<T>, eventPriority: EventPriority = EventPriority.NORMAL) :
    IAdvancedListener<T> {

    private var conditions: MutableList<((T) -> Boolean)> = ArrayList()
    private var actions: MutableList<((T) -> Unit)> = ArrayList()
    var listener: Listener? = null

    init {
        this.listener = listen(listenerClass, eventPriority) { event ->
            if (conditions.all { it(event) }) actions.forEach { it(event) }
        }
    }

    override fun addCondition(predicate: (T) -> Boolean): AdvancedListener<T> {
        this.conditions.add(predicate)
        return this
    }

    override fun addAction(predicate: (T) -> Unit): AdvancedListener<T> {
        this.actions.add(predicate)
        return this
    }

    override fun unregisterWhen(predicate: (Unit) -> IAdvancedListener<*>): AdvancedListener<T> {
        val advancedListener = predicate(Unit)
        advancedListener.addAction { this.stopListening() }
        advancedListener.addAction { advancedListener.stopListening() }
        return this
    }

    override fun stopListening() {
        listener?.let { unregisterListener(it) }
    }

    override fun unregisterAfterCall(): IAdvancedListener<T> {
        this.addAction { this.stopListening() }
        return this
    }

}