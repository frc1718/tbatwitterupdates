package dev.aello.tbatwitterupdates.components

import java.io.File
import java.util.*

class Config(private val config: File) {
    val properties = Properties()

    init {
        loadConfigFile()
    }

    private fun loadConfigFile() {
        if (!config.exists()) {
            generateConfigFile()
        }

        properties.load(config.inputStream())
    }

    private fun generateConfigFile() {
        properties.setProperties(mapOf(
                "team" to "frc1718",
                "port" to "1718",
                "accessToken" to "TOKEN",
                "accessTokenSecret" to "TOKEN_SECRET",
                "consumerApiKey" to "API_KEY",
                "consumerApiKeySecret" to "API_KEY_SECRET"
        ))

        properties.store(config.outputStream(), null)
    }

    private fun Properties.setProperties(map: Map<String, String>) {
        map.forEach { (k, v) -> this.setProperty(k, v) }
    }
}