package Controller

import Model.Student
import Model.StudentsDao
import androidx.lifecycle.LiveData

class StudentRepositry(private val studentsDao: StudentsDao) {


    val allstudents: LiveData<List<Student>> = studentsDao.getAllstudents()

    suspend fun insert(student: Student) {
        studentsDao.insert(student)
    }
    suspend fun delete(student: Student){
        studentsDao.delete(student)
    }

    suspend fun update(student: Student){
        studentsDao.update(student)
    }
}