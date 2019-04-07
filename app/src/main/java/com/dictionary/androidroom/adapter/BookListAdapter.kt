package com.dictionary.androidroom.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dictionary.androidroom.R
import com.dictionary.androidroom.database.Book
import com.dictionary.androidroom.databinding.SingleBookBinding

class BookListAdapter(arrayList: List<Book>) : RecyclerView.Adapter<BookListAdapter.BookListViewHolder>(){

    var bookList = arrayList

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BookListViewHolder {
        val binding = DataBindingUtil.inflate<SingleBookBinding>(
            LayoutInflater.from(p0.context), R.layout.single_book,p0,false
        )
        return BookListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun submitList(list:List<Book>){
        bookList = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(p0: BookListViewHolder, p1: Int) {
        val book = bookList[p1]
        p0.binding.book = book
    }

    class BookListViewHolder(singleBookBinding: SingleBookBinding):RecyclerView.ViewHolder(singleBookBinding.root){
        val binding = singleBookBinding
    }

}