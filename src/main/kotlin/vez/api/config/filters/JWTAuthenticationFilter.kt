package vez.api.config.filters

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JWTAuthenticationFilter : GenericFilterBean() {

    /**
     * Вызывается контейнером всякий раз когда запрос проходит через фильтры
     * */
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest,
                          response: ServletResponse,
                          chain: FilterChain) {

        // Получает JWT токен из хидера запроса, вытаскивает имя пользователя и возвращает Authentication
        val authentication = TokenAuthenticationService.getAuthentication(request as HttpServletRequest)
        // Добавляем полученный authentication в контекст spring-security
        SecurityContextHolder.getContext().authentication = authentication;
        // Продолжаем цепочку вызовов spring-security
        chain.doFilter(request, response)
    }

}
