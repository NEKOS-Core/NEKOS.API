package pet.nekos.api.plugin

import pet.nekos.api.Server

import java.io.File

abstract class Plugin {
    var server: Server? = null
    var name: String? = null
    var dataDirectory: File? = null
    var isEnabled: Boolean = false

    fun init(server: Server) {
        this.server = server
    }

    open fun onEnable() {
        isEnabled = true
    }

    open fun onDisable() { 
        isEnabled = false
    }
}