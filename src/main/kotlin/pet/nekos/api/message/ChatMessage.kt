package pet.nekos.api.message

import pet.nekos.api.user.User
import pet.nekos.api.channel.Channel
import pet.nekos.api.service.Service
import pet.nekos.api.service.ChatService

open class ChatMessage(
    content: String,
    user: User,
    channel: Channel,
    service: ChatService
): Message(content, user, channel, service as Service) {

    open fun isOwn(): Boolean {
        if (service.getSelfUser() == null) {
            return true
        } else if (service.getSelfUser()?.hash == user.hash) {
            return true
        }
        return false
    }

    open fun reply(content: String): Boolean {
        return false
    }

}