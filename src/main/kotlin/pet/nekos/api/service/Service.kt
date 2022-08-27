package pet.nekos.api.service

import pet.nekos.api.server.Server
import pet.nekos.api.entities.*

import java.io.File

abstract class Service {

    // Name for this service
    abstract var name: String
    // Data directory for this service
    open var dataDirectory: File? = null

    companion object {
        var server: Server? = null
        var selfUser: User? = null
    }

    /**
     * Gets the server that is running this service
     * @return Server that is running this service
     */
    fun getServer(): Server {
        return server as Server
    }

    /**
     * Gets the user this service is running from
     * @return User that this service is running as
     */
    open fun getSelfUser(): User? {
        return selfUser
    }

    /**
     * Ran by the NEKOS server manager, sets the server that is running this service
     */
    fun init(nekosServer: Server) {
        server = nekosServer
    }

    /**
     * Function to execute on enabling this service.
     * This can be used to load the config or define whatever the service needs.
     * @return If successful
     */
    open fun initService(): Boolean {
        return true
    }

    /**
     * Send a message in the services default message channel.
     * Implementations may return false if this service does not have a concept for a default Channel
     * 
     * @param content Content of the message
     * @return If successful
     */
    open fun sendMessage(content: String, vararg attachments: File): Boolean {
        return false
    }

    /**
     * Send a message in the given channel
     * 
     * @param content Content of the message
     * @param channel Channel to send the message in
     * @retrun If successful
     */
    open fun sendMessage(content: String, channel: Channel, vararg attachments: File): Boolean {
        return false
    }

}