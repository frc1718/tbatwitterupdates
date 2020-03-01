package dev.aello.tbatwitterupdates.mapping

import dev.aello.tbatwitterupdates.mapping.matchScore.MessageData

data class MatchScoreResponse(
        val message_data: MessageData
) : Response()