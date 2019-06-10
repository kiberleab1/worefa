package com.khhhm.worefa.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.khhhm.worefa.data.dao.CompanyDao
import com.khhhm.worefa.data.dao.ServicesDao
import com.khhhm.worefa.data.entitys.Services
import com.khhhm.worefa.data.network.ServiceApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class ServicesRepository private constructor(private val servicesDao: ServicesDao,  private val servicesApiService: ServiceApiService){

    fun getAllByCompanyId(companyID:Long):LiveData<List<Services>> {
        GlobalScope.launch {
            val services=servicesApiService.getServicesByCompId(companyID).await().body()
            if(services!=null){
                servicesDao.insertAllService(services)
            }
        }
        return this.servicesDao.getAllByCompanyId(companyID)
    }

    fun getSingleById(id:Long) = this.servicesDao.getSingleById(id);

    fun getAll()=this.servicesDao.getService()
    companion object{
        @Volatile private var instance: ServicesRepository? = null

        fun getInstance(servicesDao: ServicesDao,servicesApiService: ServiceApiService) =
            instance ?: synchronized(this) {
                instance ?: ServicesRepository(servicesDao,servicesApiService).also { instance = it }
            }
    }
}