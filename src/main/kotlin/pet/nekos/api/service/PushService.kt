package pet.nekos.api.service

import pet.nekos.api.channel.Channel

interface PushService {

    fun sendMessage(content: String, channel: Channel): Boolean

}