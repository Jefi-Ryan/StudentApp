package com.example.myapplication.Models

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Database.AppDatabase
import com.example.myapplication.Database.StudentRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.myapplication.Utils.*
import javax.inject.Inject
import javax.inject.Named


class ViewModel @ViewModelInject constructor(
    @Named("Application") application : Application,

    @Named("StudentRepository")
    var repository : StudentRepository
) : AndroidViewModel(application) {

    var allStudents : List<Student> = emptyList()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            allStudents = repository.getAll()
        }
    }

    suspend fun findById(id : Int) : Student?{
        return repository.findById(id)
    }


    fun insert(student : Student) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(student)
    }


    fun delete(student : Student) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(student)
    }


    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }


    fun update(fname : String, lname : String, id : Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(fname, lname, id)
    }

}