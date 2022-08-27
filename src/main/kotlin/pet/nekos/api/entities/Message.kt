package pet.nekos.api.entities

import pet.nekos.api.service.Service

import java.io.File

/**
 * Represents a chat message. Chat messages can be replied to
 * @property content Content of the message
 * @property user User that sent this message
 * @property channel Channel this message was sent in (can be null)
 * @property service Service this entity belongs to
 */
open class Message (
    var content: String,
    var user: User,
    var channel: Channel,
    var service: Service
) : Entity {

    /**
     * Check if this message belongs to the user the bot is running as
     * 
     * @return If the message is from the bot itself
     */
    open fun isOwn(): Boolean {
        if (service.getSelfUser() == null) {
            return false
        } else if (service.getSelfUser()?.hash == user.hash) {
            return true
        }
        return false
    }

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