package pet.nekos.api.channel

import pet.nekos.api.user.User
import pet.nekos.api.guild.Guild

abstract class GuildChannel(
    name: String,
    guild: Guild
) : Channel(name) { }