package pet.nekos.api.entities.guild

import pet.nekos.api.entities.channel.GuildChannel
import pet.nekos.api.service.Service
import pet.nekos.api.entities.Entity

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
    abstract fun getChannels(): Array<GuildChannel>
}