package com.khhhm.worefa.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.khhhm.worefa.data.WorefaDatabase
import com.khhhm.worefa.data.dao.FeedDao
import com.khhhm.worefa.data.network.FeedApiService
import com.khhhm.worefa.data.repository.FeedRepository

class FeedViewModel(application: Application):AndroidViewModel(application) {

    private val feedRepository:FeedRepository;

    init{
        val feedDao:FeedDao= WorefaDatabase.getDatabase(application).feedDao()
        feedRepository= FeedRepository.getInstance(feedDao, FeedApiService.getInstance())
    }
}