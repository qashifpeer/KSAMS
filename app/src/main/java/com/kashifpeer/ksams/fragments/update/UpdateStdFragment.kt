package com.kashifpeer.ksams.fragments.update

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.DatePicker
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kashifpeer.ksams.R
import com.kashifpeer.ksams.model.Student
import com.kashifpeer.ksams.viewmodel.StdViewModel
import kotlinx.android.synthetic.main.fragment_add_std.*
import kotlinx.android.synthetic.main.fragment_add_std.view.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlinx.android.synthetic.main.fragment_update_std.*
import kotlinx.android.synthetic.main.fragment_update_std.view.*
import kotlinx.coroutines.InternalCoroutinesApi
import java.text.SimpleDateFormat
import java.util.*


@InternalCoroutinesApi
class UpdateStdFragment : Fragment() {
    var cal= Calendar.getInstance()
    private val args by navArgs<UpdateStdFragmentArgs>()

    @InternalCoroutinesApi
    private lateinit var mStdViewModel: StdViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_update_std, container, false)
        mStdViewModel = ViewModelProvider(this).get(StdViewModel::class.java)



        view.Up_adNum_txt.setText(args.stdUser.adNum.toString())
        view.Up_rollNum_txt.setText(args.stdUser.rollNum.toString())
        view.Up_stdName_txt.setText(args.stdUser.stdName)
        view.Up_fatherName_txt.setText(args.stdUser.fatherName)
        view.Up_motherName_txt.setText(args.stdUser.motherName)
        view.Up_residence_txt.setText(args.stdUser.residence)
        view.Up_uid_txt.setText(args.stdUser.stdUid)
        view.Up_phone_txt.setText(args.stdUser.stdPhone)
        view.Up_admDate_dt.setText(args.stdUser.adDate)
        view.Up_dob_dt.setText(args.stdUser.birthDate)
        //Up_radioGroup.check(0)
        view.UpClassSpinner.selectedItem((args.stdUser.stdClass))
        view.updateStd_btn.setOnClickListener {

            updateItem()
        }
//************** code for Date of Admission****** Begins*****
        val dateSetListener= object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateAdmDate()
            }
        }
        view.Up_admDate_dt.setOnClickListener(object : View.OnClickListener {
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
        view.Up_dob_dt.setOnClickListener(object : View.OnClickListener {
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

        // Add menu
        setHasOptionsMenu(true)
        return view
    }
    private fun updateAdmDate(){
        val myFormat="dd/MM/yyyy"
        val sdf= SimpleDateFormat(myFormat, Locale.US)
        Up_admDate_dt.text=Editable.Factory.getInstance().newEditable(sdf.format(cal.time))

    }
    private fun updateBirthDate(){
        val myFormat="dd/MM/yyyy"
        val sdf= SimpleDateFormat(myFormat, Locale.US)
        Up_dob_dt.text=Editable.Factory.getInstance().newEditable(sdf.format(cal.time))

    }


    @SuppressLint("ResourceType")
    @InternalCoroutinesApi
    private fun updateItem(){
        var  stdGender=""
        val adNum=Integer.parseInt(Up_adNum_txt.text.toString())
        val rollNum=Integer.parseInt(Up_rollNum_txt.text.toString())
        val stdName=Up_stdName_txt.text.toString()
        val fatherName=Up_fatherName_txt.text.toString()
        val motherName=Up_motherName_txt.text.toString()
        val residence=Up_residence_txt.text.toString()
        val stdClass=UpClassSpinner.selectedItem.toString()
        val stdUid=Up_uid_txt.text.toString()
        val stdPhone=Up_phone_txt.text.toString()
        val adDate=Up_admDate_dt.text.toString()
        val birthDate=Up_dob_dt.text.toString()

        if(Up_male_radio.isChecked){
            stdGender="M"
        }
        if(Up_female_radio.isChecked){
            stdGender="F"
        }
        if (inputCheck(Up_adNum_txt.text,Up_rollNum_txt.text,stdName,fatherName,motherName,residence,stdUid,stdPhone,adDate,birthDate,stdGender)){

            // Create User Object
            val updatedStd = Student(args.stdUser.id, adNum, rollNum, stdName,fatherName,motherName,residence,stdClass,stdUid,stdPhone,adDate,birthDate,stdGender)
            // Update Current User
            mStdViewModel.updateStudent(updatedStd)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateStdFragment_to_viewStdFragment)
        }else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }

    }
    private fun inputCheck(
        rollNum: Editable,
        adNum: Editable,
        stdName: String,
        fatherName: String,
        motherName: String,
        residence: String,
        stdUid:String,
        stdPhone:String,
        adDate:String,
        birthDate: String,
        stdGender:String): Boolean{
        return !(rollNum.isEmpty() && adNum.isEmpty()
                && TextUtils.isEmpty(stdName)
                && TextUtils.isEmpty(fatherName)
                && TextUtils.isEmpty(motherName)
                && TextUtils.isEmpty(residence)
                && TextUtils.isEmpty(stdUid)
                && TextUtils.isEmpty(stdPhone)
                && TextUtils.isEmpty(adDate)
                && TextUtils.isEmpty(birthDate)
                && TextUtils.isEmpty(stdGender))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }
    @InternalCoroutinesApi
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteStudent()
        }
        return super.onOptionsItemSelected(item)
    }

    @InternalCoroutinesApi
    private fun deleteStudent() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mStdViewModel.deleteStudent(args.stdUser)
            Toast.makeText(
                    requireContext(),
                    "Successfully removed: ${args.stdUser.stdName}",
                    Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateStdFragment_to_viewStdFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.stdUser.stdName}?")
        builder.setMessage("Are you sure you want to delete ${args.stdUser.stdName}?")
        builder.create().show()
    }

}

private fun Spinner.selectedItem(stdClass: String) {

}
