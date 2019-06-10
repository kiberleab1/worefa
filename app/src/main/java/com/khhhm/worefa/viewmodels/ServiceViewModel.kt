package com.khhhm.worefa.viewmodels

import android.app.Application
import android.app.Service
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.khhhm.worefa.data.WorefaDatabase
import com.khhhm.worefa.data.dao.ServicesDao
import com.khhhm.worefa.data.entitys.Services
import com.khhhm.worefa.data.network.ServiceApiService
import com.khhhm.worefa.data.repository.ServicesRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class ServiceViewModel(application: Application):AndroidViewModel(application) {

    private val servicesRepository:ServicesRepository

    init {
        val servicesDao: ServicesDao =WorefaDatabase.getDatabase(application).servicesDao()
        servicesRepository= ServicesRepository.getInstance(servicesDao, ServiceApiService.getInstance())

    }
    fun getCompanyServices(id:Long):LiveData<List<Services>> {
        return servicesRepository.getAllByCompanyId(id)
    }
}