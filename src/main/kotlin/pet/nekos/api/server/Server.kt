package pet.nekos.api.server

import pet.nekos.api.plugin.Plugin
import pet.nekos.api.service.Service
import java.io.File

/**
 * Class representing the NEKOS server itself
 */
class Server {
    // Create the server manager
    val serverManager = ServerManager()

    /**
     * Load all plugins
     */
    fun loadPlugins() {
        serverManager.loadPlugins()
        for (plugin: Plugin in serverManager.plugins) {
            plugin.init(this)
            plugin.onEnable()
        }
    }

    /**
     * Load all services
     */
    fun loadServices() {
        serverManager.loadServices()
        for (service: Service in serverManager.services) {
            service.init(this)
            service.initService()
        }
    }

}