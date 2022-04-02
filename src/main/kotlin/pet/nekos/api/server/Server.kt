package pet.nekos.api.server

import pet.nekos.api.plugin.Plugin
import pet.nekos.api.service.Service
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

    fun loadServices() {
        serverManager.loadServices()
        for (service: Service in serverManager.services) {
            service.init(this)
            service.initService()
        }
    }

}