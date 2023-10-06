package com.appat.listgridtransition.webservice.serviceinterface

import com.appat.listgridtransition.roomdb.entities.Picsum
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface PicsumService {
    @GET("list")
    suspend fun getList(@QueryMap(encoded=true) params: HashMap<String, Int>): List<Picsum>
}