package vez.api.config.filters

/**
 * Объект с учетными данными пользователя. Передается в запросе на аутентификацию.
 * */
class AccountCredentials {

    lateinit var username: String
    lateinit var password: String

}
