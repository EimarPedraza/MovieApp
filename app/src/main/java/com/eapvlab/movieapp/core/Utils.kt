package com.eapvlab.movieapp.core

import com.eapvlab.movieapp.application.AppConstants.LAN_EN_US
import com.eapvlab.movieapp.application.AppConstants.LAN_ES_MX
import java.util.Locale

object Utils {

    fun getDefaultLanguage(): String {
        val locale = Locale.getDefault().language
        return if (locale == "es") {
            LAN_ES_MX
        } else {
            LAN_EN_US
        }
    }

}