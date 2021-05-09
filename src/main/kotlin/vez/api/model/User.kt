package vez.api.model

import org.intellij.lang.annotations.Pattern
import org.springframework.format.annotation.DateTimeFormat
import vez.api.listener.UserListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
@EntityListeners(UserListener::class)
class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)  var id: Long = 0

    @DateTimeFormat var createAt: LocalDateTime = LocalDateTime.now();

    @Column(unique = true) var username: String = ""
    @Column var phone: String = ""
    @Column var password: String = ""

    @Column  @Pattern("\\A(activated|deactivated)\\z")  var status: String = "activated"

}

