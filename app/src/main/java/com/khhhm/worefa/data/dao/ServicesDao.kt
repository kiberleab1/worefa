package com.khhhm.worefa.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khhhm.worefa.data.entitys.Services

@Dao
interface ServicesDao {
    @Query("SELECT * FROM services WHERE companyId=:companyId")
    fun getAllByCompanyId(companyId:Long):LiveData<List<Services>>

    @Query("SELECT * FROM services")
    fun getService():LiveData<List<Services>>

    @Query("SELECT * FROM services WHERE Id=:id")
    fun getSingleById(id:Long):LiveData<Services>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertService(services: Services)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllService(services: List<Services>)

}