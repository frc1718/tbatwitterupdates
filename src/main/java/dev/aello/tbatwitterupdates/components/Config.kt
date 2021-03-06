package dev.aello.tbatwitterupdates.components

import java.io.File
import java.util.*

class Config(private val config: File) {
    val properties = Properties()

    init {
        loadConfigFile()
    }

    operator fun get(key: String): String = properties.getProperty(key, "")
    operator fun set(key: String, value: String) = {
        ->
        properties[key] = value
        save()
    }

    fun save() {
        properties.store(config.outputStream(), "TBATwitterUpdates Configuration")
    }

    private fun loadConfigFile() {
        properties.load(config.inputStream())

        if (!properties.verifyContents("team",
                        "port",
                        "accessToken",
                        "accessTokenSecret",
                        "consumerApiKey",
                        "consumerApiKeySecret",
                        "secret")) {
            generateConfigFile()
        }
    }

    private fun generateConfigFile() {
        properties.setProperties(mapOf(
                "team" to "frc1718",
                "port" to "1718",
                "accessToken" to "TOKEN",
                "accessTokenSecret" to "TOKEN_SECRET",
                "consumerApiKey" to "API_KEY",
                "consumerApiKeySecret" to "API_KEY_SECRET",
                "secret" to "TBA_SECRET"
        ))

        properties.store(config.outputStream(), "TBATwitterUpdates Configuration")
        properties.load(config.inputStream())
    }

    private fun Properties.verifyContents(vararg list: String): Boolean {
        list.forEach {
            if (this[it] == null) {
                return false
            }
        }
        return true
    }

    private fun Properties.setProperties(map: Map<String, String>) {
        map.forEach { (k, v) -> this.setProperty(k, v) }
    }
}