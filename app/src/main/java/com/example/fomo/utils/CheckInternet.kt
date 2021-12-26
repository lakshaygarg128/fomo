package com.darwin.physioai.coreapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.util.Log
import kotlin.properties.Delegates

class CheckInternet {

    object Variables {
        var checkvalue = ""
        var isNetworkConnected: Boolean by Delegates.observable(false) { property, oldValue, newValue ->
            Log.i("Network connectivity", "$newValue")
            checkvalue = newValue.toString()
        }
    }

    fun isConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        return wifiConn != null && wifiConn.isConnected || mobileConn != null && mobileConn.isConnected
    }

    fun startNetworkCallback(context: Context) {
        val cm: ConnectivityManager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder: NetworkRequest.Builder = NetworkRequest.Builder()

        cm.registerNetworkCallback(
            builder.build(),
            object : ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) {
                    Variables.isNetworkConnected = true
                }

                override fun onLost(network: Network) {
                    Variables.isNetworkConnected = false
                }
            })
    }

    fun stopNetworkCallback(context: Context) {
        val cm: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        try {
            cm.unregisterNetworkCallback(ConnectivityManager.NetworkCallback())
        } catch (e: Exception) {
            Log.d("LogTag", e.toString())
        }
    }
}