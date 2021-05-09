package vez.api.config.filters

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/***
 * Фильт перехватывает входящие http запросы и пытается их аутентифицировать.
 *
 * @param url - URL на который проверка фильтра
 * @param authManager - AuthenticationManager, выполняющий аутентификацию
 */
class JWTLoginFilter(url: String, authManager: AuthenticationManager)
                    : AbstractAuthenticationProcessingFilter( AntPathRequestMatcher(url) ) {

    init {
        this.authenticationManager = authManager
    }

    /**
     * Метод пытается аутентифицировать http запрос, получая объект с учетными данными из httpRequest
     * */
    @Throws(AuthenticationException::class, IOException::class, ServletException::class)
    override fun attemptAuthentication(
        httpRequest: HttpServletRequest,
        httpResp: HttpServletResponse
    ): Authentication {

        // Получаем из запроса объект с учетными данными
        val credentials = ObjectMapper().readValue(httpRequest.inputStream, AccountCredentials::class.java)

        // Аутентифицируем созданный токен с помощью authenticationManager
        return this.authenticationManager.authenticate(
                        UsernamePasswordAuthenticationToken(
                            credentials.username,
                            credentials.password,
                            emptyList()
                        )
        )
    }

    /**
     * Вызывается при успешной аутентификации пользователя. Добавляет в заголовок Http ответа токен аутентификации
     * */
    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(
                        request: HttpServletRequest,
                        response: HttpServletResponse,
                        chain: FilterChain,
                        authResult: Authentication) {

        TokenAuthenticationService.addAuthentication(response, authResult.name)
    }
}
