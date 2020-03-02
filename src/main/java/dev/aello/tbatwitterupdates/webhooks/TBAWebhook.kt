package dev.aello.tbatwitterupdates.webhooks

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import dev.aello.tbatwitterupdates.mapping.MatchScoreResponse
import dev.aello.tbatwitterupdates.mapping.PingResponse
import dev.aello.tbatwitterupdates.mapping.Response
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

class TBAWebhook(port: Int, team: String) {
    private val server = embeddedServer(Netty, port = port) {
        routing {
            post("/") {
                val statusCode = when (val response = call.receive<Response>()) {
                    is PingResponse -> handlePingRequest(response)
                    is MatchScoreResponse -> handleMatchScoreRequest(response)
                    else -> HttpStatusCode.BadRequest
                }

                call.respond(statusCode)
            }
        }

        install(ContentNegotiation) {
            jackson {
                propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
                configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                registerKotlinModule()
            }
        }
    }

    fun start() {
        server.start(wait = true)
    }

    private fun handlePingRequest(response: PingResponse): HttpStatusCode {
        return HttpStatusCode.OK
    }

    private fun handleMatchScoreRequest(response: MatchScoreResponse): HttpStatusCode {
        return HttpStatusCode.OK
    }
}