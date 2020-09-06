package info.log.demo.domain.user.entity

import info.log.demo.common.ImageUploadUtil
import info.log.demo.domain.user.dto.UpdateUserRequestDto
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class User(
        @Id
        val email: String,
        var password: String,
        var name: String,
        var nickname: String,
        var description: String?,
        var profileImageUrl: String?,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var updatedAt: LocalDateTime = LocalDateTime.now()) {

    fun update(updateUserRequestDto: UpdateUserRequestDto) {
        if (updateUserRequestDto.password != null) {
            this.password = updateUserRequestDto.password
        }
        if (updateUserRequestDto.name != null) {
            this.name = updateUserRequestDto.name
        }
        if (updateUserRequestDto.nickname != null) {
            this.nickname = updateUserRequestDto.nickname
        }
        if (updateUserRequestDto.description != null) {
            this.description = updateUserRequestDto.description
        }
        if (updateUserRequestDto.profileImage != null) {
            this.profileImageUrl = ImageUploadUtil.uploadImage(updateUserRequestDto.profileImage)
        }

        this.updatedAt = LocalDateTime.now()
    }
}