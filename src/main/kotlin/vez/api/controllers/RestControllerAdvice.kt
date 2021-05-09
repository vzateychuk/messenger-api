package vez.api.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import vez.api.exception.UserDeactivatedException

/**
 * Обработчик исключений для ошибок UserDeactivated
 * */
@ControllerAdvice
class RestControllerAdvice {

    @ExceptionHandler(UserDeactivatedException::class)
    fun userDeactivated(exception: UserDeactivatedException) : ResponseEntity<ErrorResponse> {

        val res = ErrorResponse(ResponseConstants.ACCOUNT_DEACTIVATED.value, exception.message)
        // вернем HTTP 403 - неудачная попытка авторизациии
        return ResponseEntity(res, HttpStatus.UNAUTHORIZED)

    }

}