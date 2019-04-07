package com.dictionary.androidroom.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "book")
data class Book(
    @PrimaryKey(autoGenerate = true)
    var bookId:Int? = null,
    var bookName:String?,
    var authorName:String?,
    var bookPrice:Double?,
    var bookUrl:String?
)