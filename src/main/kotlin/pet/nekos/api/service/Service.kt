package pet.nekos.api.service

import pet.nekos.api.server.Server
import pet.nekos.api.user.User

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
    fun getSelfUser(): User? {
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

}