package com.example.education.Database.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.education.Database.entity.Mentor

@Dao
interface MentorDao {
    @Insert
    fun addMentor(mentor: Mentor)

    @Delete
    fun deleteMentor(mentor: Mentor)

    @Update
    fun editMentor(mentor: Mentor)

    @Query("select * from Mentor")
    fun allMentor():List<Mentor>
}