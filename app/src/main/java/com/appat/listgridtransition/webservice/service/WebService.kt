package com.appat.graphicov.webservice.service

import com.appat.listgridtransition.R
import com.appat.listgridtransition.utilities.Utility
import com.appat.listgridtransition.webservice.serviceinterface.PicsumService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WebService {
    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://picsum.photos/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getService(serviceClass: Class<PicsumService>): PicsumService {
        return initRetrofit().create(serviceClass)
    }

    fun getPicsumService(serviceClass: Class<PicsumService>): PicsumService
    {
        return getService(serviceClass)
    }
}