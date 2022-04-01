package pet.nekos.api.event

/**
 * Represents an event
 */
abstract class Event {
    abstract var name: String?

    abstract fun getHandlers(): HandlerList

    public fun getEventName(): String {
        if (name == null) {
            name = javaClass.kotlin.simpleName as String
        }
        return name as String
    }


}