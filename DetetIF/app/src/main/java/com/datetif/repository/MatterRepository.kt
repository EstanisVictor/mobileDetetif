package com.datetif.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.datetif.model.Matter

@Dao
interface MatterRepository {
    @Insert
    fun saveMatter(matter: Matter)

    @Query("SELECT * FROM Matter")
    fun getAll():List<Matter>
}