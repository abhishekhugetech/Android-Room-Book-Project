package com.dictionary.androidroom

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.dictionary.androidroom.adapter.BookListAdapter
import com.dictionary.androidroom.database.Book
import com.dictionary.androidroom.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var adapter:BookListAdapter
    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        recyclerView.layoutManager = GridLayoutManager(this,2)
        val list : List<Book> = listOf()
        adapter = BookListAdapter(list)
        recyclerView.adapter = adapter
        mainActivityViewModel.allBooks.observe(this,object : Observer<List<Book>>{
            override fun onChanged(t: List<Book>?) {
                if (t != null) {
                    adapter.submitList(t)
                }
            }
        })
    }

    fun addBook(view: View){
        val book = Book(
            null,
            bookName.text.toString(),
            authorName.text.toString(),
            bookPrice.text.toString().toDouble(),
            bookImage.text.toString()
        )
        mainActivityViewModel.insertNewBook(book)
        clearFields()
    }

    fun clearFields(){
        bookName.text.clear()
        authorName.text.clear()
        bookImage.text.clear()
        bookPrice.text.clear()
    }

    fun hideAway(view: View){
        if (adder.visibility == GONE){
            adder.visibility = VISIBLE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_close_black_24dp));
            } else {
                floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_close_black_24dp));
            }
        }else{
            adder.visibility = GONE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_black_24dp));
            } else {
                floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_black_24dp));
            }
        }
    }
}
