package pet.nekos.api.channel

import pet.nekos.api.user.User

open class Channel (
    var name: String,
) {
    open fun getUsers(): Array<User> {
        return arrayOf<User>()
    }
}