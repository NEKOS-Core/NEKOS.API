package pet.nekos.api.entities

import pet.nekos.api.service.Service

/**
 * The base class for a channel
 * @property name Name of this channel
 * @property service Service this entity belongs to
 */
open class Channel (
    var name: String,
    var service: Service,
    var guild: Guild?
) : Entity {
    open fun getMembers(): Array<User> {
        return arrayOf<User>()
    }
}