package pet.nekos.api.event

import pet.nekos.api.plugin.RegisteredListener
import pet.nekos.api.plugin.Plugin

/** Array of handlers for an event */
class HandlerList() {
    // Sorted list of handlers
    var handlers: Array<RegisteredListener>? = null
    // Map of handlers and their priorities
    var handlersMap = HashMap<EventPriority, ArrayList<RegisteredListener>>()

    /**
     * Initialise map with empty keys of all values
     */
    init {
        for (priority: EventPriority in EventPriority.values()) {
            handlersMap.put(priority, ArrayList<RegisteredListener>())
        }
    }

    /**
     * Register a listener
     * 
     * @param listener RegisteredListener to register to this event
     */
    fun register(listener: RegisteredListener) {
        val b = handlersMap.get(listener.priority)?.contains(listener)
        if (b != null && b) {
            throw IllegalStateException("Listener is already registered to priority " + listener.priority.toString())
        }
        handlers = null
        handlersMap.get(listener.priority)?.add(listener)
    }

    /**
     * Register a set of listeners
     * 
     * @param listener RegisteredListener to register to this event
     */
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

    /**
     * Unregister all listeners from a plugin
     * 
     * @param plugin Plugin to unregister to this event
     */
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

    /**
     * Unregister a listeners
     * 
     * @param listener RegisteredListener to unregister to this event
     */
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

    /**
     * Create a sorted array from the map of listeners and priorities
     * This function is called only when needed.
     * 
     */
    fun createArray() {
        if (handlers != null) return
        var entries = arrayListOf<RegisteredListener>()
        @Suppress("UNUSED_VARIABLE")
        for ((key, value) in handlersMap) {
            entries.addAll(0, value)
        }
        handlers = entries.toArray(arrayOf<RegisteredListener>())
    }

    /**
     * Get the listeners for this event
     * 
     * @return Array of registered listeners for this event
     */
    fun getListeners(): Array<RegisteredListener> {
        while (handlers == null) {
            createArray()
        }

        return handlers as Array<RegisteredListener>
    }

}