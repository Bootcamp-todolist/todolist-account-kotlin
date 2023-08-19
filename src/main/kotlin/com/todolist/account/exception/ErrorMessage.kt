package com.todolist.account.exception

enum class ErrorMessage(val value: String) {
    USER_NOT_EXIST("user_not_exist"),
    REPEATED_USER_NAME("repeated_user_name"),
    AUTHORIZE_FAILED("authorize_failed"),
    OLD_PASSWORD_WRONG("old_password_wrong");

}

