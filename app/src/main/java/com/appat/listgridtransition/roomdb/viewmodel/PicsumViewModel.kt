package com.appat.listgridtransition.roomdb.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.appat.graphicov.webservice.service.Resource
import com.appat.listgridtransition.roomdb.repository.PicsumRepository
import com.appat.listgridtransition.roomdb.repository.WebServiceRepository
import kotlinx.coroutines.Dispatchers

class PicsumViewModel(private val picsumRepository: PicsumRepository, private val webServiceRepository: WebServiceRepository) : ViewModel() {
    fun getLists() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = picsumRepository.getList()))
        } catch (exception: Exception) {
            Log.e("getListsFromWeb", exception.message.toString())
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    suspend fun loadListFromWeb(params: HashMap<String, Int>) {
        webServiceRepository.getList(params)
    }
}