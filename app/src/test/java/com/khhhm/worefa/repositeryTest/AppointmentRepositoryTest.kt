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
import com.khhhm.worefa.data.entitys.Appointment
import com.khhhm.worefa.data.repository.AppointmentRepository
import org.hamcrest.MatcherAssert
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest

class AppointmentRepositoryTest {

    private lateinit var repo: AppointmentRepository
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
       repo = AppointmentRepository(database.appointmentDao())

    }

    @After
    fun cleanUp() {
        database.close()
    }
    @Test
    fun insertAndRetrieve()= runBlocking{
        // GIVEN - a new appointment saved in the database
        val appointment = Appointment(3, 2,"man1", Calendar.getInstance() )
        repo.insertAppointment(appointment)

        // WHEN  - appintment retrieved by id
        val result  = repo.getAppointment(appointment.Id)

        Assert.assertThat(result, CoreMatchers.notNullValue())

        // THEN - Same appointment is returned
        Assert.assertThat(result.value?.Id, CoreMatchers.`is`(3))
        Assert.assertThat(result.value?.user, CoreMatchers.`is`("manl"))
        Assert.assertThat(result.value?.time, CoreMatchers.`is`(Calendar.getInstance()))


    }

    @Test
    fun deleteAndUpdateAppointmentRetrieveNull() = runBlockingTest {

        // Given a new appointment in the persistent repository and a mocked callback
        val appointment = Appointment(3, 2,"man1", Calendar.getInstance() )
        repo.insertAppointment(appointment)


        // When appointment are deleted
        repo.deleteAppointment(appointment)

        //retrieve appointment by id
        val result1 = repo.getAppointment(appointment.Id)

        //asser is deleted
        Assert.assertThat(result1, CoreMatchers.nullValue())

        // Then the retrieved appointment is an empty list
        val result = repo.updateAppointment(appointment)
        Assert.assertThat(result, CoreMatchers.nullValue())

    }


      }



