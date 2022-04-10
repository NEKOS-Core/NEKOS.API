package pet.nekos.api.plugin

import pet.nekos.api.event.Event
import pet.nekos.api.event.EventPriority
import pet.nekos.api.event.Listener

/**
 * Class representing a listener for an event
 * @property listener Generic Listener class
 * @propery executor Executor of this listener
 * @propery priority Priority for this listener (From priority enum)
 * @propery plugin Plugin of this listener
 */
open class RegisteredListener constructor(
    val listener: Listener,
    val executor: EventExecutor,
    val priority: EventPriority,
    val plugin: Plugin) {
    /**
     * Execute when this event is called. This calls the executor
     * @param event Event that is fired
     */
    fun callEvent(event: Event) {
        executor.execute(listener, event)
    }
}