package com.khhhm.worefa.data.entitys

import androidx.room.*
import java.util.*

@Entity(tableName = "appointment"
        ,indices= arrayOf(Index("id",unique = true),Index("user"),Index("service_id")),
        foreignKeys = [ForeignKey(entity = User::class,parentColumns = ["email"],childColumns = ["user"]),
        ForeignKey(entity = Services::class,parentColumns = ["Id"],childColumns = ["service_id"])]

    )
data class Appointment(@PrimaryKey(autoGenerate = true) @ColumnInfo(name="localId") val localId:Int,
                       @ColumnInfo(name="id") val id:Long,
                       @ColumnInfo(name="service_id") val service_id:Long,
                       @ColumnInfo(name="user") val user:String,
                       @ColumnInfo(name="time") val time:String) {
}