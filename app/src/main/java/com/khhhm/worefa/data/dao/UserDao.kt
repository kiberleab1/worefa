package com.khhhm.worefa.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khhhm.worefa.data.entitys.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE email=:mail")
    fun getByEmail(mail:String):LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(newUser: User)
}