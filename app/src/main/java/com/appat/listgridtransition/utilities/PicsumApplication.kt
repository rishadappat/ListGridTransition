package com.appat.listgridtransition.utilities

import android.app.Application

class PicsumApplication: Application() {

    companion object {
        var app: PicsumApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }
}