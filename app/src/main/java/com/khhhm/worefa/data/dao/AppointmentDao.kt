package com.khhhm.worefa.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.khhhm.worefa.data.entitys.Appointment

@Dao
interface AppointmentDao {
    @Query("SELECT * FROM appointment")
    fun getAllappointments():LiveData<List<Appointment>>

    @Query("SELECT * FROM appointment WHERE Id=:appoId")
    fun getAppointment(appoId:Int ):LiveData<Appointment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAppointment(appointment: Appointment)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(appointment: List<Appointment>)

    @Delete
    fun deleteAppointment(appointment: Appointment)

    @Update
    fun updateAppointment(appointment: Appointment)
}