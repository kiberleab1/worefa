package com.khhhm.worefa.data.repository

import android.util.Log
import com.khhhm.worefa.data.dao.CompanyDao
import com.khhhm.worefa.data.dao.ServicesDao
import com.khhhm.worefa.data.entitys.Services
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class ServicesRepository private constructor(private val servicesDao: ServicesDao){

    fun getAllByCompanyId(companyID:Long) = this.servicesDao.getAllByCompanyId(companyID)

    fun getSingleById(id:Long) = this.servicesDao.getSingleById(id);

    fun insertService(){
        GlobalScope.launch(Dispatchers.IO) {
        var service=Services(1,5,Calendar.getInstance(), Calendar.getInstance(),12)
            Log.i("services","Inserted")
        servicesDao.insertService(service)
        }
    }
    fun getAll()=this.servicesDao.getService()
    companion object{
        @Volatile private var instance: ServicesRepository? = null

        fun getInstance(servicesDao: ServicesDao) =
            instance ?: synchronized(this) {
                instance ?: ServicesRepository(servicesDao).also { instance = it }
            }
    }
}