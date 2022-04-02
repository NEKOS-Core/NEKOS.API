package pet.nekos.api.server

import pet.nekos.api.plugin.Plugin
import java.io.File

class Server {
    val serverManager = ServerManager()

    fun loadPlugins() {
        serverManager.loadPlugins()
        for (plugin: Plugin in serverManager.plugins) {
            plugin.init(this)
            plugin.onEnable()
        }
    }
}