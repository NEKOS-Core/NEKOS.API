package pet.nekos.api.event

/**
 * Represents an event
 */
abstract class Event {

    abstract fun getHandlers(): HandlerList

    open var name: String? = null

    public fun getEventName(): String {
        if (name == null) {
            name = javaClass.kotlin.simpleName as String
        }
        return name as String
    }

}