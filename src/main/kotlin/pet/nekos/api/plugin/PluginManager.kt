package pet.nekos.api.plugin

import pet.nekos.api.Server
import pet.nekos.api.service.Service
import pet.nekos.api.event.Event
import pet.nekos.api.event.Listener
import pet.nekos.api.event.EventHandler
import pet.nekos.api.event.EventPriority
import pet.nekos.api.event.HandlerList
import pet.nekos.api.event.message.MessageEvent

import java.io.File
import java.io.FileInputStream
import java.net.URL
import java.net.URLClassLoader
import java.util.jar.Attributes
import java.util.jar.JarInputStream
import java.util.jar.Manifest
import java.lang.reflect.Method
import java.lang.reflect.Field

class PluginManager() {
    var plugins: ArrayList<Plugin> = arrayListOf<Plugin>()
    var services: ArrayList<Service> = arrayListOf<Service>()
    val pluginDir = File("./plugins/")
    val serviceDir = File("./services/")

    fun loadPlugins() {
        if (pluginDir.listFiles() != null) {
            for (f: File in pluginDir.listFiles()) {
                loadPlugin(f)
            }
        } 
    }

    fun loadServices() {
        if (serviceDir.listFiles() != null) {
            for (f: File in serviceDir.listFiles()) {
                loadService(f)
            }
        }
    }

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

    fun createListeners(listener: Listener, plugin: Plugin): HashMap<Class<out Event>, MutableSet<RegisteredListener>> {
        var listeners = HashMap<Class<out Event>, MutableSet<RegisteredListener>>()
        var methods: Array<Method>

        try {
            methods = listener.javaClass.declaredMethods
        } catch (ex: NoClassDefFoundError) {
            return listeners
        }
        for (method: Method in methods) {
            var handler: EventHandler? = method.getAnnotation(EventHandler::class.java)
            if (handler == null) continue

            val checkClass: Class<*>
            checkClass = method.parameterTypes[0]    

            if (method.parameterTypes.size != 1 ||  !Event::class.java.isAssignableFrom(checkClass)) {
                continue
            }

            val eventClass: Class<out Event> = checkClass.asSubclass(Event::class.java)

            var eventSet: MutableSet<RegisteredListener>? = listeners.get(eventClass)
            if (eventSet == null) {
                eventSet = mutableSetOf<RegisteredListener>()
                listeners.put(eventClass, eventSet)
            }

            val executor = object : EventExecutor {
                override fun execute(listener: Listener, event: Event) {
                    try {
                        if (!eventClass::class.java.isAssignableFrom(Event::class.java)) {
                            return
                        }
                        method.invoke(listener, event)
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }
            }
            eventSet.add(RegisteredListener(listener, executor, handler.priority, plugin))
            
        }
        return listeners

    }

    fun registerEvents(listener: Listener, plugin: Plugin) {
        for ((key, value) in createListeners(listener, plugin)) {
            getEventListeners(key).registerAll(value)
        }
    }

    fun registerEvent(event: Class<out Event>, listener: Listener, executor: EventExecutor, priority: EventPriority, plugin: Plugin) {
        if (!plugin.isEnabled) {
            throw IllegalStateException("Plugin is not enabled")
        }
        getEventListeners(event).register(RegisteredListener(listener, executor, priority, plugin))
    }

    fun getEventListeners(event: Class<out Event>): HandlerList {        
        var m: Method = event.getDeclaredMethod("getHandlerlist")
        m.setAccessible(true)
        var handlers: HandlerList = m.invoke(null) as HandlerList
        return handlers
    }

    fun fireEvent(event: Event) {
        var handlers = event.getHandlers()

        for (rl: RegisteredListener in handlers.getListeners()) {
            if (!rl.plugin.isEnabled) { 
                continue
            }
            try {
                rl.callEvent(event)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}