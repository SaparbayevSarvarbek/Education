package com.example.education.Fragment.Mentor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.education.Database.AppDatabase
import com.example.education.Database.entity.Mentor
import com.example.education.R
import com.example.education.databinding.FragmentAddMentorBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AddMentorFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
private lateinit var binding:FragmentAddMentorBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAddMentorBinding.inflate(layoutInflater)
        val appDatabase:AppDatabase by lazy {
            AppDatabase.getInstance(requireContext())
        }
        binding.apply {
           save.setOnClickListener {
               val lastName=lastName.text.toString()
               val name=name.text.toString()
               val fatherName=fatherName.text.toString()
               val mentor= Mentor(name = name, lastName = lastName, fatherName = fatherName)
               appDatabase.mentorDao().addMentor(mentor)
               findNavController().navigateUp()
           }
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddMentorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}