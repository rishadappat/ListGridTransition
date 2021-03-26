package com.appat.listgridtransition.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.appat.listgridtransition.roomdb.dao.PicsumDao
import com.appat.listgridtransition.roomdb.entities.Picsum

@Database(
    entities = [Picsum::class],
    version = 1,
    exportSchema = false
)

abstract class PicsumDatabase: RoomDatabase() {

    abstract fun picsumDao(): PicsumDao

    companion object {

        @Volatile private var instance: PicsumDatabase? = null

        private const val DATABASE_NAME = "PicsumDatabase"

        fun getInstance(context: Context): PicsumDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, PicsumDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance as PicsumDatabase
        }
    }
}