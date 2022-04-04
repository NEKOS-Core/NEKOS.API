package pet.nekos.api.message

import pet.nekos.api.user.User

open class ChatMessage(
    content: String,
    user: User
): Message(content, user) {
    open fun reply(content: String) {

    }
}