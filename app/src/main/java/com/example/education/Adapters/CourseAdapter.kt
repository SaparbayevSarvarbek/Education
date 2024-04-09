package com.example.education.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.education.Database.entity.Course
import com.example.education.R

class CourseAdapter(var list: List<Course>, val listener: MyClickListener) :
    RecyclerView.Adapter<CourseAdapter.Vh>() {
    inner class Vh(val itemview: View) : RecyclerView.ViewHolder(itemview) {
        val textView: TextView = itemview.findViewById(R.id.coursename)

        init {
            itemview.setOnClickListener {
                val position = adapterPosition
                listener.onItemClick(position)
            }
        }
    }
    fun setFilterListener(list1:List<Course>){
        this.list=list1
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(LayoutInflater.from(parent.context).inflate(R.layout.itemcourse, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.textView.setText(list[position].name)
    }

    interface MyClickListener {
        fun onItemClick(position: Int);
    }
}