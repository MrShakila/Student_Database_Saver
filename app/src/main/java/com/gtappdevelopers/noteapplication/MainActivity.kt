package com.gtappdevelopers.noteapplication

import Controller.AddEditStudentActivity
import Controller.StudentViewModel
import Model.Student
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(), StudentClickInterface, StudentClickDeleteInterface {

    //on below line we are creating a variable for our recycler view, exit text, button and viewmodal.
    lateinit var viewModal: StudentViewModel
    lateinit var studentsRV: RecyclerView
    lateinit var addFAB: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //on below line we are initializing all our variables.
        studentsRV = findViewById(R.id.notesRV)
        addFAB = findViewById(R.id.idFAB)
        //on below line we are setting layout manager to our recycler view.
        studentsRV.layoutManager = LinearLayoutManager(this)
        //on below line we are initializing our adapter class.
        val studentRVAdapter = StudentRVAdapter(this, this, this)
        //on below line we are setting adapter to our recycler view.
        studentsRV.adapter = studentRVAdapter
        //on below line we are initializing our view modal.
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(StudentViewModel::class.java)
        //on below line we are calling all notes methof from our view modal class to observer the changes on list.
        viewModal.allStudents.observe(this, Observer { list ->
            list?.let {
                //on below line we are updating our list.
                studentRVAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener {
            //adding a click listner for fab button and opening a new intent to add a new note.
            val intent = Intent(this@MainActivity, AddEditStudentActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onStudentClick(student: Student) {
        //opening a new intent and passing a data to it.
        val intent = Intent(this@MainActivity, SecondActivity::class.java)

            intent.putExtra("student_name", student.student_name)
            intent.putExtra("subject1", student.subject1)
            intent.putExtra("subject2", student.subject2)
            intent.putExtra("subject3", student.subject3)
            intent.putExtra("id", student.id)
            startActivity(intent)
            this.finish()




    }

    override fun onDeleteIconClick(student: Student) {
        //in on note click method we are calling delete method from our viw modal to delete our not.
        viewModal.deleteStudent(student)
        //displaying a toast message
        Toast.makeText(this, "${student.student_name} Deleted", Toast.LENGTH_LONG).show()
    }

}