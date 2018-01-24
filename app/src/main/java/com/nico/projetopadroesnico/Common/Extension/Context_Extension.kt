package com.nico.projetopadroesnico.Common.Extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by nicolaugalves on 11/09/17.
 */


fun Context.isNetworkAvailable(): Boolean {
    val connectivity = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val info = connectivity.allNetworkInfo
    info?.indices?.filter { info[it].state == NetworkInfo.State.CONNECTED }?.forEach { return true }

    return false
}