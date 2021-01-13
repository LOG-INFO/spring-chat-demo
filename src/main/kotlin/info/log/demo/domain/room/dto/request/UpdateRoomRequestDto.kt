package info.log.demo.domain.room.dto.request

import info.log.demo.domain.room.enums.RoomType
import org.springframework.web.multipart.MultipartFile

data class UpdateRoomRequestDto(
        val title: String?,
        val password: String?,
        val roomType: RoomType?,
        val image: MultipartFile?,
        val maxUserCount: Int?
)