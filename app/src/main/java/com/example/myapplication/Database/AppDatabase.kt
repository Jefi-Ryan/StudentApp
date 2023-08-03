package com.example.myapplication.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.Models.Student

@Database(entities = [Student :: class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao() : Dao

    companion object{

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context : Context) : AppDatabase {
            var tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                tempInstance = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = AppDatabase :: class.java,
                    name = "AppDatabase"
                ).build()

                INSTANCE = tempInstance
                return tempInstance as AppDatabase
            }
        }
    }
}