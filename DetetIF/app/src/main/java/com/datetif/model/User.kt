package com.datetif.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
class User {

    constructor()

    constructor(nome:String, RA:String, senha:String, email:String){
        this.nome = nome
        this.RA = RA
        this.senha = senha
        this.email = email
    }

    @PrimaryKey
    @ColumnInfo(name = "RA")
    var RA: String = ""

    @ColumnInfo(name = "email")
    var email: String = ""

    @ColumnInfo(name = "senha")
    var senha: String = ""

    @ColumnInfo(name = "nome")
    var nome: String = ""
}