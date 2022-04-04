package pet.nekos.api.message

import pet.nekos.api.user.User

open class Message (
    var content: String,
    var user: User
) { }