package info.log.demo.domain.room

import info.log.demo.domain.room.dto.request.*
import info.log.demo.domain.room.dto.response.SimpleRoomResponseDto
import info.log.demo.domain.user.dto.response.SimpleUserResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors.toList

@RestController
@RequestMapping("/rooms")
class RoomController(val roomService: RoomService) {
    @GetMapping
    fun search(@ModelAttribute searchRoomDto: SearchRoomDto): List<SimpleRoomResponseDto> {
        return roomService.search(searchRoomDto).stream().map { it.toSimpleRoomResponseDto() }.collect(toList())
    }

    @PostMapping(consumes = ["multipart/form-data"])
    fun insert(@ModelAttribute insertRoomRequestDto: InsertRoomRequestDto): SimpleRoomResponseDto {
        val newRoom = roomService.insert(insertRoomRequestDto)
        return newRoom.toSimpleRoomResponseDto()
    }

    @PatchMapping(value = ["/{roomId}"], consumes = ["multipart/form-data"])
    fun update(@PathVariable roomId: Long, @RequestBody updateRoomRequestDto: UpdateRoomRequestDto): SimpleRoomResponseDto {
        val updatedRoom = roomService.update(roomId, updateRoomRequestDto)
        return updatedRoom.toSimpleRoomResponseDto()
    }

    @DeleteMapping("/{roomId}")
    fun delete(@PathVariable roomId: Long): ResponseEntity<Any> {
        roomService.delete(roomId)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{roomId}/users")
    fun enterRoom(@PathVariable roomId: Long): List<SimpleUserResponseDto> {
        return roomService.findUsersByRoomId(roomId).stream().map { it.toSimpleUserResponseDto() }.collect(toList())
    }

    @PostMapping("/{roomId}/users")
    fun enterRoom(@PathVariable roomId: Long, @RequestBody enterRoomRequestDto: EnterRoomRequestDto): SimpleRoomResponseDto {
        val newRoom = roomService.enterRoom(roomId, enterRoomRequestDto)
        return newRoom.toSimpleRoomResponseDto()
    }

    @DeleteMapping("/{roomId}/users")
    fun kickOutFromRoom(@PathVariable roomId: Long, @RequestBody kickOutFromRoomRequestDto: KickOutFromRoomRequestDto): SimpleRoomResponseDto {
        val newRoom = roomService.kickOutFromRoom(roomId, kickOutFromRoomRequestDto)
        return newRoom.toSimpleRoomResponseDto()
    }
}