package info.log.demo.domain.room.entity

import info.log.demo.common.ImageUploadUtil
import info.log.demo.domain.room.dto.request.UpdateRoomRequestDto
import info.log.demo.domain.room.dto.response.SimpleRoomResponseDto
import info.log.demo.domain.room.enums.RoomType
import info.log.demo.domain.user.dto.response.SimpleUserResponseDto
import info.log.demo.domain.user.entity.User
import lombok.EqualsAndHashCode
import java.time.LocalDateTime
import java.util.stream.Collectors.toSet
import javax.persistence.*

@Entity
@Table(name = "rooms")
data class Room(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var roomId: Long? = null,
        var password: String?,
        @Enumerated(EnumType.STRING)
        var roomType: RoomType,
        var title: String,
        var imageUrl: String?,
        var maxUserCount: Int,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var updatedAt: LocalDateTime = LocalDateTime.now(),
        @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinTable(name = "users_rooms",
                joinColumns = [JoinColumn(name = "room_id", referencedColumnName = "roomId")],
                inverseJoinColumns = [JoinColumn(name = "email", referencedColumnName = "email")])
        val users: MutableSet<User> = mutableSetOf()) {

    fun changePassword(newPassword: String) {
        this.password = newPassword
    }

    fun changeRoomType(newRoomType: RoomType) {
        this.roomType = newRoomType
    }

    fun update(updateRoomRequestDto: UpdateRoomRequestDto) {
        this.title = updateRoomRequestDto.title ?: this.title
        this.password = updateRoomRequestDto.password ?: this.password
        this.roomType = updateRoomRequestDto.roomType ?: this.roomType
        if (roomType == RoomType.PRIVATE) {
            this.password = updateRoomRequestDto.password ?: this.password
        }
        this.imageUrl = ImageUploadUtil.uploadImage(updateRoomRequestDto.image) ?: this.imageUrl
        this.maxUserCount = updateRoomRequestDto.maxUserCount ?: this.maxUserCount

        this.updatedAt = LocalDateTime.now()
    }

    fun toSimpleRoomResponseDto(): SimpleRoomResponseDto {
        val users: Set<SimpleUserResponseDto> = users.stream().map { it.toSimpleUserResponseDto() }.collect(toSet())
        return SimpleRoomResponseDto(roomId ?: 0L, roomType, title, imageUrl, maxUserCount, createdAt, updatedAt, users)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Room

        if (roomId != other.roomId) return false
        if (password != other.password) return false
        if (roomType != other.roomType) return false
        if (title != other.title) return false
        if (imageUrl != other.imageUrl) return false
        if (maxUserCount != other.maxUserCount) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = roomId?.hashCode() ?: 0
        result = 31 * result + (password?.hashCode() ?: 0)
        result = 31 * result + roomType.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + (imageUrl?.hashCode() ?: 0)
        result = 31 * result + maxUserCount
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + updatedAt.hashCode()
        return result
    }


}