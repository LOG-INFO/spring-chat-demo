package info.log.demo.domain.room

import info.log.demo.domain.room.dto.request.*
import info.log.demo.domain.room.entity.Room
import info.log.demo.domain.room.enums.RoomType
import info.log.demo.domain.room.exception.RoomNotFoundException
import info.log.demo.domain.user.UserRepository
import info.log.demo.domain.user.UserService
import info.log.demo.domain.user.entity.User
import org.springframework.stereotype.Service

//TODO: Service, Repository 의존성 관리 어떻게 해야하는지 고민
@Service
class RoomService(
        private val roomRepository: RoomRepository,
        private val userService: UserService,
        private val userRepository: UserRepository) {

    fun search(searchRoomDto: SearchRoomDto): List<Room> {
        if (searchRoomDto.title != null) {
            return roomRepository.findAllByTitleContaining(searchRoomDto.title)
        }

        if (searchRoomDto.userEmail != null) {
            return userService.findByEmail(searchRoomDto.userEmail).rooms.toList()
        }

        return this.findAllPublicRooms()
    }

    fun findAllPublicRooms(): List<Room> {
        return roomRepository.findAllByRoomType(RoomType.PUBLIC)
    }

    fun findById(roomId: Long): Room {
        val roomOptional = roomRepository.findById(roomId)
        if (roomOptional.isEmpty) {
            throw RoomNotFoundException("존재하지 않는 방입니다 roomId: $roomId")
        }

        return roomOptional.get()
    }

    fun findUsersByRoomId(roomId: Long): Set<User> {
        val room = findById(roomId)
        return room.users
    }

    fun findAllByTitle(title: String): List<Room> {
        return roomRepository.findAllByTitleContaining(title)
    }

    fun insert(insertRoomRequestDto: InsertRoomRequestDto): Room {
        val room = insertRoomRequestDto.toRoomEntity()
        val users = userService.findAllByEmailsIn(insertRoomRequestDto.userEmails)
        room.users.addAll(users)

        return roomRepository.save(room)
    }

    fun update(roomId: Long, updateRoomRequestDto: UpdateRoomRequestDto): Room {
        val room = this.findById(roomId)
        room.update(updateRoomRequestDto)

        return roomRepository.save(room)
    }

    fun delete(roomId: Long) {
        val room = this.findById(roomId)

        roomRepository.delete(room)
    }

    fun enterRoom(roomId: Long, enterRoomRequestDto: EnterRoomRequestDto): Room {
        val room: Room = findById(roomId)
        val users: List<User> = userService.findAllByEmailsIn(enterRoomRequestDto.userEmails)
        room.users.addAll(users)
        users.forEach { it.rooms.add(room); userRepository.save(it) }
        return room
    }

    fun kickOutFromRoom(roomId: Long, kickOutFromRoomRequestDto: KickOutFromRoomRequestDto): Room {
        val room: Room = findById(roomId)
        val users: List<User> = userService.findAllByEmailsIn(kickOutFromRoomRequestDto.userEmails)
        room.users.removeAll(users)
        users.forEach { it.rooms.remove(room); userRepository.save(it) }

        if(room.users.size < 1) {
            roomRepository.delete(room)
        }

        return room
    }
}