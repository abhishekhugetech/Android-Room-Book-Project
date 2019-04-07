package com.dictionary.androidroom.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.dictionary.androidroom.database.Constants.Companion.DATABASE_NAME

@Database(entities = [Book::class] , version = 1 , exportSchema = false)
abstract class BookDb : RoomDatabase(){

    abstract fun bookDao():BookDao
    companion object {
        @Volatile private var instance:BookDb? = null
        fun getInstance(context: Context):BookDb{
            return instance ?: synchronized(this){
                instance?: buildDatabase(context).also{ instance = it}
            }
        }
        private fun buildDatabase(context: Context): BookDb {
            return Room.databaseBuilder(context, BookDb::class.java, DATABASE_NAME)
                .build()
        }
    }
}