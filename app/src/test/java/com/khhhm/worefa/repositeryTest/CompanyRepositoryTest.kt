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
import com.khhhm.worefa.data.entitys.Company
import com.khhhm.worefa.data.entitys.Feed
import com.khhhm.worefa.data.network.CompanyApiService
import com.khhhm.worefa.data.network.FeedApiService
import com.khhhm.worefa.data.repository.CompanyRepository
import com.khhhm.worefa.data.repository.FeedRepository

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest

class CompanyRepositoryTest {


    private lateinit var repo: CompanyRepository
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
        repo = CompanyRepository(database.companyDao(), CompanyApiService.getInstance())

    }

    @After
    fun cleanUp() {
        database.close()
    }
    @Test
    fun insertAndRetrieve()= runBlocking{
        // GIVEN - a new group saved in the database
        val company = Company(5,"addis","meles","henokaddis@gmail.com")


        // WHEN  - company retrieved by id
        val result  = repo.getCompany(company.Id.toLong())

           }

    @Test
    fun getALLAndReload() = runBlockingTest {

        // Given a new company in the persistent repository and a mocked callback
        val company = Company(1,"addis","meles","henokaddis@gmail.com")
        val company2 = Company(3,"add","meles","henokaddis@gmail.com")


        // When groups are deleted
        repo.getCompany(company.Id.toLong())

        // Then the reload companies list
        val result = repo.reloadCompanys()

        // Then the getALL companies list
        val result2 = repo.getAllCompany()

        Assert.assertThat(result, CoreMatchers.nullValue())
        Assert.assertThat(result2, CoreMatchers.nullValue())

    }


}