package com.datetif.controller

import android.content.Context
import com.datetif.database.DatabaseROOM
import com.datetif.model.QuaterEntity
import com.datetif.repository.QuaterRepository

class QuaterController(var context: Context) {
    var quaterRepository: QuaterRepository
    init {
        quaterRepository = DatabaseROOM.getDatabase(context).quaterRepository()
    }

    fun saveQuater(nome:String, classe: String, year: Int):Boolean{
        if(nome != null && classe != null && year != null){
            val quater:QuaterEntity = QuaterEntity(nome, year, classe)
            quaterRepository.saveQuater(quater)
            return true
        }
        return false
    }

    fun getAll() : MutableList<QuaterEntity>{
        return quaterRepository.getAll().toMutableList()
    }


}