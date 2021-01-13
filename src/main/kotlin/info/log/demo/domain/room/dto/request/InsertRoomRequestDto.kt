package info.log.demo.domain.room.dto.request

import info.log.demo.common.ImageUploadUtil
import info.log.demo.domain.room.entity.Room
import info.log.demo.domain.room.enums.RoomType
import org.springframework.web.multipart.MultipartFile

data class InsertRoomRequestDto(
        val title: String,
        val password: String?,
        val roomType: RoomType = RoomType.PUBLIC,
        val userEmails: Set<String>,
        val image: MultipartFile?,
        val maxUserCount: Int = DEFAULT_MAX_USER_COUNT
) {
    companion object {
        const val DEFAULT_MAX_USER_COUNT = 100
    }

    fun toRoomEntity(): Room {
        val imageUrl: String? = ImageUploadUtil.uploadImage(image)
        return Room(
                password = password,
                roomType = roomType,
                title = title,
                imageUrl = imageUrl,
                maxUserCount = maxUserCount
        )
    }
}