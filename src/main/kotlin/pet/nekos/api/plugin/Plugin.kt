package pet.nekos.api.plugin

import pet.nekos.api.server.Server
import pet.nekos.api.event.Listener

import java.io.File

/**
 * Class to create a plugin from
 */
abstract class Plugin {

    companion object {
        var server: Server? = null
    }

    /**
     * Gets the server that is running this plugin
     * @return Server that is running this plugin
     */
    fun getServer(): Server {
        return server as Server
    }

    // Name of this plugin
    open var name: String? = null
    // Data directory for this plugin
    open var dataDirectory: File? = null
    // If this plugin is enabled
    var isEnabled: Boolean = false

    /**
     * Ran by the NEKOS server manager, sets the server that is running this plugin
     */
    fun init(nekosServer: Server) {
        server = nekosServer
    }

    /**
     * Function to execute on enabling this plugin.
     * If the plugin also extends the generic listener interface, register this listener
     * Then sets the plugin to enabled
     */
    open fun onEnable() {
        if (this is Listener) {
            server?.serverManager?.registerEvents(this, this)
        }
        isEnabled = true
    }

    /**
     * Disables the plugin
     */
    open fun onDisable() {
        isEnabled = false
    }

    /**
     * Gets the name of this plugin
     * @return Name of this plugin
     */
    public fun getPluginName(): String {
        if (name == null) {
            name = javaClass.kotlin.simpleName as String
        }
        return name as String
    }

}