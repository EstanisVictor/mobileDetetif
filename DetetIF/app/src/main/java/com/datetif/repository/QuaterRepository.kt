package com.datetif.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.datetif.model.QuaterEntity

@Dao
interface QuaterRepository {
    @Insert
    fun saveQuater(quater: QuaterEntity)

    @Query("SELECT * FROM Quater")
    fun getAll():List<QuaterEntity>
}