package com.kashifpeer.ksams.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kashifpeer.ksams.R
import com.kashifpeer.ksams.model.Classwise
import com.kashifpeer.ksams.model.Student
import com.kashifpeer.ksams.viewmodel.ClasswiseViewModel
import kotlinx.android.synthetic.main.fragment_add_class.*
import kotlinx.android.synthetic.main.fragment_add_class.view.*
import kotlinx.android.synthetic.main.fragment_add_std.view.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class AddClasswiseFragment : Fragment() {
    @InternalCoroutinesApi
    private lateinit var mClasswiseViewModel: ClasswiseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_class, container, false)
        mClasswiseViewModel= ViewModelProvider(this).get(ClasswiseViewModel::class.java)

        view.btnAddClass.setOnClickListener {
            insertDataToDatabase()

        }

        return view
    }

    private fun insertDataToDatabase() {
        val className=addClass_txt.text.toString()

        if(inputCheck(className)){
            // Create User Object
            val classwise = Classwise(
                0,
                className
            )
            // Add Data to Database
            mClasswiseViewModel.addClasss(classwise)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addClasswiseFragment_to_classwiseFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(
        className:String): Boolean{
        return !(className.isEmpty())

    }

}