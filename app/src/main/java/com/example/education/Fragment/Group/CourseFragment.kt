package com.example.education.Fragment.Group

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.education.Adapters.CourseAdapter
import com.example.education.Adapters.GroupAdapter
import com.example.education.Database.AppDatabase
import com.example.education.Database.entity.Course
import com.example.education.R
import com.example.education.databinding.FragmentCourseBinding
import com.example.education.databinding.ItemcourseBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CourseFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
private lateinit var binding:FragmentCourseBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val appDatabase:AppDatabase by lazy {
            AppDatabase.getInstance(requireContext())
        }
        binding=FragmentCourseBinding.inflate(layoutInflater)

        binding.apply {

        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CourseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}