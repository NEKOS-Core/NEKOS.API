package pet.nekos.api.channel

import pet.nekos.api.user.User

abstract class Channel (
    var name: String,
) {
    abstract fun getUsers(): Array<User>    
}