package com.khhhm.worefa.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.khhhm.worefa.data.dao.AppointmentDao
import com.khhhm.worefa.data.dao.CompanyDao
import com.khhhm.worefa.data.entitys.Appointment
import com.khhhm.worefa.data.network.AppointmentApiService
import com.khhhm.worefa.utilites.isNetworkConnected
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class AppointmentRepository private constructor(private val appointmentDao: AppointmentDao, private val appointmentApiService: AppointmentApiService){

    fun getAllMyAppointment(context: Context):LiveData<List<Appointment>> {

        GlobalScope.launch (IO ) {
            if(isNetworkConnected(context)){
                val appointments= appointmentApiService.getMyAppointments("kiber").await().body()
                if(appointments!=null)     appointmentDao.insertAll(appointments)
                Log.i("THis is inside Context","Services")

            }
        }
        Log.i("THis is out of Context","Services")
        return this.appointmentDao.getAllappointments()
    }

    fun getAppointment(appId:Int) = this.appointmentDao.getAppointment(appId)

    suspend fun insertAppointment(appointment: Appointment){
        withContext(IO){
            appointmentDao.insertAppointment(appointment)
        }
    }
    suspend fun deleteAppointment(appointment: Appointment){
        withContext(IO){
            appointmentDao.deleteAppointment(appointment)
        }
    }
    suspend fun updateAppointment(appointment: Appointment){
        withContext(IO){
            appointmentDao.updateAppointment(appointment)
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