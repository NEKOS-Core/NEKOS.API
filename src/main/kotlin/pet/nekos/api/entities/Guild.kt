package pet.nekos.api.entities

import pet.nekos.api.service.Service

/**
 * Represents a guild in NEKOS
 * A guild is mainly just a grouping of channels
 * @property name Name for this guild
 * @property service Service this entity belongs to
 */
abstract class Guild (
    var name: String,
    var service: Service
) : Entity {
    open fun getChannels(): Array<Channel> {
        return arrayOf<Channel>()
    }
    open fun getMembers(): Array<User> {
        return arrayOf<User>()
    }
}