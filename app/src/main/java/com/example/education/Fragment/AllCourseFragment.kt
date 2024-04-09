package com.example.education.Fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.education.Adapters.CourseAdapter
import com.example.education.Database.AppDatabase
import com.example.education.Database.entity.Course
import com.example.education.Fragment.Course.CourseDescFragment
import com.example.education.R
import com.example.education.databinding.AddMentorDialogBinding
import com.example.education.databinding.FragmentAllCourseBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AllCourseFragment : Fragment(), CourseAdapter.MyClickListener {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentAllCourseBinding
    private lateinit var adapter: CourseAdapter
    private lateinit var list: ArrayList<Course>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val database: AppDatabase by lazy{
            AppDatabase.getInstance(requireContext())
        }
        binding = FragmentAllCourseBinding.inflate(inflater, container, false)
        binding.apply {
            backbtn.setOnClickListener {
                findNavController().navigate(R.id.menuFragment)
            }

            add.setOnClickListener {
                val alertDialog = AlertDialog.Builder(requireContext())
                val alertDialogBinding = AddMentorDialogBinding.inflate(layoutInflater)
                alertDialog.setView(alertDialogBinding.root)
                val dialog = alertDialog.create()

                alertDialogBinding.apply {
                    add.setOnClickListener {
                        val name = coursename.text.toString()
                        val des = coursedes.text.toString()
                        val course = Course(name = name, description = des)
                        list.add(course)
                        database.courseDao().addCourse(course)
                        adapter.notifyItemInserted(list.size)
                        dialog.dismiss()
                    }
                    close.setOnClickListener {
                        dialog.dismiss()
                    }
                }

                dialog.show()
            }

        }
        list = ArrayList(database.courseDao().allCourse())
        adapter = CourseAdapter(list, this)
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AllCourseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemClick(position: Int) {
        val bundle = Bundle()
        val fragment = CourseDescFragment()
        bundle.putString("name", list[position].name)
        bundle.putString("des", list[position].description)
        fragment.arguments = bundle
        findNavController().navigate(R.id.courseDescFragment, bundle)
    }
}