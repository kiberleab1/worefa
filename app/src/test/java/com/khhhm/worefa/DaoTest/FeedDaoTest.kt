package com.khhhm.worefa.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.khhhm.worefa.data.entitys.Feed
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class FeedDaoTest{
    private lateinit var database: WorefaDatabase

    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
       database = Room.databaseBuilder(
           ApplicationProvider.getApplicationContext(),
           WorefaDatabase::class.java,"WorefaDatabase").allowMainThreadQueries().build()

    }

    @After
    fun closeDb() = database.close()


    @Test
    fun insertGroupAndGetByCode() = runBlocking {
        // GIVEN - insert a group
        val feed = Feed(2,4,"meles","henokaddis@gmail.com","0938948909","1234")
        database.feedDao().insertFeed(feed)

        // WHEN - Get the group by code from the database
        val loaded = database.feedDao().getFeed(feed.Id.toLong())

        // THEN - The loaded data contains the expected values
        MatcherAssert.assertThat<Feed>(loaded as Feed, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded.Id, CoreMatchers.`is`(feed.Id))
        MatcherAssert.assertThat(loaded.companyId, CoreMatchers.`is`(feed.companyId))
        MatcherAssert.assertThat(loaded.byWhom, CoreMatchers.`is`(feed.byWhom))
        MatcherAssert.assertThat(loaded.feed, CoreMatchers.`is`(feed.feed))
        MatcherAssert.assertThat(loaded.timestamp, CoreMatchers.`is`(feed.timestamp))
        MatcherAssert.assertThat(loaded.usermail, CoreMatchers.`is`(feed.usermail))
    }


    @Test
    fun deleteUserAndGet() = runBlockingTest {
        // Given a group inserted
        val feed = Feed(5, 5,"man" ,"Appraiser","0988766555","84949475")

        database.feedDao().deleteFeed(feed.Id)



        // THEN - The object returned is null
        val getUser = database.feedDao().getFeed(feed.Id.toLong())
        MatcherAssert.assertThat(getUser, CoreMatchers.nullValue())
    }


    @Test
    fun getAllFeeds() = runBlockingTest {
        val feed = Feed(5, 5,"man" ,"Appraiser","0988766555","84949475")
        database.feedDao().insertFeed(feed)

        val result = database.feedDao().getAllFeed(feed.companyId.toLong())

        MatcherAssert.assertThat(result, CoreMatchers.notNullValue())
    }

}

