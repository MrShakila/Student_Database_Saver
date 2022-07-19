package Model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(student : Student)

    @Update
   fun update(student: Student)

    @Delete
     fun delete(student: Student)

    @Query("Select * from studentTable order by id ASC")
    fun getAllstudents(): LiveData<List<Student>>


}