package pet.nekos.api.guild

import pet.nekos.api.channel.Channel

abstract class Guild (
    var name: String,
) {
    abstract fun getChannels(): Array<Channel>
}