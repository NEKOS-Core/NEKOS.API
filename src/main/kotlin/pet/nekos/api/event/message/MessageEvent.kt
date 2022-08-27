package pet.nekos.api.event.message

import pet.nekos.api.event.Event
import pet.nekos.api.event.HandlerList

import pet.nekos.api.entities.*
import pet.nekos.api.service.Service

import java.util.Date

/**
 * Event for any message being received
 * @property msg Message received
 */
// class MessageEvent(val msg: Message, val time: Date) : Event() {
class MessageEvent() : Event() {
        companion object {
        @JvmStatic
        val handlerlist = HandlerList()
    }

    override fun getHandlers(): HandlerList {
        return handlerlist
    }

}