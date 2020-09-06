package info.log.demo.domain.user

import info.log.demo.domain.user.exception.DuplicateUserEmailException
import info.log.demo.domain.user.exception.UserNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class UserControllerAdvice {

    @ExceptionHandler(UserNotFoundException::class)
    fun userNotFoundException(userNotFoundException: UserNotFoundException): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFoundException.message)
    }

    @ExceptionHandler(DuplicateUserEmailException::class)
    fun duplicateUserEmailException(duplicateUserEmailException: DuplicateUserEmailException): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(duplicateUserEmailException.message)
    }
}