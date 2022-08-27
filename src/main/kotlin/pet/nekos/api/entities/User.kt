package pet.nekos.api.entities

import java.security.MessageDigest
import java.math.BigInteger

import pet.nekos.api.service.Service

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

    companion object {

        fun generateHash(input: String): String {
            val md = MessageDigest.getInstance("MD5")
            val hash = md.digest(input.toByteArray())

            return BigInteger(1, hash).toString(16).padStart(32, '0')
        }

    }

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