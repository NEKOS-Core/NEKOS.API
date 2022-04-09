package pet.nekos.api.channel

import pet.nekos.api.user.User
import pet.nekos.api.guild.Guild

/**
 * Class that is part of a guild
 * @property name Name of this channel
 * @property guild Guild this channel is part of
 */
open class GuildChannel(
    name: String,
    guild: Guild
) : Channel(name) { }