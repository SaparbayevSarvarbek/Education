package com.example.education.Database.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.education.Database.entity.Student

@Dao
interface StudentDao {
    @Insert
    fun addStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)

    @Update
    fun editStudent(student: Student)

    @Query("Select * from Student")
    fun allStudent():List<Student>
}