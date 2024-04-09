package com.example.education.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.education.Adapters.CourseAdapter
import com.example.education.Adapters.GroupAdapter
import com.example.education.Database.AppDatabase
import com.example.education.Database.entity.Course
import com.example.education.R
import com.example.education.databinding.FragmentAllGroupBinding
import com.example.education.databinding.FragmentCourseBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AllGroupFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private lateinit var binding: FragmentAllGroupBinding
    private lateinit var adapter: GroupAdapter
    private lateinit var list: List<Course>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val appDatabase: AppDatabase by lazy {
            AppDatabase.getInstance(requireContext())
        }
        binding=FragmentAllGroupBinding.inflate(layoutInflater)
        binding.apply {
            list=ArrayList(appDatabase.courseDao().allCourse())
            adapter= GroupAdapter(list,object:GroupAdapter.onItemClickListener{
                override fun onClick(course: Course, position: Int) {
                    findNavController().navigate(R.id.courseFragment)
                }
            })
            rv.adapter=adapter
            rv.layoutManager=LinearLayoutManager(requireContext())
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AllGroupFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}