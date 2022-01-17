package dev.aello.tbatwitterupdates.mapping.matchScore

data class AllianceData(
        val score: Int,
        val teams: List<String>
)

data class Alliances(
        val blue: AllianceData,
        val red: AllianceData
)