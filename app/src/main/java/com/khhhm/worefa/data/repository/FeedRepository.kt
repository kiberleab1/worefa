package com.khhhm.worefa.data.repository

import com.khhhm.worefa.data.dao.FeedDao
import com.khhhm.worefa.data.entitys.Feed
import com.khhhm.worefa.data.network.FeedApiService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class FeedRepository private constructor(private val feedDao: FeedDao,private val feedApi:FeedApiService)  {

    fun getAllFeed(companyId:Long)=this.feedDao.getAllFeed(companyId);

    fun getFeed(id:Long)=this.feedDao.getFeed(id);


    suspend fun insertFeed(feed: Feed){
        withContext(IO){
            feedDao.insertFeed(feed);
        }
    }
    suspend fun deleteFeed(feed: Feed){
        withContext(IO){
            feedDao.deleteFeed(feed);
        }
    }
    suspend fun updateFeed(feed: Feed){
        withContext(IO){
            feedDao.updateFeed(feed);
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