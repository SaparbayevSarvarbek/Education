package com.example.education.Database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Group(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0,
    val name:String,
    val day:String,
    val date: String,
    val isOpen: String,
    val courseId:Int,
    val mentorId:Int
)
