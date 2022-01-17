package dev.aello.tbatwitterupdates.mapping.matchScore

data class Match(
        val compLevel: String,
        val matchNumber: Int,
        val timeString: String,
        val time: Long,
        val alliances: Alliances
)