package pet.nekos.api.event

import pet.nekos.api.plugin.RegisteredListener
import pet.nekos.api.plugin.Plugin

class HandlerList() {
    var handlers: Array<RegisteredListener>? = null
    var handlersMap = HashMap<EventPriority, ArrayList<RegisteredListener>>()

    init {
        for (priority: EventPriority in EventPriority.values()) {
            handlersMap.put(priority, ArrayList<RegisteredListener>())
        }
    }

    fun register(listener: RegisteredListener) {
        // Kotlin doesn't allow me to check this in an if statement because of null safety
        val b = handlersMap.get(listener.priority)?.contains(listener)
        if (b != null && b) {
            throw IllegalStateException("Listener is already registered to priority " + listener.priority.toString())
        }
        handlers = null
        handlersMap.get(listener.priority)?.add(listener)
    }

    fun registerAll(listeners: Set<RegisteredListener>) {
        for (listener: RegisteredListener in listeners) {
            register(listener)
        }
    }

    fun unregister(listener: RegisteredListener) {
        val b = handlersMap.get(listener.priority)?.remove(listener)
        if (b != null && b) {
            handlers = null
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
        if (changed) handlers = null
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
        if (changed) handlers = null
    }

    fun createArray() {
        if (handlers != null) return
        var entries = arrayListOf<RegisteredListener>()
        for ((key, value) in handlersMap) {
            entries.addAll(0, value)
        }
        handlers = entries.toArray(arrayOf<RegisteredListener>())
    }

    fun getListeners(): Array<RegisteredListener> {
        while (handlers == null) {
            createArray()
        }

        return handlers as Array<RegisteredListener>
    }

}