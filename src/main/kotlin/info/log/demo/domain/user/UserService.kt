package info.log.demo.domain.user

import info.log.demo.domain.user.dto.InsertUserRequestDto
import info.log.demo.domain.user.dto.SearchUserDto
import info.log.demo.domain.user.dto.UpdateUserRequestDto
import info.log.demo.domain.user.exception.DuplicateUserEmailException
import info.log.demo.domain.user.exception.UserNotFoundException
import info.log.demo.domain.user.entity.User
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

    fun search(searchUserDto: SearchUserDto): List<User> {
        if (searchUserDto.email != null) {
            return listOf(this.findByEmail(searchUserDto.email))
        } else if (searchUserDto.nickname != null) {
            return this.findAllByNickName(searchUserDto.nickname)
        }
        return this.findAll()
    }

    fun findAll(): List<User> {
        return userRepository.findAll()
    }

    fun findByEmail(email: String): User {
        val userOptional = userRepository.findByEmail(email)

        if (userOptional.isEmpty) {
            throw UserNotFoundException("존재하지 않는 유저 email: $email")
        }

        return userOptional.get()
    }

    fun findAllByNickName(nickname: String): List<User> {
        return userRepository.findAllByNickname(nickname)
    }

    fun insert(insertUserRequestDto: InsertUserRequestDto): User {
        val userOptional = userRepository.findByEmail(insertUserRequestDto.email)

        if (userOptional.isPresent) {
            throw DuplicateUserEmailException("중복된 email: ${insertUserRequestDto.email}")
        }

        val user = insertUserRequestDto.toUserEntity()
        return userRepository.save(user)
    }

    fun update(email: String, updateUserRequestDto: UpdateUserRequestDto): User {
        val user = this.findByEmail(email)
        user.update(updateUserRequestDto)

        return userRepository.save(user)
    }
}