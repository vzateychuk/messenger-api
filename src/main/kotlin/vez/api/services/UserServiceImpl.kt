package vez.api.services

import org.springframework.stereotype.Service
import vez.api.exception.InvalidUserIdException
import vez.api.exception.UserNotFoundException
import vez.api.exception.UserStatusEmptyException
import vez.api.exception.UsernameUnavailableException
import vez.api.model.User
import vez.api.repos.UserRepo

@Service
class UserServiceImpl(private val repository: UserRepo) : UserService {

    //region Public

    @Throws(UsernameUnavailableException::class)
    override fun attemptRegistration(user: User): User {

        if (this.isUsernameExists(user.username)) {
            throw UsernameUnavailableException("The username ${user.username} already exists")
        }
        val toSave = this.clone(user)
        val saved = this.repository.save( toSave )
        return this.obscurePassword(saved)
    }

    @Throws(UserStatusEmptyException::class)
    override fun updateStatus(user: User, updateDetails: User): User {
        
        if(updateDetails.status.isEmpty()) {
            throw UserStatusEmptyException()
        }

        val toSave = this.clone(user)
        toSave.status = updateDetails.status
        return this.repository.save( toSave )
    }

    @Throws(UserNotFoundException::class)
    override fun getUserByName(username: String): User? {

        val user = repository.findByUsername(username) ?: throw UserNotFoundException("Username: $username")
        return this.obscurePassword(user)
    }

    @Throws(InvalidUserIdException::class)
    override fun getUserById(id: Long): User {

        val userOptional = repository.findById(id)

        return userOptional.map { u -> this.obscurePassword(u) }
                        .orElseThrow { throw InvalidUserIdException("No user $id found") }
    }

    override fun getUserList(): List<User> {
        return repository.findAll()
    }

    override fun isUsernameExists(username: String): Boolean {

        return this.repository.findByUsername(username) != null
    }

    //endregion
    //region Private

    private fun clone(user: User): User {
        val created = User()
        created.username= user.username
        created.phone = user.phone
        created.password = user.password
        return created
    }

    private fun obscurePassword(user: User): User {
        val cloned = this.clone(user);
        cloned.password = "XXX XXX XXX"
        return cloned
    }

    //endregion
}

