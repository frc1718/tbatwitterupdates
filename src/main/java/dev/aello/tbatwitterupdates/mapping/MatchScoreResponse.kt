package dev.aello.tbatwitterupdates.mapping

import dev.aello.tbatwitterupdates.mapping.matchScore.MessageData

data class MatchScoreResponse(
        val messageData: MessageData
) : Response()