package com.datetif.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.datetif.model.Matter
import com.datetif.model.QuaterEntity
import com.datetif.model.User
import com.datetif.repository.MatterRepository
import com.datetif.repository.QuaterRepository
import com.datetif.repository.UserRepository

@Database(entities = [User::class, QuaterEntity::class, Matter::class], version = 1, exportSchema = false)
abstract class DatabaseROOM(): RoomDatabase(){
    abstract fun userRepository():UserRepository
    abstract fun quaterRepository():QuaterRepository
    abstract fun matterRepository():MatterRepository
    companion object{
        private lateinit var INSTANCE:DatabaseROOM

        fun getDatabase(contexto:Context):DatabaseROOM{
            if(!::INSTANCE.isInitialized){
                synchronized(DatabaseROOM::class){
                    INSTANCE = Room.databaseBuilder(contexto, DatabaseROOM::class.java,
                        "db_datetif_room").allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }
}