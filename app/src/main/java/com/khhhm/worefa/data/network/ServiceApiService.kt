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
import retrofit2.http.*

interface ServiceApiService {
    @GET("Services")
    fun getServicesByCompId(@Query("comp" )compId:Long): Deferred<Response<List<Services>>>

    @GET("serviceses/{id}")
    fun getSingleByServiceId(id:Long):Deferred<Response<Services>>


    @POST("Services")
    fun postService(@Body services: Services):Deferred<Response<Services>>

    @PUT("app/services")
    fun updateService(@Body services: Services):Deferred<Response<Services>>

    @DELETE("app/services")
    fun deleteService(@Body services: Services):Deferred<Response<Void>>


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