package dev.aello.tbatwitterupdates

import dev.aello.tbatwitterupdates.components.Config
import dev.aello.tbatwitterupdates.webhooks.TBAWebhook
import mu.KotlinLogging
import java.io.File

fun main() {
    val logger = KotlinLogging.logger("TBATwitterUpdates")
    val configFile: File =
            if (System.getenv("XDG_CONFIG_HOME") == null)
                File(System.getenv("HOME") + "/.config/tbatwitterupdates.conf")
            else
                File(System.getenv("XDG_CONFIG_HOME" + "/tbatwitterupdates.conf"))

    if (!configFile.exists()) {
        if (!configFile.createNewFile()) {
            logger.error { "Could not create config file! (Check permissions?)" }
            return
        }
    }

    val config = Config(configFile)
    if (config["accessToken"] == "TOKEN") {
        logger.error {
            "A new configuration file has been generated at " + configFile.absolutePath + "! " +
                    "Please update its values and run again."
        }
        return
    }

    // TODO Possibly just supply the "config" variable?
    TBAWebhook(config["port"].toInt(), config["team"], config).start()
}