package com.khhhm.worefa.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.khhhm.worefa.data.dao.AppointmentDao
import com.khhhm.worefa.data.entitys.Appointment
import com.khhhm.worefa.data.network.AppointmentApiService
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class AppointmentRepository private constructor(private val appointmentDao: AppointmentDao, private val appointmentApiService: AppointmentApiService){

    fun getAllMyAppointment(context: Context):LiveData<List<Appointment>> {

        return this.appointmentDao.getAllappointments()
    }
    suspend fun getAllFromApi(phoneno:String){
        withContext(IO){
            val appointemnts=appointmentApiService.getMyAppointments(phoneno).await().body()
            if(appointemnts!=null)
                appointmentDao.insertAll(appointemnts)
        }
    }

    fun getAppointment(appId:Int) = this.appointmentDao.getAppointment(appId)

    suspend fun insertAppointment(appointment: Appointment){
        withContext(IO){
            val newAppointment=
                appointmentApiService.postAppointments(appointment).await().body()
           appointmentDao.insertAppointment(newAppointment!!)
        }
    }
    suspend fun deleteAppointment(appointment: Appointment){
        withContext(IO){
            if(appointmentApiService.deleteAppointments(appointment.Id).await().isSuccessful){
                appointmentDao.deleteAppointment(appointment)
            }
        }
    }
    suspend fun updateAppointment(appointment: Appointment){
        withContext(IO){
            val updatedAppointment=appointmentApiService.updateAppointments(appointment).await().body()
            appointmentDao.updateAppointment(updatedAppointment!!)
        }
    }

    companion object{
        @Volatile private var instance: AppointmentRepository? = null
        fun getInstance(appointmentDao: AppointmentDao,appointmentApiService: AppointmentApiService) =
            instance ?: synchronized(this) {
                instance ?: AppointmentRepository(appointmentDao,appointmentApiService).also { instance = it }
            }
    }
}