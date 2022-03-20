package pet.nekos.api.plugin

import pet.nekos.api.event.Event
import pet.nekos.api.event.Listener

interface EventExecutor {
    fun execute(listener: Listener, event: Event)
}