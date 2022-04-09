package pet.nekos.api.channel

import pet.nekos.api.user.User
/**
 * The base class for a channel
 * @property name Name of this channel
 */
open class Channel (
    var name: String,
) {
    open fun getUsers(): Array<User> {
        return arrayOf<User>()
    }
}