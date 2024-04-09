package com.example.education.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.education.Database.entity.Mentor
import com.example.education.Database.entity.Student
import com.example.education.databinding.ItemMentorBinding

class StudentAdapter(var list: List<Student>, val listener: MentorAdapter.onItemClickListener):RecyclerView.Adapter<StudentAdapter.Vh>() {
    inner class Vh(val binding:ItemMentorBinding):RecyclerView.ViewHolder(binding.root){
           fun onBind(student: Student,position: Int){

           }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
       return Vh(ItemMentorBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int=list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }
    interface onItemClickListener{
        fun onDelete(student: Student, position: Int)
        fun onEdit(student: Student, position: Int)
    }
}