package pet.nekos.api.user

import pet.nekos.api.guild.Guild

/** Class that represents a user
 * @property user User from this member
 * @property guild Guild this user is from
 */
open class Member (
    var user: User,
    var guild: Guild    
) { }