package pet.nekos.api.message

import pet.nekos.api.user.User
import pet.nekos.api.channel.Channel

open class Message (
    var content: String,
    var user: User,
    var channel: Channel
) { }