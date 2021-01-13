package info.log.demo.domain.user

import info.log.demo.domain.user.entity.WebSocketSession
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WebsocketSessionRepository : CrudRepository<WebSocketSession, String> {
    fun findAllByUserEmail(userEmail: String): List<WebSocketSession>
}