package vez.api.services

import vez.api.model.User

interface UserService {

    fun attemptRegistration(user: User): User

    fun updateStatus(user: User, updateDetails: User): User

    fun getUserByName(username: String): User?

    fun getUserById(id: Long): User

    fun getUserList(): List<User>

    fun isUsernameExists(username: String): Boolean

}