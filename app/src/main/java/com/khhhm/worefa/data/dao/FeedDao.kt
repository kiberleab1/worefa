package com.khhhm.worefa.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.khhhm.worefa.data.entitys.Feed

@Dao
interface FeedDao {
    @Query("SELECT * FROM feed WHERE companyId=:companyId")
    fun getAllFeed(companyId:Long):LiveData<List<Feed>>

    @Query("SELECT * FROM feed WHERE companyId=:id")
    fun getFeed(id:Long):LiveData<Feed>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFeed(feed: Feed)
    @Delete
    fun deleteFeed(feed: Feed)
    @Update
    fun updateFeed(feed: Feed)
}