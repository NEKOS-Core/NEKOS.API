package pet.nekos.api.server

import pet.nekos.api.server.Server
import pet.nekos.api.service.Service
import pet.nekos.api.event.Event
import pet.nekos.api.event.Listener
import pet.nekos.api.event.EventHandler
import pet.nekos.api.event.EventPriority
import pet.nekos.api.event.HandlerList
import pet.nekos.api.event.message.MessageEvent
import pet.nekos.api.plugin.EventExecutor
import pet.nekos.api.plugin.Plugin
import pet.nekos.api.plugin.RegisteredListener
import pet.nekos.api.entities.Entity

import java.io.File
import java.io.FileInputStream
import java.net.URL
import java.net.URLClassLoader
import java.util.jar.Attributes
import java.util.jar.JarInputStream
import java.util.jar.Manifest
import java.lang.reflect.Method
import java.lang.reflect.Field

/**
 * Class for the server manager
 */
class ServerManager() {
    // List of plugins
    var plugins: ArrayList<Plugin> = arrayListOf<Plugin>()
    // List of services
    var services: ArrayList<Service> = arrayListOf<Service>()
    // Default directory for plugins
    val pluginDir = File("./plugins/")
    // Default directory for services
    val serviceDir = File("./services/")

    /**
     * Load all plugins into runtime
     */
    fun loadPlugins() {
        if (pluginDir.listFiles() != null) {
            for (f: File in pluginDir.listFiles()) {
                loadPlugin(f)
            }
        } 
    }

    /**
     * Load all services into runtime
     */
    fun loadServices() {
        if (serviceDir.listFiles() != null) {
            for (f: File in serviceDir.listFiles()) {
                loadService(f)
            }
        }
    }

    /**
     * Load a JAR file for a plugin into the server runtime
     * @param file JAR file to load
     */
    fun loadPlugin(file: File): Boolean {
        try {
            val className: String
            var jarStream: JarInputStream = JarInputStream(FileInputStream(file))
            jarStream.use {
                val manifest: Manifest = jarStream.getManifest()

                className = manifest.getMainAttributes().getValue(Attributes.Name.MAIN_CLASS)
            }

            var classLoader = URLClassLoader.newInstance(arrayOf(URL("jar:" + file.toURI().toURL() + "!/")), this::class.java.classLoader)

            var pluginClass = classLoader.loadClass(className)
            var plugin: Plugin = pluginClass.getDeclaredConstructor().newInstance() as Plugin
            plugins.add(plugin)
            println("[LOG] Activated plugin $file")
            return true
        } catch (ex: Exception) {
            println("[ERR] Could not activate plugin $file")
            ex.printStackTrace()
            return false
        }
    }

    /**
     * Load a JAR file for a service into the server runtime
     * @param file JAR file to load
     */
    fun loadService(file: File): Boolean {
        try {
            val className: String
            var jarStream: JarInputStream = JarInputStream(FileInputStream(file))
            jarStream.use {
                val manifest: Manifest = jarStream.getManifest()

                className = manifest.getMainAttributes().getValue(Attributes.Name.MAIN_CLASS)
            }

            var classLoader = URLClassLoader.newInstance(arrayOf(URL("jar:" + file.toURI().toURL() + "!/")), this::class.java.classLoader)

            var serviceClass = classLoader.loadClass(className)

            var service: Service = serviceClass.getDeclaredConstructor().newInstance() as Service
            services.add(service)
            println("[LOG] Activated service $file")
            return true
        } catch (ex: Exception) {
            println("[ERR] Could not activate plugin $file")
            ex.printStackTrace()
            return false
        }
    }

    /**
     * Create the listeners from a plugins listener class and add them to the events listeners
     * @param listener Listener from this plugin
     * @param plugin This plugin
     * @return A map of events and their registered listeners
     */
    fun createListeners(listener: Listener, plugin: Plugin): HashMap<Class<out Event>, MutableSet<RegisteredListener>> {
        // Create empty map of events and their listeners
        var listeners = HashMap<Class<out Event>, MutableSet<RegisteredListener>>()
        // Empty array of methods
        var methods: Array<Method>

        try {
            // Try to get the methods from this listener
            methods = listener.javaClass.declaredMethods
        } catch (ex: NoClassDefFoundError) {
            // Return if no methods were found
            return listeners
        }
        // For every found method
        for (method: Method in methods) {
            // Check if the method has an EventHandler annotation
            var handler: EventHandler? = method.getAnnotation(EventHandler::class.java)
            // If the method does not have an EventHandler annotation, skip this method
            if (handler == null) continue

            // Get the class this method wants as parameter
            val checkClass: Class<*>
            checkClass = method.parameterTypes[0]    
            // If the class doesn't want one parameter or the class of this parameter isn't an Event, skip this method
            if (method.parameterTypes.size < 1 ||  !Event::class.java.isAssignableFrom(checkClass)) {
                continue
            }

            // Get this class as a class that extends Event
            val eventClass: Class<out Event> = checkClass.asSubclass(Event::class.java)

            // List of listeners for this event
            var eventSet: MutableSet<RegisteredListener>? = listeners.get(eventClass)
            if (eventSet == null) {
                eventSet = mutableSetOf<RegisteredListener>()
                // Add event calss and the list of listeners to the listeners map
                listeners.put(eventClass, eventSet)
            }

            // Create an EventExecutor class for this listener
            val executor = object : EventExecutor {
                override fun execute(listener: Listener, event: Event, vararg entities: Entity) {
                    try {
                        var arguments = arrayListOf<Entity>()
                        // Loop through all types this method requires
                        for (c in method.parameterTypes) {
                            if (c == method.parameterTypes[0]) continue // Skip the first entry, it will be the event, not an entity
                            for (entity in entities) {
                                // Check if the superclass of the entity is equal to the type this method requires
                                if (entity.javaClass.superclass == c) {
                                    // Add to the list of arguments it will give
                                    arguments.add(entity)
                                }
                            }
                        }
                        // Check if the method doesn't require any arguments. 
                        if (arguments.size == 0 && method.parameterTypes.size == 1) {
                            // Invoke the method obtained earlier
                            // Invoke with itself since it is a non static method
                            // Invoke passing the fired event
                            method.invoke(listener, event)
                        // Check if all requirements are met
                        } else if (arguments.size == method.parameterTypes.size - 1) {
                            method.invoke(listener, event, *arguments.toTypedArray())
                        }
                        // If the arguments dont meet the requirements of the method, dont fire it
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }
            }
            // Add this executor to the list added to the map earlier
            eventSet.add(RegisteredListener(listener, executor, handler.priority, plugin))

        }
        // Return the map
        return listeners

    }

    /**
     * Register all events this listener has
     * @param listener The listener to register
     * @param plugin Plugin this listener belongs to
     */
    fun registerEvents(listener: Listener, plugin: Plugin) {
        for ((key, value) in createListeners(listener, plugin)) {
            getEventListeners(key).registerAll(value)
        }
    }

    /**
     * Register a single event listener
     * @param event Event to listen to
     * @param listener The listener
     * @param executor The executor for this listener
     * @param priority The priority of this listener
     * @param plugin Plugin this listener belongs to
     */
    fun registerEvent(event: Class<out Event>, listener: Listener, executor: EventExecutor, priority: EventPriority, plugin: Plugin) {
        if (!plugin.isEnabled) {
            throw IllegalStateException("Plugin is not enabled")
        }
        getEventListeners(event).register(RegisteredListener(listener, executor, priority, plugin))
    }

    /**
     * Get a handlerslist from this event
     * @param event Class that extends event to get the handlers from
     * @return HandlerList class of this event
     */
    fun getEventListeners(event: Class<out Event>): HandlerList {
        var m: Method = event.getDeclaredMethod("getHandlerlist")
        m.setAccessible(true)
        var handlers: HandlerList = m.invoke(null) as HandlerList
        return handlers
    }

    /**
     * Fire a given event
     * @param event Event to fire
     */
    fun fireEvent(event: Event, vararg entities: Entity) {
        var handlers = event.getHandlers()

        for (rl: RegisteredListener in handlers.getListeners()) {

            if (!rl.plugin.isEnabled) { 
                continue
            }
            try {
                rl.callEvent(event, *entities)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

        }
    }
}