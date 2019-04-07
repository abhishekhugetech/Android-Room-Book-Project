package com.dictionary.androidroom.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface BookDao{

    @Query("SELECT * FROM book order By bookId desc")
    fun getAllBooks():LiveData<List<Book>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewBook(book: Book)

    @Delete
    fun deleteBook(book: Book)

}