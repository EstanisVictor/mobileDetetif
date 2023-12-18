package com.datetif.config

sealed class ResponseCredentials{
    object USER_NOT_FOUND : ResponseCredentials()
    object INCORRECT_PASSWORD : ResponseCredentials()
    object SUCCESS : ResponseCredentials()
}
