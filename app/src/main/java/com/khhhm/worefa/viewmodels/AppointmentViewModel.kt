package com.khhhm.worefa.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.khhhm.worefa.data.WorefaDatabase
import com.khhhm.worefa.data.dao.AppointmentDao
import com.khhhm.worefa.data.entitys.Appointment
import com.khhhm.worefa.data.network.AppointmentApiService
import com.khhhm.worefa.data.repository.AppointmentRepository

class AppointmentViewModel(application: Application):AndroidViewModel(application) {

    private val appointmentRepository:AppointmentRepository

    init {
        val appointmentDao:AppointmentDao=WorefaDatabase.getDatabase(application).appointmentDao()
        val appointmentApiService=AppointmentApiService.getInstance()
        appointmentRepository= AppointmentRepository.getInstance(appointmentDao, appointmentApiService)
    }
    fun getAllMyAppointments():LiveData<List<Appointment>>{
        return appointmentRepository.getAllMyAppointment(getApplication())
    }

}