package pet.nekos.api.plugin

import pet.nekos.api.server.Server
import pet.nekos.api.event.Listener

import java.io.File

abstract class Plugin {

    companion object {
        var server: Server? = null
    }

    fun getServer(): Server {
        return server as Server
    }

    open var name: String? = null
    open var dataDirectory: File? = null
    var isEnabled: Boolean = false

    fun init(nekosServer: Server) {
        server = nekosServer
    }

    open fun onEnable() {
        if (this is Listener) {
            server?.serverManager?.registerEvents(this, this)
        }
        isEnabled = true
    }

    open fun onDisable() {
        isEnabled = false
    }

    public fun getPluginName(): String {
        if (name == null) {
            name = javaClass.kotlin.simpleName as String
        }
        return name as String
    }

}