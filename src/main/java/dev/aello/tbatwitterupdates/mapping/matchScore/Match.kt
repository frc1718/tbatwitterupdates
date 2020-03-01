package dev.aello.tbatwitterupdates.mapping.matchScore

data class Match(
        val comp_level: String,
        val match_number: Int,
        val time_string: String,
        val time: Long,
        val alliances: Alliances
)