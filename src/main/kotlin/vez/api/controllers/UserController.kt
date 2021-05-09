package vez.api.controllers

import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import vez.api.dto.UserDTO
import vez.api.model.User
import vez.api.services.UserService

@RestController()
@RequestMapping("/users")
class UserController (val userService : UserService) {

    @PostMapping("/registration")
    fun create(@Validated @RequestBody user: User): ResponseEntity<UserDTO> {

        val registered = userService.attemptRegistration(user)
        return ResponseEntity.ok( UserDTO(registered) )
    }

    @GetMapping("/{userId}")
    fun getUser(@PathVariable("userId") userId: Long): ResponseEntity<UserDTO> {

        val user = userService.getUserById(userId)
        return ResponseEntity.ok( UserDTO(user) )
    }

    @GetMapping
    fun getUserList(): ResponseEntity<List<UserDTO>> {

        val users: List<UserDTO> = userService.getUserList().map { UserDTO(it) }
        return ResponseEntity.ok( users )
    }

}