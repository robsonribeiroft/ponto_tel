package br.com.rrdev.pontotel.util

import android.content.Context
import android.net.ConnectivityManager
import br.com.rrdev.pontotel.PontotelApplication.Companion.application

class Network {

    companion object {
        fun hasConnection():Boolean{
            val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected

        }
    }
}