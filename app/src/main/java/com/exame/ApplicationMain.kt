package com.exame

import android.app.Application

class ApplicationMain : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: ApplicationMain? = null
    }
}