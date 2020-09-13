package info.log.demo.domain.user.dto.response

import java.time.LocalDateTime

data class SimpleUserResponseDto(
        val email: String,
        var name: String,
        var nickname: String,
        var description: String?,
        var profileImageUrl: String?,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var updatedAt: LocalDateTime = LocalDateTime.now()
)