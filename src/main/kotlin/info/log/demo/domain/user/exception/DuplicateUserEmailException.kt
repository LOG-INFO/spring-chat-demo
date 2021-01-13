package info.log.demo.domain.user.exception

import java.lang.RuntimeException

class DuplicateUserEmailException(message: String?) : RuntimeException(message) {

}