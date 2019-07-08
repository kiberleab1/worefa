package com.khhhm.worefa.viewmodels

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.khhhm.worefa.CreateCompanyFragmentDirections
import com.khhhm.worefa.data.WorefaDatabase
import com.khhhm.worefa.data.dao.AppointmentDao
import com.khhhm.worefa.data.dao.CompanyDao
import com.khhhm.worefa.data.entitys.Company
import com.khhhm.worefa.data.network.CompanyApiService
import com.khhhm.worefa.data.repository.AppointmentRepository
import com.khhhm.worefa.data.repository.CompanyRepository
import com.khhhm.worefa.utilites.isNetworkConnected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompanyViewModel(application: Application) : AndroidViewModel(application) {


    private val companyRepository:CompanyRepository
    val newCompany:Company= Company(1,"","","","")
    init{
        val companyDao:CompanyDao=WorefaDatabase.getDatabase(application).companyDao()

        companyRepository= CompanyRepository.getInstance(companyDao,CompanyApiService.getInstance())
    }
    fun getAllCompanys(): LiveData<List<Company>> {
       if(isNetworkConnected(getApplication())){
           this.viewModelScope.launch {
               companyRepository.getAllFromApi();
           }

       }
        return companyRepository.getAllCompanyFromDao()
    }
    fun insertCompany(company: Company){
        this.viewModelScope.launch(Dispatchers.IO) {
            companyRepository.insertCompany(company);
        }
    }

    fun deleteCompany(company: Company){
        this.viewModelScope.launch(Dispatchers.IO) {
            companyRepository.deleteCompany(company)
        }
    }

    fun updateCompany(company: Company){

    }
    fun createBtnOnClickListener(): View.OnClickListener{
        return View.OnClickListener {
            if(this.newCompany.name!=""&& this.newCompany.address!="" && this.newCompany.detail!=""){
                this.insertCompany(newCompany);
                val direction=CreateCompanyFragmentDirections.actionCreateCompanyToAddServices(newCompany.Id)
                Navigation.findNavController(it).navigate(direction)
            }

        }
    }

}