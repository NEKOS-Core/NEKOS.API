package pet.nekos.api.entities.message

import pet.nekos.api.entities.user.User
import pet.nekos.api.entities.channel.Channel
import pet.nekos.api.service.ChatService
import pet.nekos.api.entities.Entity

import java.io.File

/**
 * Represents a chat message. Chat messages can be replied to
 * @property content Content of the message
 * @property user User that sent this message
 * @property channel Channel this message was sent in (can be null)
 * @property service ChatService this entity belongs to
 */
open class ChatMessage(
    content: String,
    user: User,
    channel: Channel?,
    service: ChatService
): Message(content, user, channel, service) {

    /**
     * Reply to this message
     * 
     * @param content Content of the message to send
     * @param attachments Optional list of attachments
     * @return If successful
     */
    open fun reply(content: String, vararg attachments: File ): Boolean {
        return false
    }

}