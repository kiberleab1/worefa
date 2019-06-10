package com.khhhm.worefa.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.khhhm.worefa.data.entitys.Appointment
import com.khhhm.worefa.data.entitys.Company
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
class AppointmentDaoTest{
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
    fun insertAppointmentAndGet() = runBlockingTest {
        // GIVEN - insert a group
        val appointment = Appointment(3, 2,"man1", Calendar.getInstance() )
        database.appointmentDao().insertAppointment(appointment)

        // WHEN - Get the group by code from the database
        val loaded = database.appointmentDao().getAppointment(appointment.Id)

        // THEN - The loaded data contains the expected values
        MatcherAssert.assertThat<Appointment>(loaded as Appointment, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded.Id, CoreMatchers.`is`(appointment.Id))
        MatcherAssert.assertThat(loaded.user, CoreMatchers.`is`(appointment.user))
        MatcherAssert.assertThat(loaded.serviceId, CoreMatchers.`is`(appointment.serviceId))
        MatcherAssert.assertThat(loaded.time, CoreMatchers.`is`(appointment.time))
    }


    @Test
    fun deleteAppointmentAndGet() = runBlockingTest {
        // Given a appointment inserted
        val appointment = Appointment(3, 2,"man1", Calendar.getInstance() )


        // When deleting a user by id
        database.appointmentDao().deleteAppointment(appointment.copy())

        // THEN - The object returned is null
        val getUser = database.appointmentDao().getAppointment(appointment.Id)
        MatcherAssert.assertThat(getUser, CoreMatchers.nullValue())
    }

    @Test
    fun updateAppointmentAndGetById() = runBlockingTest {
        // When inserting an appointment
        val original = Appointment(1, 5,"man1", Calendar.getInstance())
        database.appointmentDao().insertAppointment(original)

        // When an appointment is updated
        val updated = Appointment(7,8,"henok", Calendar.getInstance())
        database.appointmentDao().updateAppointment(updated)

        // THEN - The loaded data contains the expected values
        val loaded = database.appointmentDao().getAppointment(original.Id)
        MatcherAssert.assertThat(loaded.value?.Id, CoreMatchers.`is`(updated.Id))
        MatcherAssert.assertThat(loaded.value?.user, CoreMatchers.`is`("henok"))
        MatcherAssert.assertThat(loaded.value?.time, CoreMatchers.`is`(Calendar.getInstance()))
    }



    @Test
    fun getAllAppointments() = runBlockingTest {
        val appointment = Appointment(3, 2,"man1", Calendar.getInstance() )
        database.appointmentDao().insertAppointment(appointment)

        val result = database.appointmentDao().getAllappointments()

        MatcherAssert.assertThat(result, CoreMatchers.notNullValue())
    }

}

