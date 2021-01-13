package info.log.demo.domain.message

import info.log.demo.domain.message.entity.Message
import info.log.demo.domain.room.entity.Room
import info.log.demo.domain.room.enums.RoomType
import info.log.demo.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MessageRepository : JpaRepository<Message, Long> {
    fun findAllByRoomRoomIdOrderByCreatedAt(roomId: Long): List<Message>
}