package com.khhhm.worefa.data.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "company")
data class Company(@PrimaryKey @ColumnInfo(name="Id") val Id:Int,
                   @ColumnInfo(name="name")val name:String,
                   @ColumnInfo(name="address") val address:String,
                   @ColumnInfo(name="rating") val rating:String) {
}