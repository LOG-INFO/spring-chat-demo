package info.log.demo.domain.message.dto.request

import info.log.demo.domain.message.entity.Message
import info.log.demo.domain.message.entity.MessageContent
import info.log.demo.domain.message.enums.MessageContentType
import info.log.demo.domain.message.enums.MessageType.NORMAL
import info.log.demo.domain.room.entity.Room

data class SendMessageDto(
        val userEmail: String,
        val contentType: MessageContentType,
        val content: String
) {
    fun toEntity(room: Room): Message {
//        if(contentType == MessageContentType.IMAGE) {
//            val imageUrl: String? = ImageUploadUtil.uploadImage()
//        }
        return Message(room, MessageContent(content, contentType), NORMAL)
    }
}