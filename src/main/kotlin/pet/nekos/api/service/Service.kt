package pet.nekos.api.service

import java.io.File
import java.io.FileInputStream
import java.net.URL
import java.net.URLClassLoader
import java.util.jar.Attributes
import java.util.jar.JarInputStream
import java.util.jar.Manifest

interface Service {
    val serviceName: String

    fun initService(): Boolean {
        return true
    }

    fun sendMessage(): Boolean {
        return false
    }
}