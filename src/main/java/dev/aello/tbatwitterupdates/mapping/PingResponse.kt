package dev.aello.tbatwitterupdates.mapping

import dev.aello.tbatwitterupdates.mapping.ping.MessageData

data class PingResponse(
        val messageData: MessageData
) : Response()