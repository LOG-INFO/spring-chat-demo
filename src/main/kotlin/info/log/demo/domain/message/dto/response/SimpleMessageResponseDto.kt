package info.log.demo.domain.message.dto.response

import info.log.demo.domain.message.entity.MessageContent
import info.log.demo.domain.message.enums.MessageType
import info.log.demo.domain.room.dto.response.SimpleRoomResponseDto
import java.time.LocalDateTime

class SimpleMessageResponseDto(
        val roomId: Long,
        val content: MessageContent,
        val type: MessageType = MessageType.NORMAL,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        val removed: Boolean = false
)