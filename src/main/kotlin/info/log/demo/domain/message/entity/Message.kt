package info.log.demo.domain.message.entity

import info.log.demo.domain.message.dto.response.SimpleMessageResponseDto
import info.log.demo.domain.message.enums.MessageType
import info.log.demo.domain.message.enums.MessageType.NORMAL
import info.log.demo.domain.room.entity.Room
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table("messages")
data class Message(
        @ManyToOne
        @JoinColumn(name = "roomId")
        val room: Room,
        @Embedded
        val content: MessageContent,
        @Enumerated(EnumType.STRING)
        val type: MessageType = NORMAL,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var removed: Boolean = false) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val messageId: Long? = null

    fun toSimpleMessageDto(): SimpleMessageResponseDto {
        return SimpleMessageResponseDto(room.roomId ?: 0L, content, type, createdAt, removed)
    }
}