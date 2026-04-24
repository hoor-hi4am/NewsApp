package com.route.newsapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.route.newsapp.data.api.model.SourceDM
import com.route.newsapp.data.database.dao.SourcesDao

@Database(entities = [SourceDM::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    companion object
    {
        private var myDatabase : MyDatabase? = null

        fun createDatabase(context: Context)
        {
            myDatabase = Room.databaseBuilder(context, MyDatabase::class.java,"todo_database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }//دي انا عاوزها تتنده مرة واحده في الابلكيشن ف هروح اندها في حاجة بتشتغل مرة واحده بس (مثلا السبلاش اسكرين)
        fun getDatabase() : MyDatabase
        {
            return myDatabase!!
        }
    }

    abstract fun getSourcesDao(): SourcesDao
}