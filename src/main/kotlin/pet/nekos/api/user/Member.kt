package pet.nekos.api.user

import pet.nekos.api.guild.Guild
import pet.nekos.api.service.Service

/** Class that represents a user
 * @property user User from this member
 * @property guild Guild this user is from
 * @property service Service this entity belongs to
 */
open class Member (
    var user: User,
    var guild: Guild,
    var service: Service
) { }