package com.datetif.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.datetif.model.User

@Dao
interface UserRepository {
    @Insert
    fun saveUser(user: User)

    @Query("Select * From User Where RA=:ra")
    fun getUser(ra:String):User?

    @Query("Update User SET senha=:novaSenha Where RA=:ra")
    fun updateUser(ra:String, novaSenha:String)
}