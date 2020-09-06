package info.log.demo.domain.user.dto

import org.springframework.web.multipart.MultipartFile

data class UpdateUserRequestDto(
        val name: String?,
        val password: String?,
        val nickname: String?,
        val description: String?,
        val profileImage: MultipartFile?)