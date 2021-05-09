package vez.api.controllers

enum class ResponseConstants(val value: String) {
    SUCCESS("success"),
    ERROR("error"),
    ACCOUNT_DEACTIVATED("account_deactivated"),
    USERNAME_UNAVAILABLE("username_unavailable"),
    INVALID_USER_ID("invalid_user_id"),
    USER_NOT_FOUND("user_not_found"),
    USER_STATUS_EMPTY("user_status_empty");
}
