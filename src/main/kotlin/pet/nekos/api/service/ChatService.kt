package pet.nekos.api.service

import pet.nekos.api.server.Server
import pet.nekos.api.channel.Channel
import pet.nekos.api.message.Message

import java.io.File

abstract class ChatService : Service() {

    /**
     * Send a message in the services default message channel.
     * Implementations may return false if this service does not have a concept for a default Channel
     * 
     * @param content Content of the message
     * @return If successful
     */
    open fun sendMessage(content: String): Boolean {
        return false
    }

    /**
     * Send a message in the given channel
     * 
     * @param content Content of the message
     * @param channel Channel to send the message in
     * @retrun If successful
     */
    open fun sendMessage(content: String, channel: Channel): Boolean {
        return false
    }

}