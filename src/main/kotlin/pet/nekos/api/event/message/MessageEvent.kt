package pet.nekos.api.event.message

import pet.nekos.api.event.Event
import pet.nekos.api.event.HandlerList

class MessageEvent(val content: String) : Event() {
    override var handlers = HandlerList()
}