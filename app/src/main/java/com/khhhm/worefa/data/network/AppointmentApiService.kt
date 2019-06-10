package com.khhhm.worefa.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.khhhm.worefa.data.entitys.Appointment
import com.khhhm.worefa.utilites.BASEURL
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


interface AppointmentApiService {

    @GET("appointments/{id}")
    fun getSingleFindById(@Path("id" )id:Long): Deferred<Response<Appointment>>


    @GET("app/user")
    fun getMyAppointments(@Query("user") userEmail:String):Deferred<Response<List<Appointment>>>

    @POST("appointments")
    fun postAppointments(@Body newAppointment: Appointment):Deferred<Response<Void>>

    @PUT("appointments/{id}")
    fun updateAppointments(@Path("id") id:Long):Deferred<Response<Void>>

    @DELETE("appointments/{id}")
    fun deleteAppointments(@Path("id") id:Long):Deferred<Response<Void>>

    companion object {

        private val baseUrl = BASEURL

        fun getInstance(): AppointmentApiService {

           val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit: Retrofit =  Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

            return retrofit.create(AppointmentApiService::class.java)
        }
    }

}