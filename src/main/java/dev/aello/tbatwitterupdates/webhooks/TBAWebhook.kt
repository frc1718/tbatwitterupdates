package dev.aello.tbatwitterupdates.webhooks

import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

class TBAWebhook(port: Int, team: String) {
    private val server = embeddedServer(Netty, port = port) {
        routing {
            post("/") {
                // TODO handle incoming requests
            }
        }

        install(ContentNegotiation) {
            jackson {
                registerKotlinModule()
            }
        }
    }
}