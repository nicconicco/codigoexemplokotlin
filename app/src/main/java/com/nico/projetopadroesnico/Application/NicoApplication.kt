package com.nico.projetopadroesnico.Application

import android.app.Application

class NicoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }
    companion object {

        var appInstance: NicoApplication? = null
        fun getInstance(): NicoApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configure a classe Application no AndroidManifest.xml")
            }

            return appInstance!!
        }
    }
}