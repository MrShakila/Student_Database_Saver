package Controller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gtappdevelopers.noteapplication.MainActivity
import com.gtappdevelopers.noteapplication.R
import Model.Student

import java.text.SimpleDateFormat
import java.util.*

class AddEditStudentActivity : AppCompatActivity() {
    //on below line we are creating variables for our UI components.
    lateinit var studentNameEdit: EditText
    lateinit var subject1Edt: EditText
    lateinit var subject2Edt: EditText
    lateinit var subject3Edt: EditText
    lateinit var saveBtn: Button


    //on below line we are creating variable for viewmodal and and integer for our note id.
    lateinit var viewModal: StudentViewModel
    var studentId = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)
        //on below line we are initlaiing our view modal.
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(StudentViewModel::class.java)
        //on below line we are initializing all our variables.
        studentNameEdit = findViewById(R.id.idEdtStudentName)
        subject1Edt = findViewById(R.id.idEdtsubject1)

        subject2Edt = findViewById(R.id.idEdtsubject2)
        subject3Edt = findViewById(R.id.idEdtsubject3)
        saveBtn = findViewById(R.id.idBtn)


        //on below line we are getting data passsed via an intent.

            saveBtn.setText("Save Student")


        //on below line we are adding click listner to our save button.
        saveBtn.setOnClickListener {
            //on below line we are getting title and desc from edit text.
            val studentName = studentNameEdit.text.toString()
            val subject1 = subject1Edt.text.toString()
            val subject2 = subject2Edt.text.toString()
            val subject3 = subject3Edt.text.toString()


            //on below line we are checking the type and then saving or updating the data.

                if (studentName.isNotEmpty() && subject1.isNotEmpty()&& subject2.isNotEmpty()&& subject3.isNotEmpty()) {
                  if (subject1.toInt()<=100&&subject2.toInt()<=100&&subject3.toInt()<=100) {
                      saveBtn.isEnabled=true
                      val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    //if the string is not empty we are calling a add note method to add data to our room database.
                    viewModal.addStudent(Student(studentName, subject1,subject2,subject3, currentDateAndTime))
                      Toast.makeText(this, "$studentName Added", Toast.LENGTH_LONG).show()
                  }else{Toast.makeText(this, "Invalid Marks", Toast.LENGTH_LONG).show()}

                }else{
                    Toast.makeText(this, "Invalid Format", Toast.LENGTH_LONG).show()
                }

            //opening the new activity on below line
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}

