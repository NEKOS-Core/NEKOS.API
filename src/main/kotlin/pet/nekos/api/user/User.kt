package pet.nekos.api.user

import pet.nekos.api.service.Service

/** Class that represents a user
 * @property name Name of this user
 * @property nickname The friendly name of this user, defaults to the same as name
 * @propery hash A hash of permmanent identifiable information for this user. This can be used to verify if user objects are equal
 * @property service Service this entity belongs to
 */
open class User (
    var name: String,
    var nickname: String = name,
    var hash: String,
    var service: Service
) { }