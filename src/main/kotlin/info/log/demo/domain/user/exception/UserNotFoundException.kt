package info.log.demo.domain.user.exception

import java.lang.RuntimeException

class UserNotFoundException(message: String?) : RuntimeException(message) {

}