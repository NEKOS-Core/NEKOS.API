package pet.nekos.api.guild

import pet.nekos.api.channel.GuildChannel
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
) {
    abstract fun getChannels(): Array<GuildChannel>
}