package com.example.education.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.education.Database.entity.Course
import com.example.education.Fragment.Group.CourseFragment
import com.example.education.databinding.ItemcourseBinding

class GroupAdapter(val list: List<Course>, val listener: onItemClickListener) :
    RecyclerView.Adapter<GroupAdapter.Vh>() {
    inner class Vh(val binding: ItemcourseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(course: Course, position: Int) {
            binding.apply {
                coursename.text = course.name
                linear.setOnClickListener { listener.onClick(course, position) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemcourseBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    interface onItemClickListener {
        fun onClick(course: Course, position: Int)
    }

}