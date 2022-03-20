package pet.nekos.api.plugin

import pet.nekos.api.event.Event
import pet.nekos.api.event.EventPriority
import pet.nekos.api.event.Listener

open class RegisteredListener constructor(
    val listener: Listener,
    val executor: EventExecutor,
    val priority: EventPriority,
    val plugin: Plugin) {
    fun callEvent(event: Event) {
        executor.execute(listener, event)
    }
}