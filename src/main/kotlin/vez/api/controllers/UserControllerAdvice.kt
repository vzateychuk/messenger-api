package vez.api.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import vez.api.exception.InvalidUserIdException
import vez.api.exception.UserNotFoundException
import vez.api.exception.UserStatusEmptyException
import vez.api.exception.UsernameUnavailableException

/**
 * Классы ControllerAdvice используются для обработки ошибок
 * */
@ControllerAdvice
class UserControllerAdvice {

    @ExceptionHandler(UsernameUnavailableException::class)
    fun usernameUnavailable(exception: UsernameUnavailableException) : ResponseEntity<ErrorResponse> {

        val res = ErrorResponse(ResponseConstants.USERNAME_UNAVAILABLE.value, exception.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }

    @ExceptionHandler(InvalidUserIdException::class)
    fun invalidUser(exception: InvalidUserIdException) : ResponseEntity<ErrorResponse> {

        val res = ErrorResponse(ResponseConstants.INVALID_USER_ID.value, exception.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }

    @ExceptionHandler(UserStatusEmptyException::class)
    fun userStatusEmpty(exception: UserStatusEmptyException) : ResponseEntity<ErrorResponse> {

        val res = ErrorResponse(ResponseConstants.USER_STATUS_EMPTY.value, exception.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun userNotFound(exception: UserNotFoundException) : ResponseEntity<ErrorResponse> {

        val res = ErrorResponse(ResponseConstants.USER_NOT_FOUND.value, exception.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }

}