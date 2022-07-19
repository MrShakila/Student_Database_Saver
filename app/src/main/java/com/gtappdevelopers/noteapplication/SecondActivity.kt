package com.gtappdevelopers.noteapplication


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    lateinit var backBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        supportActionBar?.title= "Student Marks "

        val st_name =intent.getStringExtra("student_name")
        val textView= findViewById<TextView>(R.id.idst_name).apply {
            text=st_name +" Exam Results "
        }
        val st_id =intent.getStringExtra("id")
        val subject1 =intent.getStringExtra("subject1")
        val textView2= findViewById<TextView>(R.id.idMathsMark).apply {
            text=  "Maths : "+subject1
        }
        val subject2 =intent.getStringExtra("subject2")
        val textView4= findViewById<TextView>(R.id.idScienceMarks).apply {
            text="Science : "+ subject2
        }
        val subject3 =intent.getStringExtra("subject3")
        val textView3= findViewById<TextView>(R.id.idEnglishMarks).apply {
            text= "English : "+ subject3
        }

        backBtn = findViewById(R.id.goback)
        backBtn.setOnClickListener(View.OnClickListener {
            backBtn.animate().apply {
               rotationY(360f)

            }
            startActivity(Intent(this , MainActivity::class.java)) })



    }


}