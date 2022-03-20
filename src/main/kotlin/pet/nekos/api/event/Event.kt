package pet.nekos.api.event

/**
 * Represents an event
 */
abstract class Event {
    var name: String? = null
    abstract var handlers: HandlerList
    
    public fun getEventName(): String {
        if (name == null) {
            name = javaClass.kotlin.simpleName as String
        }
        return name as String
    }


}