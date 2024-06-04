package com.islamic.remotedatasource.networkcheck

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

class CheckNetworkAvailability @Inject constructor(private val mConnectivityManager: ConnectivityManager) :
    ICheckNetworkAvailability {
    override fun isNetworkAvailable(): Boolean {
        return checkNetworkState()
    }

    private fun checkNetworkState(): Boolean {
        val network = mConnectivityManager.activeNetwork
        val connection = mConnectivityManager.getNetworkCapabilities(network)
        return connection?.hasTransport(NetworkCapabilities.TRANSPORT_VPN) == true || connection?.hasTransport(
            NetworkCapabilities.TRANSPORT_WIFI
        ) == true || connection?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true
    }
}