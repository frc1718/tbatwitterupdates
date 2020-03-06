package dev.aello.tbatwitterupdates.webhooks

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import dev.aello.tbatwitterupdates.components.Config
import dev.aello.tbatwitterupdates.mapping.MatchScoreResponse
import dev.aello.tbatwitterupdates.mapping.PingResponse
import dev.aello.tbatwitterupdates.mapping.Response
import dev.aello.tbatwitterupdates.mapping.VerificationResponse
import dev.aello.twitter.Twitter
import dev.aello.twitter.utils.TwitterCredentials
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.request.header
import io.ktor.request.receive
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import mu.KotlinLogging
import org.apache.commons.codec.digest.DigestUtils

class TBAWebhook(port: Int, private val team: String, private val config: Config) {
    private val logger = KotlinLogging.logger(this::class.java.simpleName)
    private val server = embeddedServer(Netty, port = port) {
        routing {
            post("/") {
                val checksumHeader = call.request.header("X-TBA-Checksum").toString()
                if (!verifyIntegrity(checksumHeader, call.receiveText())) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }

                val statusCode = when (val response = call.receive<Response>()) {
                    is PingResponse -> handlePingRequest(response)
                    is MatchScoreResponse -> handleMatchScoreRequest(response)
                    is VerificationResponse -> handleVerificationRequest(response)
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

    private fun verifyIntegrity(checksumHeader: String, requestBody: String): Boolean {
        val checksum = DigestUtils.sha1Hex(config["secret"] + requestBody).toLowerCase()
        return checksum == checksumHeader
    }

    private fun handlePingRequest(response: PingResponse): HttpStatusCode {
        logger.info { "Ping received! Message: " + response.messageData.desc }
        return HttpStatusCode.OK
    }

    private fun handleVerificationRequest(response: VerificationResponse): HttpStatusCode {
        logger.info { "Verification key: " + response.messageData.verificationKey }
        return HttpStatusCode.OK
    }

    private val compLevelMap = mapOf("qm" to "Quals",
            "ef" to "Eighths",
            "qf" to "Quarters",
            "sf" to "Semis",
            "f" to "Finals")

    private fun handleMatchScoreRequest(response: MatchScoreResponse): HttpStatusCode {
        val match = response.messageData.match
        val teamAlliance = if (team in match.alliances.blue.teams) "Blue" else "Red"
        val opposingAllianceScore: Int
        val score: Int

        if (teamAlliance == "blue") {
            opposingAllianceScore = match.alliances.red.score
            score = match.alliances.blue.score
        } else {
            opposingAllianceScore = match.alliances.blue.score
            score = match.alliances.red.score
        }

        val result = if (score > opposingAllianceScore) "Win" else "Loss"
        val compLevel = compLevelMap[match.compLevel]

        val tweet = "- $compLevel ${match.matchNumber} -\n" +
                "Result: $result\n" +
                "Score: $score - $opposingAllianceScore\n" +
                "Alliance: $teamAlliance"

        val credentials = TwitterCredentials(config["accessToken"],
                config["accessTokenSecret"],
                config["consumerApiKey"],
                config["consumerApiKeySecret"])

        Twitter(credentials).updateStatus(tweet)

        return HttpStatusCode.OK
    }
}