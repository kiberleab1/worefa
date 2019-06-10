package com.khhhm.worefa.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.khhhm.worefa.data.entitys.Appointment
import com.khhhm.worefa.data.entitys.Feed
import com.khhhm.worefa.utilites.BASEURL
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FeedApiService {

    @GET("feeds/{companyId}")
    fun getAllByCompanyId(@Path ("companyId") companyId:Long):Deferred<retrofit2.Response<List<Feed>>>

    @POST(value = "feeds")
    fun postFeed(feed: Feed)


    companion object {

        private val baseUrl = BASEURL

        fun getInstance(): FeedApiService {

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

            return retrofit.create(FeedApiService::class.java)
        }
    }
}