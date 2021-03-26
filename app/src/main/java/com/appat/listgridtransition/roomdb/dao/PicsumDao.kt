package com.appat.listgridtransition.roomdb.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appat.listgridtransition.roomdb.entities.Picsum

@Dao
interface PicsumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertAll(lists: List<Picsum>)

    @Query("SELECT * FROM Picsum")
    fun getAll(): LiveData<List<Picsum>>
}