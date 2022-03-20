package pet.nekos.api

import pet.nekos.api.plugin.PluginManager
import pet.nekos.api.plugin.Plugin
import java.io.File

class Server {
    val pluginManager = PluginManager()

    fun loadPlugins() {
        pluginManager.loadPlugins()
        for (plugin: Plugin in pluginManager.plugins) {
            plugin.init(this)
            plugin.onEnable()
        }
    }
}