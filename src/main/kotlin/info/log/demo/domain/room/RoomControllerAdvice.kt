package info.log.demo.domain.room

import info.log.demo.domain.room.exception.RoomNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class RoomControllerAdvice {

    @ExceptionHandler(RoomNotFoundException::class)
    fun roomNotFoundException(roomNotFoundException: RoomNotFoundException): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(roomNotFoundException.message)
    }
}