package com.khhhm.worefa.data.repository

import androidx.lifecycle.LiveData
import com.khhhm.worefa.data.dao.ServicesDao
import com.khhhm.worefa.data.entitys.Services
import com.khhhm.worefa.data.network.ServiceApiService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class ServicesRepository private constructor(
    private val servicesDao: ServicesDao,
    private val servicesApiService: ServiceApiService
) {

    fun getAllByCompanyId(companyId: Long): LiveData<List<Services>> {
        /* withContext(IO){
           servicesApiService.getServicesByCompId(companyId).await().body()?.let { servicesDao.insertAllService(it) };
         }*/
        return servicesDao.getService();
    }

    suspend fun getAllCompaniesFromApi(id:Long){
        val listOfServices=servicesApiService.getServicesByCompId(id).await().body()
        servicesDao.insertAllService(listOfServices!!);
    }


    suspend fun insertService(services: Services) {
        withContext(IO) {
            servicesApiService.postService(services);
        }
    }

    suspend fun deleteService(services: Services) {
        withContext(IO) {
            servicesApiService.deleteService(services);
        }
    }

    suspend fun updateService(services: Services) {
        withContext(IO) {
            servicesApiService.updateService(services);
        }
    }

    fun getSingleById(id: Long) = this.servicesDao.getSingleById(id);

    fun getAll() = this.servicesDao.getService()

    companion object {
        @Volatile
        private var instance: ServicesRepository? = null

        fun getInstance(servicesDao: ServicesDao, servicesApiService: ServiceApiService) =
            instance ?: synchronized(this) {
                instance ?: ServicesRepository(servicesDao, servicesApiService).also { instance = it }
            }
    }
}