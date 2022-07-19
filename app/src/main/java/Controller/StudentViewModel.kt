package Controller

import Controller.StudentRepositry
import Model.Student
import Model.StudentDatabase
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    val allStudents: LiveData<List<Student>>
    val repository: StudentRepositry

    init {
        val dao = StudentDatabase.getDatabase(application).getStudentsDao()
        repository = StudentRepositry(dao)
        allStudents = repository.allstudents
    }

    fun deleteStudent(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(student)
    }

    fun updateStudent(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(student)
    }

    fun addStudent(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(student)
    }
}