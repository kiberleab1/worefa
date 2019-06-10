package com.khhhm.worefa.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.khhhm.worefa.data.entitys.Appointment
import com.khhhm.worefa.data.entitys.Services
import com.khhhm.worefa.utilites.BASEURL
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApiService {
    @GET("app/services")
    fun getServicesByCompId(@Query("comp" )compId:Long): Deferred<Response<List<Services>>>

    @GET("serviceses/{id}")
    fun getSingleByServiceId(id:Long):Deferred<Response<Services>>



    companion object {

        private val baseUrl = BASEURL

        fun getInstance(): ServiceApiService {

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

            return retrofit.create(ServiceApiService::class.java)
        }
    }
}