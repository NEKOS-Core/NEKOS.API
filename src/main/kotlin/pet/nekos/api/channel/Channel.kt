package pet.nekos.api.channel

import pet.nekos.api.user.User
import pet.nekos.api.service.Service

/**
 * The base class for a channel
 * @property name Name of this channel
 * @property service Service this entity belongs to
 */
open class Channel (
    var name: String,
    var service: Service
) {
    open fun getUsers(): Array<User> {
        return arrayOf<User>()
    }
}