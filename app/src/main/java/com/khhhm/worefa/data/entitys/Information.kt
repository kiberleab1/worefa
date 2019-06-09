package com.khhhm.worefa.data.entitys

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "information",foreignKeys = [ForeignKey(entity = Company::class,parentColumns = ["Id"],childColumns = ["companyId"])],indices = [Index("companyId")])
data class Information(@PrimaryKey @ColumnInfo(name="Id") val Id:Int,
                       @ColumnInfo(name="companyId") val companyID:Int,
                       @ColumnInfo(name="imgPath") val imgPath:String,
                       @ColumnInfo(name="filePath") val filePath:String,
                       @ColumnInfo(name="detail") val detail:String
                       ):Serializable