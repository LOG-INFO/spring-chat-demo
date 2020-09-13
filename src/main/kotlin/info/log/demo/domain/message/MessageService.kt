package info.log.demo.domain.message

import info.log.demo.domain.message.dto.request.SendMessageDto
import info.log.demo.domain.message.entity.Message
import info.log.demo.domain.room.RoomRepository
import info.log.demo.domain.room.RoomService
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
class MessageService(
        private val messageRepository: MessageRepository,
        private val roomService: RoomService) {

    fun insert(roomId: Long, sendMessageDto: SendMessageDto) {
        val room: Room = roomService.findById(roomId)
        val newMessage = sendMessageDto.toEntity(room)
        messageRepository.save(newMessage)
    }

    fun findAllByRoomRoomIdOrderByCreatedAt(roomId: Long): List<Message> {
        return messageRepository.findAllByRoomRoomIdOrderByCreatedAt(roomId)
    }

    fun delete(messageId: Long) {
        messageRepository.deleteById(messageId)
    }
}