package pet.nekos.api.message

import pet.nekos.api.user.User
import pet.nekos.api.channel.Channel
import pet.nekos.api.service.Service

open class Message (
    var content: String,
    var user: User,
    var channel: Channel?,
    var service: Service
) { }