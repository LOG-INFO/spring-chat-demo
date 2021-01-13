package info.log.demo.domain.message.entity

import info.log.demo.domain.message.enums.MessageContentType
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Embeddable
data class MessageContent(
        var content: String,
        @Enumerated(EnumType.STRING)
        var contentType: MessageContentType = MessageContentType.TEXT
)