package com.khhhm.worefa.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.khhhm.worefa.data.entitys.chat

@Dao
interface FeedDao {
    @Query("SELECT * FROM feed WHERE companyId=:companyId")
    fun getAllFeed(companyId:Long):LiveData<List<chat>>

    @Query("SELECT * FROM feed WHERE companyId=:id")
    fun getFeed(id:Long):LiveData<chat>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFeed(chat: chat)
    @Delete
    fun deleteFeed(chat: chat)
    @Update
    fun updateFeed(chat: chat)
}