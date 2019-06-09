package com.khhhm.worefa.data.entitys

import androidx.room.*
import java.sql.Date
import java.util.*

@Entity(tableName = "services",foreignKeys = [ForeignKey(entity = Company::class,parentColumns = ["Id"],childColumns = ["companyId"])],indices = [Index("companyId")])
data class Services (@PrimaryKey @ColumnInfo(name="Id") val Id:Int,
                     @ColumnInfo(name = "companyId") val companyId:Int,
                     @ColumnInfo(name="starthour") val startHour: Calendar = Calendar.getInstance(),
                     @ColumnInfo(name="endhour") val endHour:Calendar= Calendar.getInstance(),
                     @ColumnInfo(name="rating") val rating:Int
)