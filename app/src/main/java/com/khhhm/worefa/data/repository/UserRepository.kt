package com.khhhm.worefa.data.repository

import com.khhhm.worefa.data.dao.CompanyDao
import com.khhhm.worefa.data.dao.UserDao
import com.khhhm.worefa.data.entitys.User
import com.khhhm.worefa.data.network.UserApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserRepository private constructor(private val userDao: UserDao,private val userApiService: UserApiService){

    fun getUserByEmail(email:String) = this.userDao.getByEmail(email);

    fun saveUser(newUser: User){
        GlobalScope.launch(Dispatchers.IO) {
            userApiService.postUser(newUser);
            if(userApiService.getUserByEmail(newUser.email).await().body()?.email!=null){
                userDao.insertUser(newUser);
            }

        }
    }
    companion object{
        @Volatile private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao,userApiService: UserApiService) =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userDao,userApiService).also { instance = it }
            }
    }
}