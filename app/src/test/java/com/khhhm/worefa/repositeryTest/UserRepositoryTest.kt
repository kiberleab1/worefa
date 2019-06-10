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
import com.khhhm.worefa.data.network.UserApiService

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest

class UserRepositoryTest {

    private lateinit var repo: UserRepository
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
       repo = UserRepository(database.userDao(),UserApiService.getInstance())

    }

    @After
    fun cleanUp() {
        database.close()
    }
    @Test
    fun insertAndRetrieve()= runBlocking{
        // GIVEN - a new group saved in the database
        val user = User("Bcccb", "fffhhhh","kkk","djfd@gmail.com","098873","1234")
        repo.saveUser(user)

        // WHEN  - Group retrieved by code
        val result  = repo.getUserByEmail(user.email)

        Assert.assertThat(result, CoreMatchers.notNullValue())

        // THEN - Same group is returned
        Assert.assertThat(result.value?.fname, CoreMatchers.`is`("henok@gmail.com"))
        Assert.assertThat(result.value?.mname, CoreMatchers.`is`("addis"))
        Assert.assertThat(result.value?.lname, CoreMatchers.`is`("meles"))
        Assert.assertThat(result.value?.password, CoreMatchers.`is`("1234"))
        Assert.assertThat(result.value?.phoneNo, CoreMatchers.`is`("0987387346"))

    }

    @Test
    fun deleteUserRetrieveNull() = runBlockingTest {

        // Given a new user in the persistent repository and a mocked callback
        val user = User("Bbb", "fff","kkk","djfd@gmail.com","098873","1234")

        repo.saveUser(user)

        // When groups are deleted
        //repo.deleteUser(user)
        // When groups are deleted
        repo.getUserByEmail(user.email)

        // Then the retrieved tasks is an empty list
        val result = repo.getUserByEmail(user.email)
        Assert.assertThat(result, CoreMatchers.nullValue())

    }


}