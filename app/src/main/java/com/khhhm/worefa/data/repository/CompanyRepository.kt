package com.khhhm.worefa.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.khhhm.worefa.data.dao.CompanyDao
import com.khhhm.worefa.data.entitys.Company
import com.khhhm.worefa.data.network.CompanyApiService
import com.khhhm.worefa.utilites.isNetworkConnected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class CompanyRepository private constructor(private val companyDao: CompanyDao,private val companyApiService: CompanyApiService){


    fun getAllCompany()=this.companyDao.getAllCompanys()

    fun getCompany(id:Long):LiveData<Company>{
        GlobalScope.launch (Dispatchers.IO){
          ///  val company=companyApiService.getCompany(id).await().body();
         //   if(company!=null){
            val company=Company(4,"ghghghgh","dfdf","5")
            companyDao.insertAll(company)
            val companys=companyDao.getCompany(5).value
            if(company!=null){
            Log.i("comany From Sqlite",companys?.name+"")
            }else{
            Log.i("Company From Sqlite","Is null")
        }
        }

        return companyDao.getCompany(5)
    }
    fun getCompaysFromApi(){}
    fun reloadCompanys(){
        GlobalScope.launch (Dispatchers.IO) {
            val companys =companyApiService.getAllCompany().await().body()

            companyDao.getAllCompanys()
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