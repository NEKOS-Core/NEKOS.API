package pet.nekos.api.capabilities

/**
 * Enum of possible capabilities for a guild
 */
enum class GuildCapabilities() {
    GET_NAME,
    SET_NAME,
    GET_TOPIC,
    SET_TOPIC,
    GET_IMAGE,
    SET_IMAGE,
    GET_GUILD,
    GET_MEMBERS,
    BAN_MEMBER,
    KICK_MEMBER,
    GET_BANS,
    UNBAN_MEMBER,
    CREATE_INSTANT_INVITE,
    GET_CHANNELS,
    CREATE_CHANNEL,
    DELETE_CHANNEL,
}