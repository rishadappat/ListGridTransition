package com.appat.listgridtransition.utilities

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import com.appat.listgridtransition.roomdb.PicsumDatabase

object Utility {

    fun getContext(): Context {
        return PicsumApplication.app!!.applicationContext
    }

    fun openUrlInCustomTab(uriString: String, context: AppCompatActivity)
    {
        Log.d("openUrlInCustomTab", uriString)
        val uri = Uri.parse(uriString)
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, uri)
    }

    fun getString(resId: Int):String {
        return PicsumApplication.app?.getString(resId) ?: ""
    }

    fun getDatabase(): PicsumDatabase
    {
        return PicsumDatabase.getInstance(getContext())
    }
}