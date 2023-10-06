package com.appat.listgridtransition.roomdb.repository

import android.util.Log
import com.appat.listgridtransition.webservice.api.PicsumApi

class WebServiceRepository(private val api: PicsumApi) {
    suspend fun getList(params: HashMap<String, Int>)
    {
        try {
            val allLists = api.getList(params)
            PicsumRepository().insertAllLists(allLists)
        }
        catch (e: Exception) {
            Log.e("WebServiceRepository", e.toString())
        }
    }
}