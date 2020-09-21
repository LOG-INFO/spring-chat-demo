package info.log.demo.domain.user.entity

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import javax.persistence.Id

@RedisHash("WebSocketSession")
data class WebSocketSession(
        @Id val id: String,
        @Indexed var userEmail: String
)