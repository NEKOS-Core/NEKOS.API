package pet.nekos.api.event.message

import pet.nekos.api.event.Event
import pet.nekos.api.event.HandlerList

import pet.nekos.api.message.ChatMessage

class ChatMessageEvent(val msg: ChatMessage) : Event() {
    companion object {
        @JvmStatic
        val handlerlist = HandlerList()
    }

    override fun getHandlers(): HandlerList {
        return handlerlist
    }

}