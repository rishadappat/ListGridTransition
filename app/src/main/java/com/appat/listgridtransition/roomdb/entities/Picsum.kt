package com.appat.listgridtransition.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Picsum")
data class Picsum(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "author")
    val author: String?,

    @ColumnInfo(name = "download_url")
    val download_url: String?,

    @ColumnInfo(name = "height")
    val height: Int?,

    @ColumnInfo(name = "url")
    val url: String?,

    @ColumnInfo(name = "width")
    val width: Int?
)