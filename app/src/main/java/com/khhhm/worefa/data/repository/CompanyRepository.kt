package com.khhhm.worefa.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.khhhm.worefa.data.dao.CompanyDao
import com.khhhm.worefa.data.entitys.Company
import com.khhhm.worefa.data.network.CompanyApiService
import com.khhhm.worefa.utilites.isNetworkConnected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class CompanyRepository private constructor(private val companyDao: CompanyDao,private val companyApiService: CompanyApiService) {


    fun getAllCompanyFromDao():LiveData<List<Company>> {


        return this.companyDao.getAllCompanys();
    }
    suspend fun getAllFromApi(){
    withContext(IO){
        val companys=companyApiService.getAllCompany().await().body()
        if(companys!=null)
        companyDao.insertAll(companys )
        }
    }
    suspend fun insertCompany(company: Company){
        withContext(IO) {
            companyApiService.postCompany(company);
        }
    }
    suspend fun deleteCompany(company: Company){
        withContext(IO) {
            if(companyApiService.deleteCompany(company.Id).await().isSuccessful){
                companyDao.deleteCompany(company)
            }
        }
    }
    suspend fun updateCompany(company: Company){
        withContext(IO) {

            companyApiService.updateComppany(company);
        }
    }


    companion object{
        @Volatile private var instance: CompanyRepository? = null

        fun getInstance(companyDao: CompanyDao,companyApiService: CompanyApiService) =
            instance ?: synchronized(this) {
                instance ?: CompanyRepository(companyDao,companyApiService).also { instance = it }
            }
    }
}