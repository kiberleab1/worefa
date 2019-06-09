package com.khhhm.worefa.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.khhhm.worefa.data.entitys.Information

@Dao
interface InformationDao {
    @Query("SELECT * FROM information WHERE companyId=:companyId")
    fun getAllInformation(companyId:Long):LiveData<List<Information>>

    @Query("SELECT * FROM information WHERE Id=:id")
    fun getSingleInformation(id:Long):LiveData<Information>

}