package com.example.education.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.education.Adapters.CourseAdapter
import com.example.education.Database.AppDatabase
import com.example.education.Database.entity.Course
import com.example.education.Fragment.Mentor.MentorFragment
import com.example.education.R
import com.example.education.databinding.FragmentAllMentorBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AllMentorFragment : Fragment(),CourseAdapter.MyClickListener {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
private  lateinit var binding:FragmentAllMentorBinding
private lateinit var adapter:CourseAdapter
private lateinit var list: List<Course>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val appDatabase:AppDatabase  by lazy {
            AppDatabase.getInstance(requireContext())
        }
        binding=FragmentAllMentorBinding.inflate(layoutInflater)
        list=ArrayList(appDatabase.courseDao().allCourse())
        binding.apply {
            adapter= CourseAdapter(list,this@AllMentorFragment)
            rv.adapter=adapter
            rv.layoutManager=LinearLayoutManager(requireContext())

        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AllMentorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemClick(position: Int) {
        val fragment=MentorFragment()
        val bundle=Bundle()
        bundle.putString("course name",list[position].name)
        fragment.arguments=bundle
        findNavController().navigate(R.id.mentorFragment,bundle)
    }
}