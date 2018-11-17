package me.maxandroid.github.network

import me.maxandroid.github.AppContext
import org.jetbrains.anko.connectivityManager

object Network {
    fun isAvailable(): Boolean = AppContext.connectivityManager.activeNetworkInfo?.isAvailable ?: false
}