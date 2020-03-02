package dev.aello.tbatwitterupdates.mapping

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "message_type",
        defaultImpl = Void::class)
@JsonSubTypes(
        JsonSubTypes.Type(value = PingResponse::class, name = "ping"),
        JsonSubTypes.Type(value = MatchScoreResponse::class, name = "match_score")
)
abstract class Response {
    val messageType: String = ""
}