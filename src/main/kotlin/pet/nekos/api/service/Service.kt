package pet.nekos.api.service

import pet.nekos.api.server.Server
import pet.nekos.api.user.User

import java.io.File

abstract class Service {

    abstract var name: String
    open var dataDirectory: File? = null

    companion object {
        var server: Server? = null
        var selfUser: User? = null
    }

    fun getServer(): Server {
        return server as Server
    }

    fun getSelfUser(): User? {
        return selfUser
    }

    fun init(nekosServer: Server) {
        server = nekosServer
    }

    open fun initService(): Boolean {
        return true
    }

}