package vez.api.repos

import org.springframework.data.jpa.repository.JpaRepository
import vez.api.model.User

interface UserRepo: JpaRepository<User, Long> {

    fun findByUsername(username: String): User?
}