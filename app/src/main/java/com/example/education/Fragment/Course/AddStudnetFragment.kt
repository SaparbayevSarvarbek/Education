package com.example.education.Fragment.Course

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.education.Database.AppDatabase
import com.example.education.Database.entity.Student
import com.example.education.R
import com.example.education.databinding.FragmentAddStudnetBinding
import kotlinx.coroutines.flow.combineTransform

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AddStudnetFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private lateinit var binding:FragmentAddStudnetBinding
    private lateinit var list:List<Student>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val appDatabase:AppDatabase by lazy{
            AppDatabase.getInstance(requireContext())
        }
        binding=FragmentAddStudnetBinding.inflate(layoutInflater)
        binding.apply {

            backbtn.setOnClickListener{
                findNavController().navigateUp()
            }
            savebtn.setOnClickListener {
                val lastName=lastname.text.toString()
                val firstName=firstname.text.toString()
                val fatherName=father.text.toString()
                val date=date.text.toString()
                val mentorId=group.id
                val groupId=group.id
             //   var student=Student(firstName=firstName, lastname = lastName, patron = fatherName, date = date, mentorId = mentorId, groupId = groupId)
                findNavController().navigate(R.id.allCourseFragment)
            }
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddStudnetFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}