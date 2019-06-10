package com.khhhm.worefa.all_dataTest.repositeryTest


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.MediumTest
import com.khhhm.worefa.data.WorefaDatabase
import com.khhhm.worefa.data.entitys.User
import com.khhhm.worefa.data.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.junit.*
import org.junit.runner.RunWith
import androidx.test.runner.AndroidJUnit4
import com.khhhm.worefa.data.entitys.Feed
import com.khhhm.worefa.data.network.FeedApiService
import com.khhhm.worefa.data.network.UserApiService
import com.khhhm.worefa.data.repository.FeedRepository

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest

class FeedRepositoryTest {

    private lateinit var repo: FeedRepository
    private lateinit var database: WorefaDatabase

    // Executes each user synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        // using an in-memory database for testing, since it doesn't survive killing the process
        database = Room.databaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WorefaDatabase::class.java,
            "uniguide_db").allowMainThreadQueries().build()
        repo = FeedRepository(database.feedDao(), FeedApiService.getInstance())

    }

    @After
    fun cleanUp() {
        database.close()
    }
    @Test
    fun insertAndRetrieve()= runBlocking{
        // GIVEN - a new group saved in the database
        val feed = Feed(4, 4,"kkk","djfd@gmail.com","098873","1234")
        repo.insertFeed(feed)

        // WHEN  - Group retrieved by code
        val result  = repo.getFeed(feed.Id.toLong())

        Assert.assertThat(result, CoreMatchers.notNullValue())

        // THEN - Same group is returned
        Assert.assertThat(result.value?.Id, CoreMatchers.`is`(7))
        Assert.assertThat(result.value?.companyId, CoreMatchers.`is`(2))
        Assert.assertThat(result.value?.usermail, CoreMatchers.`is`("meles"))
        Assert.assertThat(result.value?.byWhom, CoreMatchers.`is`("1234"))
        Assert.assertThat(result.value?.feed, CoreMatchers.`is`("0987387346"))
        Assert.assertThat(result.value?.timestamp, CoreMatchers.`is`("0987387346"))

    }

    @Test
    fun deleteUserRetrieveNull() = runBlockingTest {

        // Given a new user in the persistent repository and a mocked callback
        val feed = Feed(4, 4,"kkk","djfd@gmail.com","098873","1234")

        repo.insertFeed(feed)


        // When groups are deleted
        repo.getFeed(feed.Id.toLong())

        // Then the retrieved tasks is an empty list
        val result = repo.getAllFeed(feed.companyId.toLong())
        Assert.assertThat(result, CoreMatchers.nullValue())

    }


}