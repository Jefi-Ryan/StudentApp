package com.example.myapplication.Database

import androidx.room.*
import com.example.myapplication.Models.Student

@androidx.room.Dao
interface Dao {
    @Query("SELECT * FROM studentTable")
    fun getAll() : List<Student>

    @Query("SELECT * FROM studentTable WHERE id LIKE :id LIMIT 1")
    suspend fun findById(id : Int) : Student?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(student : Student)

    @Delete
    suspend fun delete(student : Student)

    @Query("DELETE FROM studentTable")
    suspend fun deleteAll()

    @Query("UPDATE studentTable SET FirstName = :fname, LastName = :lname WHERE id LIKE :id")
    suspend fun update(fname : String, lname : String, id : Int)

}