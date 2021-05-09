package vez.api.services

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import vez.api.exception.UserNotFoundException
import vez.api.repos.UserRepo

/**
 * Находит пользователя по username или генерируется исключение. Используется в (@see WebSecurityConfig.kt)
 * */
@Service
class AppUserDetailsService(val userRepo: UserRepo) : UserDetailsService {

    @Throws(UserNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {

        val user = userRepo.findByUsername(username)
            ?: throw UserNotFoundException("User '$username' not exists")

        return User(user.username,
                    user.password,
                    ArrayList<GrantedAuthority>()
        )
    }
}
