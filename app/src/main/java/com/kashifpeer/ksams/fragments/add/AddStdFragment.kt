package com.kashifpeer.ksams.fragments.add

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kashifpeer.ksams.R
import com.kashifpeer.ksams.model.Student
import com.kashifpeer.ksams.viewmodel.StdViewModel
import kotlinx.android.synthetic.main.fragment_add_std.*
import kotlinx.android.synthetic.main.fragment_add_std.view.*
import kotlinx.coroutines.InternalCoroutinesApi
import java.text.SimpleDateFormat
import java.util.*


@InternalCoroutinesApi
class AddStdFragment : Fragment() {

    var cal=Calendar.getInstance()
    private lateinit var mStdViewModel: StdViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_std, container, false)
        mStdViewModel = ViewModelProvider(this).get(StdViewModel::class.java)

        view.addStd_btn.setOnClickListener {
            insertDataToDatabase()

        }


        //************** code for Spinner Class of Student****** Begins hre*****
        view.classSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                // do something upon option selection
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // can leave this empty
            }
        }


        //************** code for Spinner Class of Student****** End hre*****

        //************** code for Date of Admission****** Begins*****
        val dateSetListener= object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateAdmDate()
            }
        }
        view.admDate_dt.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                DatePickerDialog(
                    requireContext(), dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        })
        //************** code for Date Of Admission****** End hre*****
        //************** code for Date of Admission****** Begins*****
        val dateSetListener2= object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateBirthDate()
            }
        }
        view.dob_dt.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                DatePickerDialog(
                    requireContext(), dateSetListener2,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        })
        //************** code for Date Of Admission****** End hre*****

        return view

    }


    private fun updateAdmDate(){
    val myFormat="dd/MM/yyyy"
    val sdf=SimpleDateFormat(myFormat, Locale.US)
    admDate_dt.text=Editable.Factory.getInstance().newEditable(sdf.format(cal.time))

}
    private fun updateBirthDate(){
        val myFormat="dd/MM/yyyy"
        val sdf=SimpleDateFormat(myFormat, Locale.US)
        dob_dt.text=Editable.Factory.getInstance().newEditable(sdf.format(cal.time))

    }




    private fun insertDataToDatabase() {
        var  stdGender=""

        val rollNum=rollNum_txt.text
        val adNum=adNum_txt.text
        val stdName=stdName_txt.text.toString()
        val fatherName=fatherName_txt.text.toString()
        val motherName=motherName_txt.text.toString()
        val residence=residence_txt.text.toString()
        val stdClass=classSpinner.selectedItem.toString()
        val adDate=admDate_dt.text.toString()
        val birthDate=dob_dt.text.toString()
        val stdUid=uid_txt.text.toString()
        val stdPhone=phone_txt.text.toString()




       if(male_radio.isChecked){
           stdGender="M"
       }
        if(female_radio.isChecked){
            stdGender="F"
        }

        if(inputCheck(rollNum,adNum, stdName, fatherName,motherName,residence, stdClass,stdUid,stdPhone,stdGender,adDate,birthDate)){
            // Create User Object
            val student = Student(
                0,
                Integer.parseInt(rollNum.toString()),
                Integer.parseInt(adNum.toString()),
                stdName,
                fatherName,
                motherName,
                residence,
                stdClass,
                stdUid,
                stdPhone,
                adDate,
                birthDate,
                stdGender




            )
            // Add Data to Database
            mStdViewModel.addStudent(student)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addStdFragment_to_viewStdFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(
        rollNum: Editable,
        adNum: Editable,
        stdName: String,
        fatherName: String,
        motherName: String,
        residence: String,
        stdClass: String,
        stdUid: String,
        stdPhone: String,
        adDate:String,
        birthDate: String,
        stdGender: String



    ): Boolean{
        return !(rollNum.isEmpty() && TextUtils.isEmpty(stdName)
                && TextUtils.isEmpty(fatherName)
                && TextUtils.isEmpty(stdClass)
                && TextUtils.isEmpty(stdUid)
                && TextUtils.isEmpty(stdPhone)
                && TextUtils.isEmpty(adDate)
                && TextUtils.isEmpty(birthDate)
                && TextUtils.isEmpty(stdGender))
    }



}

