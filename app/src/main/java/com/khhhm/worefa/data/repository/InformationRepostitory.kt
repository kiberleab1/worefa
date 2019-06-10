package com.khhhm.worefa.data.repository

import com.khhhm.worefa.data.dao.CompanyDao
import com.khhhm.worefa.data.dao.InformationDao

class InformationRepostitory private constructor(private val informationDao: InformationDao){

    fun getAllInformation(companyId:Long) = this.informationDao.getAllInformation(companyId);

    fun getSingleInformation(id:Long) = this.informationDao.getSingleInformation(id);




    companion object{
        @Volatile private var instance: InformationRepostitory? = null

        fun getInstance(informationDao: InformationDao) =
            instance ?: synchronized(this) {
                instance ?: InformationRepostitory(informationDao).also { instance = it }
            }
    }
}