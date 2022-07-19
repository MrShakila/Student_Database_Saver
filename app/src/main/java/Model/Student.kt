package Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "studentTable")
class Student (@ColumnInfo(name = "st_name")val student_name :String,
               @ColumnInfo(name = "subject1")val subject1 :String,
               @ColumnInfo(name = "subject2")val subject2 :String,
               @ColumnInfo(name = "subject3")val subject3 :String,
               @ColumnInfo(name = "timestamp")val timeStamp :String) {
    @PrimaryKey(autoGenerate = true) var id = 0

}