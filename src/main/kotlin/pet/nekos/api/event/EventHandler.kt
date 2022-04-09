package pet.nekos.api.event

/**
 * Annotation that a function handles an event
 * @property priority Priority this event handler has
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class EventHandler(
    val priority: EventPriority = EventPriority.NORMAL
)