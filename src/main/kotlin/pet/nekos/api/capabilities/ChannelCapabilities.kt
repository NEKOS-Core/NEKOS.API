package pet.nekos.api.capabilities

/**
 * Enum of possible capabilities for a channel
 */
enum class ChannelCapabilities() {
    GET_NAME,
    SET_NAME,
    GET_TOPIC,
    SET_TOPIC,
    GET_IMAGE,
    SET_IMAGE,
    GET_GUILD,
    GET_MEMBERS,
    SEND_MESSAGE,
    READ_MESSAGE_HISTORY,
    BAN_MEMBER,
    KICK_MEMBER,
    GET_BANS,
    UNBAN_MEMBER,
    CREATE_INSTANT_INVITE,
}