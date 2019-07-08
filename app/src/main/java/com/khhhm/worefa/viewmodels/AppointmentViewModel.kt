package com.khhhm.worefa.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.khhhm.worefa.data.WorefaDatabase
import com.khhhm.worefa.data.dao.AppointmentDao
import com.khhhm.worefa.data.entitys.Appointment
import com.khhhm.worefa.data.network.AppointmentApiService
import com.khhhm.worefa.data.repository.AppointmentRepository
import com.khhhm.worefa.utilites.isNetworkConnected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppointmentViewModel(application: Application) : AndroidViewModel(application) {

    private val appointmentRepository: AppointmentRepository

    init {
        val appointmentDao: AppointmentDao = WorefaDatabase.getDatabase(application).appointmentDao()
        val appointmentApiService = AppointmentApiService.getInstance()
        appointmentRepository = AppointmentRepository.getInstance(appointmentDao, appointmentApiService)
    }

    fun getAllMyAppointments(): LiveData<List<Appointment>> {
        if (isNetworkConnected(getApplication())) {
            this.viewModelScope.launch(Dispatchers.IO) {
                appointmentRepository.getAllFromApi("keber")
            }
        }
        return appointmentRepository.getAllMyAppointment(getApplication())
    }

    fun insertAppointment(appointment: Appointment) {
        this.viewModelScope.launch((Dispatchers.IO)) {
            appointmentRepository.insertAppointment(appointment);
        }
    }

    fun deleteAppointment(appointment: Appointment) {
        this.viewModelScope.launch((Dispatchers.IO)) {
            appointmentRepository.deleteAppointment(appointment)
        }

    }

    fun updateAppointment(appointment: Appointment) {
        this.viewModelScope.launch(Dispatchers.IO) {
            appointmentRepository.updateAppointment(appointment);
        }
    }
}