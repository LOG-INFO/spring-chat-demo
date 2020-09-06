package info.log.demo.domain.user

import info.log.demo.domain.user.dto.InsertUserRequestDto
import info.log.demo.domain.user.dto.SearchUserDto
import info.log.demo.domain.user.dto.UpdateUserRequestDto
import info.log.demo.domain.user.entity.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(val userService: UserService) {
    @GetMapping
    fun search(@ModelAttribute searchUserDto: SearchUserDto): List<User> {
        return userService.search(searchUserDto)
    }

    @PostMapping(consumes = ["multipart/form-data"])
    fun insert(@ModelAttribute insertUserRequestDto: InsertUserRequestDto): ResponseEntity<Any>? {
        val newUser = userService.insert(insertUserRequestDto)
        return ResponseEntity.ok().body(newUser)
    }

    @PatchMapping("/{email}")
    fun update(@PathVariable email: String, @RequestBody updateUserRequestDto: UpdateUserRequestDto): ResponseEntity<Any> {
        val updatedUser = userService.update(email, updateUserRequestDto)
        return ResponseEntity.ok().body(updatedUser)
    }

    @DeleteMapping("/{email}")
    fun delete(@PathVariable email: String): ResponseEntity<Any> {
        val updatedUser = userService.delete(email)
        return ResponseEntity.ok().body(updatedUser)
    }
}