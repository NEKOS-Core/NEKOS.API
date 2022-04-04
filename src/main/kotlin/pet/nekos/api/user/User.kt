package pet.nekos.api.user

open class User (
    var name: String,
    var nickname: String = name,
    var hash: String
) { }