package info.log.demo.domain.room

import info.log.demo.domain.room.entity.Room
import info.log.demo.domain.room.enums.RoomType
import info.log.demo.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RoomRepository: JpaRepository<Room, Long> {
    fun findAllByRoomType(roomType: RoomType): List<Room>

    fun findAllByTitleContaining(title: String): List<Room>
}