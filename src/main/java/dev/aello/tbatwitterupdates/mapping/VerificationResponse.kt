package dev.aello.tbatwitterupdates.mapping

import dev.aello.tbatwitterupdates.mapping.verification.MessageData

data class VerificationResponse(
        val messageData: MessageData
) : Response()