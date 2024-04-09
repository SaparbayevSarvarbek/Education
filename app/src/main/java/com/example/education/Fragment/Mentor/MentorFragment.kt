package com.example.education.Fragment.Mentor

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.education.Adapters.MentorAdapter
import com.example.education.Database.AppDatabase
import com.example.education.Database.entity.Course
import com.example.education.Database.entity.Mentor
import com.example.education.R
import com.example.education.databinding.AddMentorDialog2Binding
import com.example.education.databinding.AddMentorDialogBinding
import com.example.education.databinding.FragmentMentorBinding
import java.lang.Appendable


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MentorFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentMentorBinding
    private lateinit var adapter: MentorAdapter
    private lateinit var list: ArrayList<Mentor>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val database: AppDatabase by lazy {
            AppDatabase.getInstance(requireContext())
        }
        binding = FragmentMentorBinding.inflate(layoutInflater)
        binding.apply {
            list = ArrayList(database.mentorDao().allMentor())
            adapter = MentorAdapter(list, object : MentorAdapter.onItemClickListener {
                override fun onDelete(mentor: Mentor, position: Int) {
                    database.mentorDao().deleteMentor(mentor)
                    list.remove(mentor)
                    adapter.notifyItemRemoved(position)
                    adapter.notifyItemRangeChanged(position, list.size)
                }

                override fun onEdit(mentor: Mentor, position: Int) {
                    val alertDialog = AlertDialog.Builder(requireContext())
                    val alertDialogBinding = AddMentorDialog2Binding.inflate(layoutInflater)
                    alertDialog.setView(alertDialogBinding.root)
                    val dialog = alertDialog.create()
                    alertDialogBinding.apply {
                        mentorName.setText(mentor.name)
                        mentorLastname.setText(mentor.lastName)
                        mentorFathername.setText(mentor.fatherName)
                        edit.setOnClickListener {
                            val name = mentorName.text.toString()
                            val lastName = mentorLastname.text.toString()
                            val father = mentorFathername.text.toString()
                            mentor.name = name
                            mentor.lastName = lastName
                            mentor.fatherName = father
                            database.mentorDao().editMentor(mentor)
                            adapter.notifyItemRangeChanged(position, list.size)
                            dialog.dismiss()
                        }
                        close.setOnClickListener {
                            dialog.dismiss()
                        }
                    }
                    dialog.show()
                }
            })
            Toast.makeText(requireContext(), "$list", Toast.LENGTH_SHORT).show()
            adapter.notifyDataSetChanged()
            rv.adapter = adapter
            rv.layoutManager = LinearLayoutManager(requireContext())
            val courseName = arguments?.getString("course name")
            toolbarCourseName.text = courseName
            add.setOnClickListener {
                findNavController().navigate(R.id.addMentorFragment)
            }
            backbtn.setOnClickListener {
                findNavController().navigateUp()
            }

        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MentorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}