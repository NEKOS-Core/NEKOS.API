package pet.nekos.api.capabilities

/**
 * Enum of possible capabilities for a user
 */
enum class UserCapabilities() {
    GET_USERNAME,
    GET_NICKNAME,
    GET_USER_BIO,
    GET_UNIQUE_HASH,
    GET_AVATAR,
    GET_AVATAR_URL,
    GET_BANNER,
    GET_BANNER_URL,
    SET_OWN_NICKNAME,
    SET_OTHER_NICKNAME,
    SET_OWN_AVATAR,
    SET_OTHER_AVATAR,
    SET_OWN_BANNER,
    SET_OTHER_BANNER,
    DIRECT_MESSAGE,
    GET_USER_ROLES,
    BLOCK_USER,
    UNBLOCK_USER,
    GET_KNOWN_GUILDS,
    GET_KNOWN_CHANNELS,
    ADD_FRIEND,
    REMOVE_FRIEND,
    GET_KNOWN_FRIENDS,
}