package com.khhhm.worefa.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.khhhm.worefa.data.dao.*
import com.khhhm.worefa.data.entitys.*

@Database(entities = arrayOf(Appointment::class,Company::class, Feed::class,Information::class, Services::class,User::class) , version = 1,exportSchema = false)
@TypeConverters(Converters::class)
abstract class WorefaDatabase : RoomDatabase() {
    abstract fun appointmentDao():AppointmentDao

    abstract fun companyDao():CompanyDao

    abstract fun servicesDao():ServicesDao

    abstract fun informationDao():InformationDao

    abstract fun userDao():UserDao

    abstract fun feedDao():FeedDao

    companion object {

        @Volatile
        private var INSTANCE: WorefaDatabase? = null

        fun getDatabase(context: Context): WorefaDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WorefaDatabase::class.java, "worefa_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}