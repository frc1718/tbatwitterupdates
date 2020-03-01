package dev.aello.tbatwitterupdates.mapping

import dev.aello.tbatwitterupdates.mapping.matchScore.Match

data class MatchScoreResponse(
        val event_name: String,
        val match: Match
) : Response()