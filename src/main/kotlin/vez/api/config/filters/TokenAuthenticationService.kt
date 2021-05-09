package vez.api.config.filters

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

internal object TokenAuthenticationService {

    private val TOKEN_EXPIRY: Long = 864_000_000
    private val TOKEN_PREFIX = "Bearer"
    private val SECRET = "XthnbXtBc,jre<fynbr"
    private val AUTH_HEADER = "Authorization"

    /**
     * Создает из имени пользователя JWT токен и добавляет его к заголовку 'Authorization' ответа.
     * Используется в фильтре (@see JWTLoginFilter.kt) при успешной аутентификации пользователя.
     * */
    fun addAuthentication(response: HttpServletResponse, username: String) {

        val JWT = Jwts.builder()
            .setSubject(username)
            .setExpiration( Date(System.currentTimeMillis() + TOKEN_EXPIRY))
            .signWith( SignatureAlgorithm.HS512, SECRET)
            .compact()

        response.addHeader(AUTH_HEADER, "$TOKEN_PREFIX $JWT")
    }

    /**
     * Получает JWT токен из хидера запроса, вытаскивает имя пользователя и возвращает Authentication (объект spring-security)
     * */
    fun getAuthentication(request: HttpServletRequest) : Authentication? {
        val token =  request.getHeader(AUTH_HEADER)
        if (token != null) {
            // синтаксический разбор токена
            val username = Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws( token.replace(TOKEN_PREFIX, "") )
                .body.subject

            if (username != null) {
                return UsernamePasswordAuthenticationToken(username, null, emptyList())
            }
        }
        return null
    }

    }
