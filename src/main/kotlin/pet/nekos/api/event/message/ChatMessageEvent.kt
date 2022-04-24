package pet.nekos.api.event.message

import pet.nekos.api.event.Event
import pet.nekos.api.event.HandlerList

import pet.nekos.api.entities.message.ChatMessage
import pet.nekos.api.service.ChatService

import java.util.Date

/**
 * Event for a chat message being received. These can be replied to
 * @property msg ChatMessage received
 */
// class ChatMessageEvent(val msg: ChatMessage, val time: Date) : Event() {
class ChatMessageEvent() : Event() {
    companion object {
        @JvmStatic
        val handlerlist = HandlerList()
    }

    override fun getHandlers(): HandlerList {
        return handlerlist
    }

}