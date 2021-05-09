package vez.api.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import vez.api.config.filters.JWTAuthenticationFilter
import vez.api.config.filters.JWTLoginFilter
import vez.api.services.AppUserDetailsService

/**
 * Включает и настраивает поддержку SpringSecurity
 * */
@Configuration
@EnableWebSecurity
class WebSecurityConfig(val userDetailService: AppUserDetailsService) : WebSecurityConfigurerAdapter() {

    /**
     * Конфигурация определяет какие из URL-s должны быть защищены а какие доступны.
     * Устанавливает проверку на наличие JWT в заголовке запроса.
     * */
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {

        // Разрешить все POST к /login и /user/registration
        http.csrf().disable().authorizeRequests()
            .antMatchers(HttpMethod.POST, "/users/registration").permitAll()
            .antMatchers(HttpMethod.POST, "/login").permitAll()
            .anyRequest().authenticated()
            .and()
                // Фильтруем запросы к "login" с помощью кастомного JWTLoginFilter
            .addFilterBefore(
                JWTLoginFilter("/login", this.authenticationManager()),
                UsernamePasswordAuthenticationFilter::class.java
            )
                // Запросы прошедшие аутентификацию должны содержать JWT заголовок. Проверяем на наличие JWT в заголовке.
            .addFilterBefore(
                JWTAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter::class.java
            )
    }

    /**
     * Устанавливаем наш кастомный userDetailService для предоставления информации о пользователях.
     * Указываем кодировщик пароля.
     * */
    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {

        auth.userDetailsService(userDetailService)
            .passwordEncoder( BCryptPasswordEncoder() )
    }
}