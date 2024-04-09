package com.example.education.Database.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.education.Database.entity.Course

@Dao
interface CourseDao {
    @Insert
    fun addCourse(course: Course)

    @Delete
    fun deleteCourse(course: Course)

    @Query("select * from course")
    fun allCourse():List<Course>
}