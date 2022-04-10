package pet.nekos.api.plugin

import pet.nekos.api.event.Event
import pet.nekos.api.event.Listener

/** 
 * Interface for an event executor. This has the execute function which gets called when an event is fired.
 */
interface EventExecutor {
    fun execute(listener: Listener, event: Event)
}