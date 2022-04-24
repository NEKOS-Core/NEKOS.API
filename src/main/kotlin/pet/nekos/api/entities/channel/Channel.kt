package pet.nekos.api.entities.channel

import pet.nekos.api.entities.user.User
import pet.nekos.api.service.Service
import pet.nekos.api.entities.Entity

/**
 * The base class for a channel
 * @property name Name of this channel
 * @property service Service this entity belongs to
 */
open class Channel (
    var name: String,
    var service: Service
) : Entity {
    open fun getUsers(): Array<User> {
        return arrayOf<User>()
    }
}