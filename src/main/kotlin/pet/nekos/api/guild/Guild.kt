package pet.nekos.api.guild

import pet.nekos.api.channel.GuildChannel

/**
 * Represents a guild in NEKOS
 * A guild is mainly just a grouping of channels
 * @property name Name for this guild
 */
abstract class Guild (
    var name: String,
) {
    abstract fun getChannels(): Array<GuildChannel>
}