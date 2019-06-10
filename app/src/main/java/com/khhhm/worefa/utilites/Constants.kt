package com.khhhm.worefa.utilites

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

const val BASEURL:String="http://192.168.42.101:9090/api/"

fun isNetworkConnected(context: Context):Boolean {
    val connectivtyManager: ConnectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo:NetworkInfo?=  connectivtyManager.activeNetworkInfo
    return networkInfo!=null && networkInfo.isConnected
}