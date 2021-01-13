package info.log.demo.domain.room.dto.response

import info.log.demo.domain.room.enums.RoomType
import info.log.demo.domain.user.dto.response.SimpleUserResponseDto
import java.time.LocalDateTime

data class SimpleRoomResponseDto(
        val roomId: Long,
        val roomType: RoomType,
        val title: String,
        val imageUrl: String?,
        val maxUserCount: Int,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        val updatedAt: LocalDateTime = LocalDateTime.now(),
        val users: Set<SimpleUserResponseDto>
)