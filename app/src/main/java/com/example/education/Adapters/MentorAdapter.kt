package com.example.education.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.education.Database.entity.Mentor
import com.example.education.R
import com.example.education.databinding.ItemMentorBinding

class MentorAdapter(var list: List<Mentor>,val listener:onItemClickListener) : RecyclerView.Adapter<MentorAdapter.Vh>() {
    inner class Vh(val binding: ItemMentorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(mentor: Mentor, position: Int) {
          binding.apply {
              name.text = "${list[position].name} ${list[position].lastName}"
              fatherName.text = list[position].fatherName
              edit.setOnClickListener { listener.onEdit(mentor,position) }
              delete.setOnClickListener { listener.onDelete(mentor,position) }
          }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemMentorBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MentorAdapter.Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface onItemClickListener {
        fun onDelete(mentor: Mentor, position: Int)
        fun onEdit(mentor: Mentor, position: Int)
    }
}