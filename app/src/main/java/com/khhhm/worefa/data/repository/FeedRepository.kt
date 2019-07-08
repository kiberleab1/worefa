package com.khhhm.worefa.data.repository

import com.khhhm.worefa.data.dao.FeedDao
import com.khhhm.worefa.data.entitys.chat
import com.khhhm.worefa.data.network.FeedApiService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class FeedRepository private constructor(private val feedDao: FeedDao,private val feedApi:FeedApiService)  {

    fun getAllFeed(companyId:Long)=this.feedDao.getAllFeed(companyId);

    fun getFeed(id:Long)=this.feedDao.getFeed(id);


    suspend fun insertFeed(chat: chat){
        withContext(IO){
            feedDao.insertFeed(chat);
        }
    }
    suspend fun deleteFeed(chat: chat){
        withContext(IO){
            feedDao.deleteFeed(chat);
        }
    }
    suspend fun updateFeed(chat: chat){
        withContext(IO){
            feedDao.updateFeed(chat);
        }
    }

    companion object{
        @Volatile private var instance: FeedRepository? = null

        fun getInstance(feedDao: FeedDao,feedApi: FeedApiService) =
            instance ?: synchronized(this) {
                instance ?:FeedRepository(feedDao,feedApi).also { instance = it }
            }
    }
}