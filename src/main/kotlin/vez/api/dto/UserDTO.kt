package vez.api.dto

import vez.api.model.User

class UserDTO(user: User) {

    var id: Long = user.id
    var username: String = user.username
    var phone: String = user.phone
    var status: String = user.status
    var createdAt: String = user.createAt.toString()

}