package pet.nekos.api.user

import java.security.MessageDigest
import java.math.BigInteger

object Hash {
    fun hash(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        val hash = md.digest(input.toByteArray())

        return BigInteger(1, hash).toString(16).padStart(32, '0')
    }
}