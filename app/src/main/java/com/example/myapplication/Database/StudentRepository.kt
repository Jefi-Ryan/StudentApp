package com.example.myapplication.Database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.Models.Student
import com.example.myapplication.Utils.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject



class StudentRepository @Inject constructor(val dao : Dao) {

    fun getAll() = dao.getAll()


    suspend fun findById(id : Int) = dao.findById(id)


    suspend fun insert(student : Student) = dao.insert(student)


    suspend fun delete(student : Student) = dao.delete(student)


    suspend fun deleteAll() = dao.deleteAll()


    suspend fun update(fname : String, lname : String, id : Int) = dao.update(fname, lname, id)

}