package com.khhhm.worefa.viewmodels

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.khhhm.worefa.BR
import com.khhhm.worefa.data.WorefaDatabase
import com.khhhm.worefa.data.dao.UserDao
import com.khhhm.worefa.data.entitys.User
import com.khhhm.worefa.data.network.UserApiService
import com.khhhm.worefa.data.repository.UserRepository

class UserViewModel(application: Application):AndroidViewModel(application) {

    private val userRepository:UserRepository;
    val user: LiveData<User>
    private val email: String=""
    init {
        userRepository= UserRepository.getInstance(WorefaDatabase.getDatabase(application).userDao(),UserApiService.getInstance())
        //email="kiberleabebiyew@gmail.com"
        user=userRepository.getUserByEmail(email)
    }
    fun postuser(newUser: User){
        userRepository.saveUser(newUser)

    }


    fun onSignUp(view: View,newUser: User?){


    }
}