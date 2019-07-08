package com.khhhm.worefa.viewmodels

import android.app.Application
import android.app.Service
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.khhhm.worefa.AddServicesDirections
import com.khhhm.worefa.CreateCompanyFragmentDirections
import com.khhhm.worefa.data.WorefaDatabase
import com.khhhm.worefa.data.dao.ServicesDao
import com.khhhm.worefa.data.entitys.Services
import com.khhhm.worefa.data.network.ServiceApiService
import com.khhhm.worefa.data.repository.ServicesRepository
import com.khhhm.worefa.utilites.isNetworkConnected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ServiceViewModel(application: Application):AndroidViewModel(application) {

    private val servicesRepository:ServicesRepository
    public lateinit var newServices: Services

    init {
        val servicesDao: ServicesDao =WorefaDatabase.getDatabase(application).servicesDao()
        servicesRepository= ServicesRepository.getInstance(servicesDao, ServiceApiService.getInstance())
        newServices= Services(1,1,"","","","",1.0)

    }
    fun getCompanyServices(id:Long) :LiveData<List<Services>>{
        if(isNetworkConnected(getApplication())){
            viewModelScope.launch (Dispatchers.IO){
                servicesRepository.getAllCompaniesFromApi(id)
            }
        }
        return  servicesRepository.getAllByCompanyId(id)

    }

    fun insertService(services: Services){
        this.viewModelScope.launch {
            servicesRepository.insertService(services);
        }
    }

    fun deleteService(services: Services){
        this.viewModelScope.launch {
            servicesRepository.deleteService(services);
        }
    }

    fun updateService(services: Services){
        this.viewModelScope.launch {
            servicesRepository.updateService(services);
        }
    }
    fun addNextBtnOnClickListener(): View.OnClickListener{
        return View.OnClickListener {
            this.insertService(newServices)
            newServices=Services(1,1,"","","","",1.0)
            val directions=AddServicesDirections.actionAddServicesSelf(1)
         Navigation.findNavController(it).navigate(directions)
           //     val direction= CreateCompanyFragmentDirections.actionCreateCompanyToAddServices(newCompany.Id)
          //      Navigation.findNavController(it).navigate(direction)

        }
    }

    fun doneBtnOnClickListener(): View.OnClickListener{
        return View.OnClickListener {
            this.insertService(newServices)
             //    val direction= CreateCompanyFragmentDirections.actionCreateCompanyToAddServices(newCompany.Id)
            //      Navigation.findNavController(it).navigate(direction)
            Navigation.findNavController(it).navigateUp()
        }
    }
}