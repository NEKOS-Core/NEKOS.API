package pet.nekos.api.entities.channel

import pet.nekos.api.entities.user.User
import pet.nekos.api.entities.guild.Guild
import pet.nekos.api.entities.Entity
import pet.nekos.api.service.Service


/**
 * Class that is part of a guild
 * @property name Name of this channel
 * @property guild Guild this channel is part of
 * @property service Service this entity belongs to
 */
open class GuildChannel(
    name: String,
    guild: Guild,
    service: Service
) : Channel(name, service) { }