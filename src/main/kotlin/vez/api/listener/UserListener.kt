package vez.api.listener

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import vez.api.model.User
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

/**
 * Кодирует пароль перед сохранением пользователя
 * */
class UserListener {

    @PrePersist
    @PreUpdate
    fun hashPassword(user: User) {
        user.password = BCryptPasswordEncoder().encode(user.password);
    }
}
