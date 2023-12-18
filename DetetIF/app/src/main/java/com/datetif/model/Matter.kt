package com.datetif.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Matter")
class Matter {
    constructor()

    constructor(disc_code: String, name: String){
        this.disc_code = disc_code
        this.name = name
    }

    @PrimaryKey
    @ColumnInfo(name = "disc_code")
    var disc_code: String = ""

    @ColumnInfo(name = "disc_name")
    var name: String = ""
}