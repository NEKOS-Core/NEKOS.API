package pet.nekos.api.message

import pet.nekos.api.user.User
import pet.nekos.api.channel.Channel
import pet.nekos.api.service.Service

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
    var channel: Channel?,
    var service: Service
) {

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

}