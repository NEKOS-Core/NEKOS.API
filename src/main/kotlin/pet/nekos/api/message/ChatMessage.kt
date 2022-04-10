package pet.nekos.api.message

import pet.nekos.api.user.User
import pet.nekos.api.channel.Channel
import pet.nekos.api.service.Service
import pet.nekos.api.service.ChatService

/**
 * Represents a chat message. Chat messages can be replied to
 * @property content Content of the message
 * @property user User that sent this message
 * @property channel Channel this message was sent in (can be null)
 * @property service Service that this message originates from
 */
open class ChatMessage(
    content: String,
    user: User,
    channel: Channel?,
    service: ChatService
): Message(content, user, channel, service as Service) {

    /**
     * Reply to this message
     * 
     * @param content Content of the message to send
     * @return If successful
     */
    open fun reply(content: String): Boolean {
        return false
    }

}