package com.khhhm.worefa.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.khhhm.worefa.data.entitys.Company

@Dao
interface CompanyDao {
    @Query("SELECT * FROM company")
    fun getAllCompanys():LiveData<List<Company>>

    @Query("SELECT * FROM company WHERE Id=:id")
    fun getCompany(id:Long)   :LiveData<Company>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( companys: List<Company>)
    @Delete
    fun deleteCompany(company: Company)
}