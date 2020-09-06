package info.log.demo.domain.user.dto

import info.log.demo.common.ImageUploadUtil
import info.log.demo.domain.user.entity.User
import org.springframework.web.multipart.MultipartFile

data class InsertUserRequestDto(
        val email: String,
        val name: String,
        val password: String,
        val nickname: String?,
        val description: String?,
        val profileImage: MultipartFile?) {

    fun toUserEntity(): User {
        val profileImageUrl: String? = ImageUploadUtil.uploadImage(profileImage)
        return User(email, password, name, nickname ?: name, description, profileImageUrl)
    }
}