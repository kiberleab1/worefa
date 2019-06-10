package com.khhhm.worefa.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.khhhm.worefa.data.entitys.Services
import com.khhhm.worefa.data.entitys.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ServiceDaoTest{
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
    fun insertCompanyAndGet() = runBlockingTest {
        // GIVEN - insert a group
        val services = Services(1,3,Calendar.getInstance(), Calendar.getInstance(),2)
        database.servicesDao().insertService(services)

        // WHEN - Get the group by code from the database
        val loaded = database.servicesDao().getSingleById(services.Id.toLong())

        // THEN - The loaded data contains the expected values
        MatcherAssert.assertThat<Services>(loaded as Services, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded.value.Id, CoreMatchers.`is`(services.Id))
        MatcherAssert.assertThat(loaded.value.companyID, CoreMatchers.`is`(services.companyId))
        MatcherAssert.assertThat(loaded.value.startHour, CoreMatchers.`is`(services.endHour))
        MatcherAssert.assertThat(loaded.value.endHour, CoreMatchers.`is`(services.startHour))
        MatcherAssert.assertThat(loaded.value.rating, CoreMatchers.`is`(services.rating))
    }


    @Test
    fun deleteAndGetService() = runBlockingTest {
        // Given a group inserted
        val services = Services(1,3,Calendar.getInstance(), Calendar.getInstance(),2)


        // When deleting a seervic by id
        database.servicesDao().DeleteService(services.companyId.toLong())

        // THEN - The object returned is null
        val getService = database.servicesDao().getAllByCompanyId(services.companyId)
        MatcherAssert.assertThat(getService, CoreMatchers.nullValue())
    }


    @Test
    fun getAllCompaniys() = runBlockingTest {
        val services = Services(1,3,Calendar.getInstance(), Calendar.getInstance(),2)
        database.servicesDao().insertService(services)

        val result = database.servicesDao().getAllByCompanyId(services.companyId)

        MatcherAssert.assertThat(result, CoreMatchers.notNullValue())
    }

}

