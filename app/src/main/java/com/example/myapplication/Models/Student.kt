package com.example.myapplication.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "studentTable")
data class Student(
    @PrimaryKey(autoGenerate = true) var id : Int?,
    @ColumnInfo(name = "FirstName") var firstName : String?,
    @ColumnInfo(name = "LastName") var lastName  :String?,
    @ColumnInfo(name = "Age") var age : Int?
)
