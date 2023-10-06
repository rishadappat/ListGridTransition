package com.appat.listgridtransition.roomdb.repository

import com.appat.listgridtransition.roomdb.entities.Picsum
import com.appat.listgridtransition.utilities.Utility

class PicsumRepository {
    suspend fun insertAllLists(lists: List<Picsum>) = Utility.getDatabase().picsumDao().insertAll(lists)

    suspend fun getList() = Utility.getDatabase().picsumDao().getAll()
}