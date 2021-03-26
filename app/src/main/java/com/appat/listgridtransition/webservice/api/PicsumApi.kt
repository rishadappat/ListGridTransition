package com.appat.listgridtransition.webservice.api

import com.appat.listgridtransition.webservice.serviceinterface.PicsumService

class PicsumApi(private val picsumService: PicsumService) {
    suspend fun getList(params: HashMap<String, Int>) = picsumService.getList(params)
}