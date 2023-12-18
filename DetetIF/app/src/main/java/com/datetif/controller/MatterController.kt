package com.datetif.controller

import android.content.Context
import com.datetif.database.DatabaseROOM
import com.datetif.model.Matter
import com.datetif.repository.MatterRepository

class MatterController(var context: Context) {
    var matterRepository: MatterRepository

    init {
        matterRepository = DatabaseROOM.getDatabase(context).matterRepository()
    }

    fun saveMatter(disc_code: String, name:String ):Boolean{
        if(name != null && disc_code != null){
            val matter:Matter = Matter(disc_code, name)
            matterRepository.saveMatter(matter)
            return true
        }
        return false
    }

    fun getAll() : MutableList<Matter>{
        return matterRepository.getAll().toMutableList()
    }
}