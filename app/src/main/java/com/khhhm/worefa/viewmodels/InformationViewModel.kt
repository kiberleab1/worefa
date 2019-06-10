package com.khhhm.worefa.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.khhhm.worefa.data.WorefaDatabase
import com.khhhm.worefa.data.dao.InformationDao
import com.khhhm.worefa.data.repository.InformationRepostitory

class InformationViewModel(application: Application):AndroidViewModel(application) {

    private val informationRepostitory:InformationRepostitory

    init {
        val informationDao:InformationDao=WorefaDatabase.getDatabase(application).informationDao()
        informationRepostitory= InformationRepostitory.getInstance(informationDao)
    }
}