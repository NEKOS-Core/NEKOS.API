package pet.nekos.api.capabilities

/**
 * Enum of possible capabilities for a message
 */
enum class MessageCapabilities() {
    SEND_MESSAGE,
    EDIT_OWN_MESSAGE,
    EDIT_OTHER_MESSAGES,
    DELETE_OWN_MESSAGE,
    DELETE_OTHER_MESSAGES,
    PIN_MESSAGE,
    UNPIN_MESSAGE,
    READ_MESSAGE_HISTORY,
    GET_MESSAGE_LINK,
    REPLY_TO_MESSAGE,
    GET_MESSAGE_REPLY_TO,
    ADD_REACTION,
    REMOVE_OWN_REACTION,
    REMOVE_OTHER_REACTIONS,
    GET_REACTIONS,
}