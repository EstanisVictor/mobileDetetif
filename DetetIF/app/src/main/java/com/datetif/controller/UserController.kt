package com.datetif.controller

import android.content.Context
import com.datetif.config.ResponseCredentials
import com.datetif.database.DatabaseROOM
import com.datetif.model.User
import com.datetif.repository.UserRepository

class UserController(var context: Context) {
    var userRepository: UserRepository

    init {
        userRepository = DatabaseROOM.getDatabase(context).userRepository()
    }

    fun saveUser(nome: String, RA: String, email: String, senha: String): Boolean {
        if (nome != null && RA != null && senha != null && email != null && email.count { it == '@' } == 1) {
            val usr: User = User(nome, RA, senha, email)
            userRepository.saveUser(usr)
            return true
        }
        return false
    }

    fun validateLogin(ra: String, senha: String): ResponseCredentials {
        val usr:User ? = userRepository.getUser(ra)

        return when {
            usr == null -> ResponseCredentials.USER_NOT_FOUND
            usr.senha.equals(senha) -> ResponseCredentials.SUCCESS
            else -> ResponseCredentials.INCORRECT_PASSWORD
        }
    }

    fun updateUser(ra: String, novaSenha: String): ResponseCredentials {

        return when (userRepository.getUser(ra)) {
            null -> ResponseCredentials.USER_NOT_FOUND
            else -> {
                userRepository.updateUser(ra, novaSenha)
                ResponseCredentials.SUCCESS
            }
        }
    }

    fun getUserByRa(ra: String): User? {
        return userRepository.getUser(ra)
    }
}