package com.example.education.Database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0,
    val firstName:String,
    val lastname:String,
    val patron:String,
    val date:String,
    @ColumnInfo("Mentor Id")
    val mentorId:Int,
    @ColumnInfo("Group Id")
    val groupId: Int

)
