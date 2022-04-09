package pet.nekos.api.event.message

import pet.nekos.api.event.Event
import pet.nekos.api.event.HandlerList

import pet.nekos.api.message.Message

/**
 * Event for any message being received
 * @property msg Message received
 */
class MessageEvent(val msg: Message) : Event() {
    companion object {
        @JvmStatic
        val handlerlist = HandlerList()
    }

    override fun getHandlers(): HandlerList {
        return handlerlist
    }

}