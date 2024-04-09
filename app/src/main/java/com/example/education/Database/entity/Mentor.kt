package com.example.education.Database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Mentor(
    @PrimaryKey(autoGenerate = true)
    var id:Long=0,
    var name:String,
    var lastName:String,
    var fatherName:String
//    val courseId:Int,

)
