package pet.nekos.api.event

import pet.nekos.api.plugin.RegisteredListener
import pet.nekos.api.plugin.Plugin

class HandlerList() {
    @Volatile var handlers = arrayOf<RegisteredListener>()
    var handlersMap = HashMap<EventPriority, ArrayList<RegisteredListener>>()
    var handlersList = ArrayList<HandlerList>()

    init {
        for (priority: EventPriority in EventPriority.values()) {
            handlersMap.put(priority, ArrayList<RegisteredListener>())
        }
    }

    fun createAllArrays() {
        for (h: HandlerList in handlersList) {
            h.createArray()
        }
    }

    fun unregisterAll(plugin: Plugin) {
        for (list: HandlerList in handlersList) {
            list.unregister(plugin)
        }
    }

    fun unregisterAll(listener: Listener) {
        for (list: HandlerList in handlersList) {
            list.unregister(listener)
        }
    }

    fun register(listener: RegisteredListener) {
        // Kotlin doesn't allow me to check this in an if statement because of null safety
        val b = handlersMap?.get(listener.priority)?.contains(listener)
        if (b != null && b) {
            throw IllegalStateException("Listener is already registered to priority " + listener.priority.toString())
        }
        handlers = arrayOf<RegisteredListener>()
        handlersMap.get(listener.priority)?.add(listener)
    }

    fun registerAll(listeners: MutableSet<RegisteredListener>) {
        for (listener: RegisteredListener in listeners) {
            register(listener)
        }
    }

    fun unregister(listener: RegisteredListener) {
        val b = handlersMap.get(listener.priority)?.remove(listener)
        if (b != null && b) {
            handlers = arrayOf<RegisteredListener>()
        }
    }

    fun unregister(plugin: Plugin) {
        var changed = false
        for (list: ArrayList<RegisteredListener> in handlersMap.values) {
            val i = list.iterator()

            while (i.hasNext()) {
                val t = i.next()
                if (t.plugin.equals(plugin)) {
                    i.remove()
                    changed = true
                }
            }
        }
        if (changed) handlers = arrayOf<RegisteredListener>()
    }

    fun unregister(listener: Listener) {
        var changed = false
        for (list: ArrayList<RegisteredListener> in handlersMap.values) {
            val i = list.iterator()

            while (i.hasNext()) {
                val t = i.next()
                if (t.listener.equals(listener)) {
                    i.remove()
                    changed = true
                }
            }
        }
        if (changed) handlers = arrayOf<RegisteredListener>()
    }

    fun createArray() {
        if (handlers.isEmpty()) return
        var entries = arrayListOf<RegisteredListener>()
        for ((key, value) in handlersMap) {
            entries.addAll(value)
        }
        handlers = entries.toArray(arrayOf<RegisteredListener>())
    }

    fun getListeners(): Array<RegisteredListener> {
        var handlers = arrayOf<RegisteredListener>()
        while ((handlers.isEmpty())) {
            handlers = this.handlers
            createArray()
        }
        return handlers
    }

}