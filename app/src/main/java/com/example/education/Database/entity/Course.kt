package com.example.education.Database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Course(
    @PrimaryKey(autoGenerate = true)
    val id: Long=0,
    val name: String,
    val description: String
)
