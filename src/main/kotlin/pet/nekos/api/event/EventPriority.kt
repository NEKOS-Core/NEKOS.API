package pet.nekos.api.event

enum class EventPriority(val num: Int) {
    /**
     * The plugin implementing this event is not of high importance,
     * other plugins can handle this event after this plugin finishes
     *  */ 
    LOW(0),

    /**
     * Default importance, use this if you're unsure
     */
    NORMAL(1),

    /**
     * The plugin should handle this message
     * Example: Logging a messages after a filter matches should happen here
     */
    HIGH(2),

    /**
     * Use this if the plugin should have the final say, 
     * Example: Deleting messages after a filter should happen here
     */
    HIGHEST(3),

}