package info.log.demo.domain.message

import info.log.demo.domain.message.dto.request.SendMessageDto
import info.log.demo.domain.message.dto.response.SimpleMessageResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors.toList

@RestController
@RequestMapping("/rooms/{roomId}/messages")
class MessageController(val messageService: MessageService) {

    @PostMapping
    fun sendMessage(@PathVariable roomId: Long, @RequestBody sendMessageDto: SendMessageDto): SimpleMessageResponseDto {
        return messageService.insert(roomId, sendMessageDto).toSimpleMessageDto()
    }

    @DeleteMapping
    fun search(@PathVariable roomId: Long): List<SimpleMessageResponseDto> {
        return messageService.findAllByRoomRoomIdOrderByCreatedAt(roomId).stream()
                .map { it.toSimpleMessageDto() }
                .collect(toList())
    }

    @DeleteMapping("/{messageId}")
    fun delete(@PathVariable roomId: Long, @PathVariable messageId: Long): ResponseEntity<Any> {
        messageService.delete(messageId)
        return ResponseEntity.ok().build()
    }
}