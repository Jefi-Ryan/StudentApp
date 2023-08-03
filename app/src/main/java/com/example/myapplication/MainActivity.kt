package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import com.example.myapplication.Database.AppDatabase
import com.example.myapplication.Database.StudentRepository
import com.example.myapplication.Models.Student
import com.example.myapplication.Models.ViewModel
import com.example.myapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewmodel: ViewModel by viewModels()
    private lateinit var db : AppDatabase
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        viewmodel = ViewModelProvider(this,
//        ViewModelProvider.AndroidViewModelFactory(application)).get(ViewModel :: class.java)
        // uncomment if not using dagger hilt

        binding.btnWrite.setOnClickListener {
            val fname = binding.etFirstName.text
            val lname = binding.etLastName.text
            val age = binding.etAge.text


            if(fname.isNotEmpty() && lname.isNotEmpty() && age.isNotEmpty()){
                viewmodel.insert(
                    Student(
                    id = null,
                    firstName = fname.toString(),
                    lastName = lname.toString(),
                    age = age.toString().toInt())
                )
                Log.d("MainActivity", viewmodel.allStudents.toString())
            }
        }

        binding.btnUpdate.setOnClickListener {
            val fname = binding.etFirstName.text
            val lname = binding.etLastName.text
            val id = binding.etAge.text


            if(fname.isNotEmpty() && lname.isNotEmpty() && id.isNotEmpty()){
                viewmodel.update(
                    fname = fname.toString(),
                    lname = lname.toString(),
                    id = id.toString().toInt()
                )
            }
        }
        
        binding.btnRead.setOnClickListener{
            val id = binding.etAge.text.toString().toInt()
            GlobalScope.launch(Dispatchers.IO) {
                var data = viewmodel.findById(id)
                binding.etResult.text = "${data?.firstName}  ${data?.lastName}  ${data?.age}"
            }
        }

        binding.btnDelete.setOnClickListener{
            viewmodel.deleteAll()
        }

        binding.btnDeleteOne.setOnClickListener {
            val id = binding.etAge.text.toString().toInt()
            Log.d("MainActivity", "Trying to delete id: $id")
            GlobalScope.launch(Dispatchers.IO) {
                var data = viewmodel.findById(id)
                if (data != null) {
                    viewmodel.delete(data)
                }
            }
        }
    }
}