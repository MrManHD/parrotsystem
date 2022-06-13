package net.mrmanhd.parrot.lib.listener

import org.bukkit.event.Event

/**
 * Created by MrManHD
 * Class create at 12.05.2021 16:57
 */

interface IAdvancedListener<T : Event> {

    /**
     * Adds a condition to this listener.
     */
    fun addCondition(predicate: (T) -> Boolean): IAdvancedListener<T>

    /**
     * Adds a action to this listener.
     * Actions will only be called if all provided conditions are true
     */
    fun addAction(predicate: (T) -> Unit): IAdvancedListener<T>

    /**
     * Sets another [IAdvancedListener] to unregister this listener
     * This listener will be unregistered when all specified conditions are true
     * The specified listener will also be unregistered when all conditions are true.
     * The specified listener will be started by this method.
     */
    fun unregisterWhen(predicate: (Unit) -> IAdvancedListener<*>): IAdvancedListener<T>

    /**
     * Stops listening
     */
    fun stopListening()

    fun unregisterAfterCall(): IAdvancedListener<T>

}