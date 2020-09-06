package info.log.demo.domain.user

import info.log.demo.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User, String> {

    fun findByEmail(email: String): Optional<User>

    fun findAllByNickname(nickname: String): List<User>

}