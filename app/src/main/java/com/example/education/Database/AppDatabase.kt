package com.example.education.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.education.Database.Dao.CourseDao
import com.example.education.Database.Dao.MentorDao
import com.example.education.Database.Dao.StudentDao
import com.example.education.Database.entity.Course
import com.example.education.Database.entity.Mentor
import com.example.education.Database.entity.Group
import com.example.education.Database.entity.Student

@Database(entities = [Student::class, Mentor::class,Course::class,Group::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun courseDao(): CourseDao
    abstract fun mentorDao():MentorDao


    companion object {
        private var appDatabase: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (appDatabase == null) {
                appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "my_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return appDatabase!!
        }
    }
}