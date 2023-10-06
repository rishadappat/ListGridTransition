package com.appat.listgridtransition.roomdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appat.listgridtransition.roomdb.repository.PicsumRepository
import com.appat.listgridtransition.roomdb.repository.WebServiceRepository
import com.appat.listgridtransition.webservice.api.PicsumApi

class ViewModelFactory(private val api: PicsumApi) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(PicsumViewModel::class.java) -> {
                PicsumViewModel(PicsumRepository(), WebServiceRepository(api)) as T
            }
            else -> throw IllegalArgumentException("Unknown class name")
        }
    }
}