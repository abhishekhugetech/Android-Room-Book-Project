package com.dictionary.androidroom.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import android.widget.Toast
import com.dictionary.androidroom.database.Book
import com.dictionary.androidroom.database.BookDao
import com.dictionary.androidroom.database.BookDb
import java.lang.Exception

class MainActivityViewModel(application: Application):AndroidViewModel(application){
    var bookDao: BookDao
    val context = application
    var allBooks : LiveData<List<Book>>
    init {
        val database = BookDb.getInstance(application)
        bookDao = database.bookDao()
        allBooks = bookDao.getAllBooks()
    }
    fun insertNewBook(book: Book){
        val message = InsertBook(bookDao).execute(book).get()
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

    class InsertBook(bookDao: BookDao) : AsyncTask<Book,Void,String>(){
        private val dao = bookDao
        override fun doInBackground(vararg params: Book?): String {
            try {
                dao.addNewBook(params[0]!!)
                return "New Book Inserted"
            }catch (e:Exception){
                return "Failed Due to : ${e.message}"
            }
        }
    }

}