package pet.nekos.api.message

import pet.nekos.api.user.User
import pet.nekos.api.channel.Channel

open class ChatMessage(
    content: String,
    user: User,
    channel: Channel
): Message(content, user, channel) {
    open fun reply(content: String): Boolean {
        return false
    }
}