package pet.nekos.api.service

import pet.nekos.api.server.Server

import java.io.File

abstract class Service {

    companion object {
        var server: Server? = null
    }

    fun getServer(): Server {
        return server as Server
    }

    open var name: String? = null
    open var dataDirectory: File? = null

    fun init(nekosServer: Server) {
        server = nekosServer
    }

    open fun initService(): Boolean {
        return true
    }

    open fun sendMessage(content: String): Boolean {
        return false
    }

    public fun getServiceName(): String {
        if (name == null) {
            name = javaClass.kotlin.simpleName as String
        }
        return name as String
    }

}