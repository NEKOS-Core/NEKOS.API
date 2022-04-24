package pet.nekos.api.entities.user

import pet.nekos.api.service.Service
import pet.nekos.api.entities.guild.Guild
import pet.nekos.api.entities.Entity

import java.io.File

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
    var guild: Guild?,
    var service: Service
) : Entity { 

    /**
     * Check if this is the user the bot is running as
     * 
     * @return If the user is the bot itself
     */
    open fun isSelf(): Boolean {
        if (service.getSelfUser() == null) {
            return false
        } else if (service.getSelfUser()?.hash == hash) {
            return true
        }
        return false
    }

    open fun sendMessage(content: String, vararg attachments: File ): Boolean {
        return false
    }
}