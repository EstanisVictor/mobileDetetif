package com.datetif.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Quater")
class QuaterEntity {

    constructor()

    constructor(name: String, year: Int, classe: String){
        this.name = name
        this.year = year
        this.classe = classe
    }
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0L

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "year")
    var year: Int = 0

    @ColumnInfo(name = "classe")
    var classe: String = ""
}