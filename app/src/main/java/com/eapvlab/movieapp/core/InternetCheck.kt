package com.eapvlab.movieapp.core

import com.eapvlab.movieapp.application.AppConstants.PING_IP
import com.eapvlab.movieapp.application.AppConstants.PING_PORT
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.InetSocketAddress
import java.net.Socket

object InternetCheck {

    suspend fun isNetworkAvailable(
        ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Boolean = withContext(ioDispatcher) {
        try {
            val sock = Socket()
            val socketAddress = InetSocketAddress(PING_IP, PING_PORT)
            sock.connect(socketAddress, 5000)
            sock.close()
            true
        } catch (exception: Exception) {
            exception.printStackTrace()
            false
        }
    }

}
