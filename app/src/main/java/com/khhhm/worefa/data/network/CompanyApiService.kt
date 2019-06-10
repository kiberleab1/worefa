package com.khhhm.worefa.data.network

import androidx.lifecycle.LiveData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.khhhm.worefa.data.entitys.Company
import com.khhhm.worefa.utilites.BASEURL
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CompanyApiService {

    @GET("app/comp")
    fun getAllCompany():Deferred<Response<List<Company>>>

    @GET("companies/{id}")
    fun getCompany(@Path("id") id:Long) :Deferred<Response<Company>>

    companion object {

        private val baseUrl = BASEURL

        fun getInstance(): CompanyApiService {

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

            return retrofit.create(CompanyApiService::class.java)
        }
    }

}