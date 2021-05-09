package vez.api.exception

class UserDeactivatedException(override val message: String) : RuntimeException() { }

class UsernameUnavailableException(override val message: String) : RuntimeException() { }

class InvalidUserIdException(override val message: String) : RuntimeException() { }

class UserStatusEmptyException(override val message: String = "User status can't be empty") : RuntimeException() { }

class UserNotFoundException(override val message: String) : RuntimeException() { }


