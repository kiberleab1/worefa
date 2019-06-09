package com.khhhm.worefa.data.entitys

import androidx.room.*
import java.sql.Timestamp

@Entity(tableName = "feed",
    foreignKeys = [ForeignKey(entity = Company::class,parentColumns = ["Id"],childColumns = ["companyId"]),
    ForeignKey(entity = User::class,parentColumns = ["email"],childColumns = ["usermail"])],
    indices = [Index("usermail"),Index("companyId")])

data class Feed(@PrimaryKey(autoGenerate = true) @ColumnInfo(name="Id") val Id:Int,
                @ColumnInfo(name = "companyId") val companyId:Int,
                @ColumnInfo(name="usermail") val usermail:String,
                @ColumnInfo(name="byWhom") val byWhom:String,
                @ColumnInfo(name="feed") val feed:String,
                @ColumnInfo(name="timeStamp") val timestamp: String
) {
}